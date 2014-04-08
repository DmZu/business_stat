package project.types.record;

/**
 * Created by d.zhukov on 25.03.14.
 */
public enum record_type {

    Error,

    Business_params,

    Debug,

    Unknown;


    public static record_type GetByName(String name)
    {
        for(record_type ss : record_type.values())
            if(ss.name().equals(name))
                return ss;

        return record_type.Unknown;
    }

}
