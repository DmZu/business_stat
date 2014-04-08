import org.junit.Assert;
import org.junit.Test;
import project.types.record.Log_record;
import project.types.record.params_level.RecParametr;
import project.types.record.params_level.record_name;

import java.util.List;

/**
 * Created by d.zhukov on 25.03.14.
 */
public class rec_param_test extends Assert {

    @Test
    public void ParseStr() throws Exception {


        Log_record lr = new Log_record("14/03/09 13:41:36.852\tPrint\tDebug\ttext message\t 1");


        assertTrue(lr.GetStrValue().equals("text message\t 1"));

        //assertTrue(lr.GetStrValue().equals("text message"));

        String str = "14/04/01 13:41:36.852\tPrint\tDebug\tprint_job_start\tuser_name=operator1; file_name=default; sizeX=1200; sizeY=600; inkC=678; inkM=756; inkY=456; inkK=789; \n";


        System.out.println("rec_name = " + record_name.GetByName("print_job_start").name());

        List<Log_record> r_list = Log_record.FileToRecords(str);

        assertTrue(r_list.size() == 1);

        System.out.println("rec val=" + RecParametr.StringToParams("user_name=operator1; file_name=default; sizeX=1200; sizeY=600; inkC=678; inkM=756; inkY=456; inkK=789; \n").size());




        assertTrue(r_list.get(0).GetParamValue("file_name").equals("default"));

    }
}
