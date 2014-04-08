package project;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import project.types.access_level;
import sun.util.calendar.CalendarUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by d.zhukov on 02.04.14.
 */
public class Adapter_to_config {

    private Properties props= new Properties();

    private final String
            prop_lang = "lang",
            prop_connections = "connections_strings",
            prop_active_connection = "active_connection",
            prop_date_begin = "date_begin",
            prop_date_end = "date_end";

    private static Adapter_to_config ourInstance = new Adapter_to_config();

    public static Adapter_to_config getInstance() {

        //ourInstance.Load_config();
        return ourInstance;
    }

    private Adapter_to_config() { Load_config(); }

    private void Load_config()
    {
        try {
            props.load(new FileInputStream(new File("config.ini")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Save_config()
    {
        try {
            props.store(new FileOutputStream("config.ini"), null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Load_config();
    }

    public Locale GetLocale()
    {

        if( props.getProperty(prop_lang, "ru").equals("ru"))
            return new Locale("ru", "RU");
        else
            return Locale.ENGLISH;

    }

    public  void SetLocale(String local)
    {
        if(local.equals("ru"))
            props.setProperty(prop_lang, "ru");
        else
            props.setProperty(prop_lang, "en");
    }

    public access_level GetAccessLevel()
    {
        return access_level.Business;
    }

    public List<String> GetCnctsStrs()
    {
        List<String> list = new ArrayList<String>();


        for (String str : props.getProperty(prop_connections, "name 68<div>192.168.1.68<div>root<div>123456\nname 69<div>192.168.1.69<div>root<div>123456").split("\n"))
            list.add(str);

        //list.add("name 68\t192.168.1.68\troot\t123456");
        //list.add("name 69\t192.168.1.69\troot\t123456");

        return list;
    }

    public String GetActiveConnect()
    {
        return props.getProperty(prop_active_connection, "name 68<div>192.168.1.68<div>root<div>123456");
        //return "name 68\t192.168.1.68\troot\t123456";
    }

    public void SetActiveConnect(String con_str_name)
    {
        String str = GetActiveConnect();

        for(String st : GetCnctsStrs())
            if(st.split("<div>")[0].equals(con_str_name))
                str = st;

        props.setProperty(prop_active_connection, str);
    }

    public String GetReportFileName()
    {
        if(GetLocale() == Locale.ENGLISH)
            SetLocale("ru");
        else
            SetLocale("en");

        Save_config();

        return "report.html";
    }

    public Date GetBeginDate()
    {
        //Load_config();

        Date d = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");
        try {
            d = sdf.parse(props.getProperty(prop_date_begin, "2014.04.01 12:23:34.123"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("date=" + sdf.format(d));

        return d;
    }

    public void SetBeginDate(Date value)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");

        props.setProperty(prop_date_begin, sdf.format(value));

    }

    public Date GetEndDate()
    {
        //Load_config();

        Date d = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");
        try {
            d = sdf.parse(props.getProperty(prop_date_end, "2014.04.9 14:28:34.123"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("date=" + sdf.format(d));

        return d;
    }

    public void SetEndDate(Date value)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");

        props.setProperty(prop_date_end, sdf.format(value));

    }

}
