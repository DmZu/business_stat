package project.log;

/**
 * Created by d.zhukov on 25.03.14.
 */
public class Record_parametr {

    String name = "";

    String value = "";

    public Record_parametr(String str)
    {
        // " Arg_1=123; "

        String[] strs = str.split("=");



        if(strs.length != 2)
            return;

        name = strs[0];

        //strs = strs[1].split(";");

        boolean is_end = false;

        //System.out.println("=== " + strs[1].length());

        for(int i = 0; i < strs[1].length(); i++)
        {
           if(strs[1].charAt(i) == ';')
           {
               is_end = true;
               break;
           }

            value += strs[1].charAt(i);

        }

        if(!is_end)
            value = "";

    }

    public String GetName()
    {
        return name;
    }

    public String GetValue()
    {
        return value;
    }

}
