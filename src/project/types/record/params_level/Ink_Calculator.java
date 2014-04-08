package project.types.record.params_level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.zhukov on 08.04.14.
 */
public class Ink_Calculator {

    List<InkMass> inks = new ArrayList<InkMass>();

    public Ink_Calculator(List<RecParametr> params) {

        for(RecParametr rp : params)
        {
            InkMass im = new InkMass(rp);
            if(im.GetColor() != record_ink.Unknown)
                inks.add(im);
        }

    }

    public String CalcInkRashod(List<RecParametr> params)
    {
        String str = "";

        for(RecParametr rp : params)
        {


        }

        return str;
    }

}
