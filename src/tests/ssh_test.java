import org.junit.Assert;
import org.junit.Test;
import project.log.Log_reader;

/**
 * Created by d.zhukov on 25.03.14.
 */
public class ssh_test extends Assert{

    @Test
    public void Connection() throws Exception {

        System.out.println("try connect to 192.168.1.68");
        assertTrue(Log_reader.Connect("192.168.1.68","root","123456"));
        System.out.println("try connect to 192.168.1.69");

        assertFalse(Log_reader.Connect("192.168.1.69","root","123456"));

    }
}
