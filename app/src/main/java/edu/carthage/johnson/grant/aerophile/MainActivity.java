package edu.carthage.johnson.grant.aerophile;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void HostProject(View view)
    {
        Intent moveToHost = new Intent(this, HostActivity.class);
        startActivity(moveToHost);
    }

    public void PartnerProject(View view)
    {
        Intent moveToPartner = new Intent(this, partnerProject.class);
        startActivity(moveToPartner);
    }
}
