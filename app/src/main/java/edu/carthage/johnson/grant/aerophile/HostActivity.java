package edu.carthage.johnson.grant.aerophile;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class HostActivity extends ActionBarActivity {

    private Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        ArrayList<Project> projects = new ArrayList<>();
        Project project = new Project();
        project.setPartnerCount(5);
        project.setProjectName("New Project");
        project.setProjectSize(50);
        projects.add(project);
        Project project2 = new Project();
        project2.setPartnerCount(3);
        project2.setProjectName("Frog on a bicycle");
        project2.setProjectSize(70);
        projects.add(project2);
        CustomProjectAdapter adapter = new CustomProjectAdapter(getApplicationContext(), projects, ctx );
        ListView listView = (ListView) findViewById(R.id.ProjectListView);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_host, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
