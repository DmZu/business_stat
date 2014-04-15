package project.types.record;

import project.types.record.params_level.RecParametr;
import project.types.record.params_level.record_name;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by d.zhukov on 25.03.14.
 */
public class Log_record {


    public static List<Log_record> FileToRecords(String file)
    {
        List<Log_record> list_rec = new ArrayList<Log_record>();

        String[] lines = file.split("\n");

        for(String ln : lines)
        {
            Log_record lr = new Log_record(ln);
            if(lr.GetDate().getTime() != 0)
                list_rec.add(lr);
        }

        return list_rec;
    }


    protected Date data;

    protected record_subsystems subsystem;

    protected record_type message_type;

    protected record_name name;

    protected List<RecParametr> params;

    protected String str_value;


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Log_record)
        {
            Log_record lr_obj = (Log_record)obj;

            if(this.GetDate().getTime() == lr_obj.GetDate().getTime())
                if(this.GetSubSystem() == lr_obj.GetSubSystem())
                    if(this.GetType() == lr_obj.GetType())
                        if(this.GetStrValue().equals(lr_obj.GetStrValue()))
                            return true;
        }

        return false;
    }

    public Log_record(String one_line_rec)
    {
        //14/03/09 13:41:36.852	subsys	event	print_job_start	user_name=operator1; file_name=default; sizeX=1200; sizeY=600; inkC=678; inkM=756; inkY=456; inkK=789;
        data = new Date();
        data.setTime(0);

        subsystem = record_subsystems.Unknown;
        message_type = record_type.Unknown;

        name = record_name.Unknown;

        params = new ArrayList<RecParametr>();

        //SimpleDateFormat sdf = new SimpleDateFormat("yy/mm/dd hh/m");

        String[] strs = one_line_rec.split("\t");

        //System.out.println("strs_len=" + strs.length);

        if(strs.length < 4)
            return;

        String stringDateFormat = "yy/MM/dd HH:mm:ss.SSS";
        SimpleDateFormat format = new SimpleDateFormat(stringDateFormat, Locale.US);

        try {

            data = format.parse(strs[0]);

            //System.out.println(strs[0] + " d= " + data);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //System.out.println(strs[0] + " d2= " + data);

        subsystem = record_subsystems.GetByName(strs[1]);
        message_type = record_type.GetByName(strs[2]);

        str_value = "";
        str_value = strs[3];

        for(int i = 4; i < strs.length; i++)
            str_value += "\t" + strs[i];

        //System.out.println("str3 = " + strs[3]);

        name = record_name.GetByName(strs[3]);

        //System.out.println("str3 = " + name.name());

        if(strs.length>4)
            params = RecParametr.StringToParams(strs[4]);

        //System.out.println("record");


    }



    public Date GetDate()
    {
        return data;
    }

    public record_subsystems GetSubSystem()
    {
        return subsystem;
    }

    public record_type GetType()
    {
        return message_type;
    }

    public String GetStrValue()
    {
        return str_value;
    }

    public record_name GetName()
    {
        return name;
    }

    public List<RecParametr> GetParams()
    {
        return params;
    }

    public String GetParamValue(String param_name)
    {
        for(RecParametr rp : params)
            if(rp.GetName().equals(param_name))
                return rp.GetValue();

        return "";
    }

}
