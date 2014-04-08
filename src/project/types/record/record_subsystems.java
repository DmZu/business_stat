package project.types.record;

/**
 * Created by d.zhukov on 25.03.14.
 */
public enum record_subsystems {

    Print,

    Unknown;

    public static record_subsystems GetByName(String name)
    {
        for(record_subsystems ss : record_subsystems.values())
            if(ss.name().equals(name))
                return ss;

        return record_subsystems.Unknown;
    }
}
