package edu.carthage.johnson.grant.aerophile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class NewProjectActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_project, menu);
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

    public void Browse(View view)
    {
        //opens file browser
        //does stuff
    }

    public void CreateProject(View view)
    {
        //create project
        TextView projectNameTV = (TextView) view.findViewById(R.id.projectNameTextView);
        String projectName = projectNameTV.getText().toString();
        TextView filePathTV = (TextView) view.findViewById(R.id.projectPathText);
        String filePath = filePathTV.getText().toString();
        Project newProject = new Project();

        //add to file
        //?????????
    }
}
