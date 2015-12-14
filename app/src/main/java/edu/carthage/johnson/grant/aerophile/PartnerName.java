package edu.carthage.johnson.grant.aerophile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class PartnerName extends ActionBarActivity {

    private Context ctx = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_name);
        Project project = new Project();
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project);
        CustomProjectAdapter adapter = new CustomProjectAdapter(getApplicationContext(), projects, ctx );
        ListView listView = (ListView) findViewById(R.id.ProjectListView);
        listView.setAdapter(adapter);
    }

    public void onConnectButton()
    {
        //do something
    }

}
