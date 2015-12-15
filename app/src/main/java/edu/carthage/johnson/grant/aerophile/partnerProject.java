package edu.carthage.johnson.grant.aerophile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class partnerProject extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Partner a Project");
        setContentView(R.layout.activity_partner_project);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project_menu, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.qr_code, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.qr_code) {
            IntentIntegrator integrator = new IntentIntegrator(this);

            integrator.initiateScan();
        }



        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {

            // handle scan result
            String re = scanResult.getContents();
            if (re != null) {
                Log.d("code", re);
                Toast.makeText(partnerProject.this, re, Toast.LENGTH_SHORT).show();
            } else {
                // else continue with any other code you need in the metho
                finish();
            }
        }

    }

}
