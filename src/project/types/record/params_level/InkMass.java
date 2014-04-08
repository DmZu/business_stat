package project.types.record.params_level;

/**
 * Created by d.zhukov on 08.04.14.
 */
public class InkMass {

    record_ink ink_color = record_ink.Unknown;

    int milligrams = 0;

    public InkMass(RecParametr ink_param)
    {
        ink_color = record_ink.GetByName(ink_param.GetName());
        milligrams = Integer.getInteger(ink_param.GetValue());
    }

    public InkMass(record_ink color, int milligrams_)
    {
        ink_color = color;
        milligrams = milligrams_;
    }

    public record_ink GetColor()
    {
        return ink_color;
    }

    public int GetMilligrams()
    {
        return milligrams;
    }


}
