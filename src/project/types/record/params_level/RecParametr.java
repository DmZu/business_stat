package project.types.record.params_level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.zhukov on 04.04.14.
 */
public class RecParametr {

    private String name = "";
    private String value = "";


    public RecParametr(String str)
    {

        if(str.split("=").length == 2)
        {
            name = str.split("=")[0];

            //System.out.println("strs=" + str.split("=")[1].split(";").length);

            if(str.split("=")[1].split(";").length >= 1)
                value = str.split("=")[1].split(";")[0];
        }

    }

    public String GetName()
    {
        return name;
    }

    public String GetValue()
    {
        return value;
    }

    public static List<RecParametr> StringToParams(String str)
    {
        List<RecParametr> list = new ArrayList<RecParametr>();

        String[] strs = str.split(";");



        for(String s1 : strs)
        {


            RecParametr r = new RecParametr(s1.trim() + ";");



            if(!r.GetName().equals("") && !r.GetValue().equals(""))
                list.add(r);
        }

        return list;
    }

}
