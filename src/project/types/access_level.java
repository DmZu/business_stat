package project.types;

/**
 * Created by d.zhukov on 25.03.14.
 */
public enum access_level {

    Business,

    Servicer,

    Developer,

    Unknown;


    public static access_level GetRecType(String name)
    {
        for(access_level ss : access_level.values())
            if(ss.name() == name)
                return ss;

        return access_level.Unknown;
    }

}
