package project.log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.zhukov on 25.03.14.
 */
public class Log_record {

    private long data;

    private record_subsystems subsystem;

    private record_type message_type;

    private String name;

    private List<Record_parametr> params = new ArrayList<Record_parametr>();


    public Log_record(String rec)
    {

    }

}
