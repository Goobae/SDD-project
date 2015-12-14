package edu.carthage.johnson.grant.aerophile;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by James on 12/12/2015.
 */
public class ClientProxyThread extends AsyncTask<String, Void, String> {
    private int port;
    private TextView message;
    private Activity mActivity;
    public ClientProxyThread(int port, Activity activity)
    {
        this.port = port;
        mActivity = activity;
    }
    @Override
    protected String doInBackground(String... strings)
    {
        String return_s = null;
        //Handler handler = new Handler();
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        Socket socket = null;

        try {

            ServerSocket welcomeSocket = new ServerSocket(port);
            InetAddress p = welcomeSocket.getInetAddress();
            //
            socket = welcomeSocket.accept();
            dataInputStream = new DataInputStream(socket.getInputStream());
            //
            return_s = dataInputStream.readUTF();
        }
        catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (dataOutputStream != null){
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (dataInputStream != null){
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return return_s;
    }
    protected void onPostExecute(String s) {
        //showDialog("Downloaded " + result + " bytes");
        final String holder = s;
        //the string has to be final because if the String 's' was changed while this thread
        //was being executed, there would be problems. So Java/Android forces final
        mActivity.runOnUiThread(new Runnable(){public void run(){
            TextView t = (TextView)mActivity.findViewById(R.id.connect_flag);
            t.setText(holder);
        }});
    }
}
