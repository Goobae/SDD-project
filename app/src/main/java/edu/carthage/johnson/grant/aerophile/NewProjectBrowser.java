package edu.carthage.johnson.grant.aerophile;

import android.content.Intent;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;


public class NewProjectBrowser extends ActionBarActivity {

    private String baseFilePath;
    private String currentFilePath;
    private String projectName;
    private SavedProjects savedProjects;
    private File currentFile;
    private ListView listView;
    private FileBrowser fileBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_browser);


        if(getIntent().hasExtra("ProjectName"))
        {
            projectName = getIntent().getStringExtra("ProjectName");
        }

        if(getIntent().hasExtra("SavedProjects"));
        {
            savedProjects = getIntent().getParcelableExtra("SavedProjects");
        }

        listView = (ListView) findViewById(R.id.list);
        fileBrowser = new FileBrowser();

        baseFilePath = Environment.getExternalStorageDirectory().getPath();
        currentFilePath = baseFilePath;
        currentFile = new File(currentFilePath);

        fileBrowser.PopulateList(this, baseFilePath, listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fileName = (String) ((ListView)parent).getAdapter().getItem(position);
                String newFilePath;
                if(currentFilePath.endsWith(File.separator))
                {
                    newFilePath = currentFilePath + fileName;
                }
                else
                {
                    newFilePath  = currentFilePath + File.separator + fileName;
                }

                File newFile = new File(newFilePath);
                if(newFile.isDirectory())
                {
                    currentFilePath = newFilePath;
                    currentFile = newFile;
                    fileBrowser.PopulateList(parent.getContext(), currentFilePath, listView);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Intent moveToNewProj = new Intent(parent.getContext(), NewProjectActivity.class);
                String baseFilepath = (String) ((ListView) parent).getAdapter().getItem(position);
                if (currentFilePath.endsWith(File.separator)) {
                    baseFilepath = currentFilePath + baseFilepath;
                } else {
                    baseFilepath = currentFilePath + File.separator + baseFilepath;
                }
                moveToNewProj.putExtra("BasePath", baseFilepath);
                if(getIntent().hasExtra("ProjectName"))
                {
                    moveToNewProj.putExtra("ProjectName", projectName);
                }

                if(getIntent().hasExtra("SavedProjects"))
                {
                    moveToNewProj.putExtra("SavedProjects", (Parcelable) savedProjects);
                }

                startActivity(moveToNewProj);
                return false;

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_project_browser, menu);
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
