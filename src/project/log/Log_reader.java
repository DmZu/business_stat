package project.log;

import com.jcraft.jsch.*;

/**
 * Created by d.zhukov on 25.03.14.
 */
public class Log_reader {

    public static boolean Connect(String ip, String user_name, String password)
    {
        Session session = null;

        Channel channel = null;

        JSch jsch = new JSch();

        try {

            session = jsch.getSession(user_name, ip, 22);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(password);
            session.connect();


            // exec 'scp -f rfile' remotely
            //String command = "scp -f " + remoteFilename;
            channel = session.openChannel("exec");

            session.openChannel("poweroff");
            ((ChannelExec) channel).
            ((ChannelExec) channel).setCommand("poweroff");

        } catch (JSchException e) {
            e.printStackTrace();
        }

        if(session != null && session.isConnected())
            return true;
        else
            return false;
    }

}
