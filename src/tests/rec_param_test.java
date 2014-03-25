import org.junit.Assert;
import org.junit.Test;
import project.log.Record_parametr;

/**
 * Created by d.zhukov on 25.03.14.
 */
public class rec_param_test extends Assert {

    @Test
    public void ParseStr() throws Exception {


        System.out.println("= |" + (new Record_parametr("arg_1=000;").GetValue()) + "|");

        assertTrue((new Record_parametr("arg_1=000;").GetValue()).equals("000"));
        assertTrue((new Record_parametr("  arg_1=000;  ").GetValue()).equals("000"));


        assertTrue((new Record_parametr("arg_1=000;").GetName()).equals("arg_1"));
        assertTrue((new Record_parametr("  arg_1=000;  ").GetName()).equals("arg_1"));

    }
}
