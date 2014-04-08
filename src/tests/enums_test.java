import org.junit.Assert;
import org.junit.Test;
import project.types.record.record_subsystems;
import project.types.record.record_type;

/**
 * Created by d.zhukov on 25.03.14.
 */
public class enums_test extends Assert {

    @Test
    public void testSybSystem() throws Exception {

        assertTrue(record_subsystems.Print == record_subsystems.GetByName("Print"));

        assertTrue(record_subsystems.Unknown == record_subsystems.GetByName("Print1"));

    }

    @Test
    public void testType() throws Exception {

        assertTrue(record_type.Error == record_type.GetByName("Error"));

        assertTrue(record_type.Unknown == record_type.GetByName("Print1"));

    }


}
