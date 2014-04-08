package project.types.record.params_level;

/**
 * Created by d.zhukov on 25.03.14.
 */
public enum record_name {

    print_job_start,

    print_job_pause,

    print_job_continue,

    print_job_done,

    print_job_abort,



    Unknown;

    public static record_name GetByName(String name)
    {
    /*
        System.out.println();
        for(int i = 0; i < name.getBytes().length; i++)
            System.out.println("qqqq)=|" + name.getBytes()[i] + "|");
        */
        for(record_name ss : record_name.values())
            if(ss.name().equals(name))
                return ss;

        return record_name.Unknown;
    }
}
