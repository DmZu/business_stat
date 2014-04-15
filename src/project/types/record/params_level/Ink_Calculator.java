package project.types.record.params_level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.zhukov on 08.04.14.
 */
public class Ink_Calculator {

    List<InkMass> inks = new ArrayList<InkMass>();

    public Ink_Calculator(List<RecParametr> params) {

        if(params != null)
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
        List<InkMass> rashod = new ArrayList<InkMass>();

        for(RecParametr rp : params)
            for(InkMass ik : inks)
                if(record_ink.GetByName(rp.GetName()) == ik.GetColor())
                    rashod.add(new InkMass(ik.GetColor(), Double.parseDouble(rp.GetValue()) - ik.GetMilligrams() ));

        for(InkMass ik : rashod)
            str += ik.GetColor().name() + "=" + (int)ik.GetMilligrams() + " ml; ";

        return str;
    }

}
