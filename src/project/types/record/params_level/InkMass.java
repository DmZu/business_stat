package project.types.record.params_level;

/**
 * Created by d.zhukov on 08.04.14.
 */
public class InkMass {

    private record_ink ink_color = record_ink.Unknown;

    private double milligrams = 0;

    public InkMass(RecParametr ink_param)
    {
        ink_color = record_ink.GetByName(ink_param.GetName());
        try
        {
            milligrams = Double.parseDouble(ink_param.GetValue());
        }
        catch (NumberFormatException e) { }
    }

    public InkMass(record_ink color, double milligrams_)
    {
        ink_color = color;
        milligrams = milligrams_;
    }

    public record_ink GetColor()
    {
        return ink_color;
    }

    public double GetMilligrams()
    {
        return milligrams;
    }


}
