package project;

import project.log.HTMLGenerator;
import project.log.Log_reader;
import project.types.record.Log_record;
import project.types.record.params_level.Ink_Calculator;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by d.zhukov on 01.04.14.
 */
public class Adapter_to_log {
    private static Adapter_to_log ourInstance = new Adapter_to_log();

    private String conected_to = "";

    private ResourceBundle text = ResourceBundle.getBundle("Resources.localization.lang", Adapter_to_config.getInstance().GetLocale());

    public static Adapter_to_log getInstance() {

        //ourInstance.Connect();

        return ourInstance;
    }




    private Adapter_to_log() {
    }

    private Log_reader log_reader;

    private List<Log_record> log_list = new ArrayList<Log_record>();

    public int Connect()
    {
        System.out.println(Adapter_to_config.getInstance().GetActiveConnect());

        if(ourInstance.log_reader != null && ourInstance.log_reader.IsConnected() && !conected_to.equals(Adapter_to_config.getInstance().GetActiveConnect()))
            Disconnect();

        if(ourInstance.log_reader == null || !ourInstance.log_reader.IsConnected())
            log_reader = new Log_reader(
                    Adapter_to_config.getInstance().GetActiveConnect().split("<div>")[1],
                    Adapter_to_config.getInstance().GetActiveConnect().split("<div>")[2],
                    Adapter_to_config.getInstance().GetActiveConnect().split("<div>")[3]
            );

        if(ourInstance.log_reader != null && ourInstance.log_reader.IsConnected())
            conected_to = Adapter_to_config.getInstance().GetActiveConnect();
        else
            conected_to = "";

        return 0;
    }

    /*
    public int ReConnect()
    {
        System.out.println("1111111");
        Disconnect();
        System.out.println("1111111");

        return Connect();
    }
    */

    public int Disconnect()
    {
        if(ourInstance.log_reader != null && ourInstance.log_reader.IsConnected())
        {
            log_reader.Disconnect();
            log_reader = null;
        }

        return 0;
    }

    /*
    public boolean IsConnected()
    {
        if(ourInstance.log_reader == null || !ourInstance.log_reader.IsConnected())
            return false;
        return true;
    }
    */

    public int ReadLog(Date begin_date, Date end_date)
    {
        Connect();

        log_list.clear();

        //log_list.addAll(log_reader.GetLog());

        AddToLogList(log_reader.GetLog(Adapter_to_config.getInstance().GetAccessLevel(), begin_date, end_date));

        GenerateRepot();

        return 0;
    }



    private int GenerateRepot()
    {
        //String str = "" + GetHead();

        try {


            //System.setProperty("file.encoding", "UTF-8");

            //Charset.

            //FileWriter fw = new FileWriter(Adapter_to_config.getInstance().GetReportFileName());

            OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(Adapter_to_config.getInstance().GetReportFileName()), "UTF-8");



            //Writer writer = new OutputStreamWriter()

            //OutputStreamWriter osw = new OutputStreamWriter()


            fw.write(HTMLGenerator.GetHead());


            //for(record lr : log_list)

            String str = "";


            boolean is_print_start = false;

            Date begin_print = new Date();
            Date end_print = new Date();

            double sizeX = 0, sizeY = 0;

            String user_name = "" , file_name = "";

            Ink_Calculator ink_calculator = new Ink_Calculator(null);


            for(int i = 0; i < log_list.size(); i++)
            {
                Log_record lr = log_list.get(i);

                //if(lr.GetStrValue())

                //System.out.println(lr.GetDate().toString());


                //record_name rn = record_name.print_job_start;



                switch (lr.GetName())
                {
                    case print_job_start:

                        begin_print = lr.GetDate();

                        sizeX = new Double(lr.GetParamValue("sizeX"));

                        sizeY = new Double(lr.GetParamValue("sizeY"));

                        user_name = lr.GetParamValue("user_name");
                        file_name = lr.GetParamValue("file_name");

                        is_print_start = true;

                        ink_calculator = new Ink_Calculator(lr.GetParams());

                        break;

                    case print_job_done:
                        end_print = lr.GetDate();

                        fw.write(MessageAboutOnePrint(begin_print, end_print, 0, sizeX, sizeY, user_name, file_name, ink_calculator.CalcInkRashod(lr.GetParams())));

                        is_print_start = false;

                        break;

                    case print_job_abort:

                        is_print_start = false;
                        end_print = lr.GetDate();
                        fw.write(MessageAboutOnePrint(begin_print, end_print, 1, sizeX, sizeY, user_name, file_name, ""));

                        break;

                    case print_job_pause:

                        break;

                    case print_job_continue:

                        break;

                }



                //fw.write(HTMLGenerator.GetTableRow(lr.GetDate().toString(), lr.GetType().toString(), lr.GetStrValue()));
            }

            if(is_print_start)
            {
                fw.write(MessageAboutOnePrint(begin_print, begin_print, 2, sizeX, sizeY, user_name, file_name, ""));
                is_print_start = false;
            }


            fw.write(HTMLGenerator.GetTail());
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private String MessageAboutOnePrint(Date begin_date, Date end_date, int result_ , double sizeX , double sizeY , String user_name, String file_name, String rashod_ink)
    {
        SimpleDateFormat d_format = new SimpleDateFormat("yyyy.MM.dd  HH:mm", Adapter_to_config.getInstance().GetLocale());

        String annotation_text = text.getString("Print_file") + ":\n" + file_name + "\n" + text.getString("User") + ":\n" + user_name;

        long time = (end_date.getTime() - begin_date.getTime());
        String params_text = text.getString("Size") + ": " + sizeX + " : " + sizeY + " " + text.getString("MM")
                + "\n" + text.getString("Time_printing") + ":\n" + (int)(time/3600000) + ":" + (int)((time%3600000)/60000)
                + "\n" + text.getString("BeginDate") + ":\n" + d_format.format(begin_date)
                + "\n" + text.getString("EndDate") + ":\n" + d_format.format(end_date)
                ;



        String result_text = "";

        if(result_ == 0)
        {
            result_text += text.getString("Print_done");
            result_text += "\n" + text.getString("SpentInk") + ":\n" + rashod_ink;
        }
        if(result_ == 1)
            result_text += text.getString("Print_abort");
        if(result_ == 2)
            result_text += text.getString("Print_resault_unknown");



        return HTMLGenerator.GetTableRow(annotation_text, params_text, result_text);
    }

    private int AddToLogList(List<Log_record> l_list)
    {
        for(Log_record lr : l_list)
        {
            for(int i = 0; i < log_list.size(); i++)
            {
                if(lr.equals(log_list.get(i)))
                    break;

                if(lr.GetDate().getTime() < log_list.get(i).GetDate().getTime())
                {
                    log_list.add(i, lr);
                    break;
                }

                if(i == log_list.size() - 1)
                {
                    log_list.add(lr);
                    break;
                }
            }

            if(log_list.size() == 0)
                log_list.add(lr);
        }

        return 0;
    }


}
