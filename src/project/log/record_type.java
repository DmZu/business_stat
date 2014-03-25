package project.log;

/**
 * Created by d.zhukov on 25.03.14.
 */
public enum record_type {

    Error,

    Debug,



    Unknown;

    public static record_type GetSubsystem(String name)
    {
        for(record_type ss : record_type.values())
            if(ss.name() == name)
                return ss;

        return record_type.Unknown;
    }
}
