package edu.carthage.johnson.grant.aerophile;

import java.io.IOException;

//package com.exercise.AndroidClient;

        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.net.Socket;
        import java.net.UnknownHostException;

        import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
import edu.carthage.johnson.grant.aerophile.ServerProxyThread;

public class AndroidClient extends Activity {
    EditText textOut;
    TextView textIn;
    TextView viewIp;
    private Context ctx = this;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_test);

        textOut = (EditText)findViewById(R.id.textout);
        //Button buttonSend = (Button)findViewById(R.id.send);
        textIn = (TextView)findViewById(R.id.textin);
        //viewIp = (TextView)findViewById(R.id.textView2);

    }

        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            new ServerProxyThread().execute(textOut.getText().toString());

            //Thread thread = new Thread(spt.doInBackground("Something something"));

        }
}