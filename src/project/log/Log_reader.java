package project.log;

import com.jcraft.jsch.*;
import project.types.record.Log_record;
import project.types.access_level;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by d.zhukov on 25.03.14.
 */
public class Log_reader {

    Session session = null;

    Channel channel = null;

    JSch jsch = null;

    ChannelSftp c = null;


    boolean is_connected;


    public boolean IsConnected()
    {
        return is_connected;
    }

    public void Disconnect()
    {
        channel.disconnect();
        session.disconnect();
    }

    public Log_reader(String ip, String user_name, String password)
    {

        is_connected = false;

        jsch = new JSch();

        try {

            session = jsch.getSession(user_name, ip, 22);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(password);

            session.connect();

            channel=session.openChannel("sftp");
            channel.connect();
            c=(ChannelSftp)channel;

            is_connected = true;

        } catch (JSchException e) {
            e.printStackTrace();
        }





    }

    public List<Log_record> GetLog(access_level acc_level, Date begin_dat, Date end_dat)
    {
        String all_file = "";

        InputStream file_in = null;



        try {

            file_in = c.get("/var/log/sunprint/business/business.inf");

            byte[] buf = new byte[1024];

            int reded_buf_size = buf.length;



            while(reded_buf_size == buf.length)
            {
                try {

                    //StringReader sr = new StringWriter();

                    reded_buf_size = file_in.read(buf, 0, buf.length);

                    all_file += new String(buf,0,reded_buf_size);

                    System.out.println("= " + reded_buf_size);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            try {
                file_in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SftpException e) {
            e.printStackTrace();
        }

        List<Log_record> out_list = new ArrayList<Log_record>();

        List<Log_record> from_file = Log_record.FileToRecords(all_file);

        for(Log_record lr : from_file)
            if(lr.GetDate().getTime() > begin_dat.getTime() && lr.GetDate().getTime() < end_dat.getTime() )
                out_list.add(lr);

        return out_list;
    }

}
