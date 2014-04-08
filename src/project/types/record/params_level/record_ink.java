package project.types.record.params_level;

/**
 * Created by d.zhukov on 25.03.14.
 */
public enum record_ink {

    inkC,

    inkM,

    inkY,

    inkK,

    Unknown;



    public static record_ink GetByName(String name)
    {
        for(record_ink ss : record_ink.values())
            if(ss.name().equals(name))
                return ss;

        return record_ink.Unknown;
    }
}
