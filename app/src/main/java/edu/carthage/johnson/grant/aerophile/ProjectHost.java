package edu.carthage.johnson.grant.aerophile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProjectHost extends ActionBarActivity{

    private String baseFilePath;
    private String currentFilePath;
    private ListView listView;
    private File prevFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_host);

        listView = (ListView) findViewById(R.id.list);

        baseFilePath = "/";
        currentFilePath = baseFilePath;
        if(getIntent().hasExtra("FilePath"))
        {
            baseFilePath = getIntent().getStringExtra("FilePath");
        }

        if(getIntent().hasExtra("ProjectTitle"))
        {
            setTitle(getIntent().getStringExtra("ProjectTitle"));
        }
        PopulateList(baseFilePath);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fileName = (String) ((ListView)parent).getAdapter().getItem(position);

                if(currentFilePath.endsWith(File.separator))
                {
                    fileName = currentFilePath + fileName;
                }
                else
                {
                    fileName = currentFilePath + File.separator + fileName;
                }

                if(new File(fileName).isDirectory())
                {
                    PopulateList(fileName);
                }
            }
        });
    }

    private void PopulateList(String path)
    {

        ArrayList files = new ArrayList();
        File directory = new File(path);

        String[] list = directory.list();
        if(list != null)
        {
            for(String file : list)
            {
                if(!file.startsWith("."))
                {
                    files.add(file);
                }
            }
        }
        Collections.sort(files);

        ArrayAdapter adapter = new CustomFileAdapter(this, files);
       // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, files);
        listView.setAdapter(adapter);
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

        return super.onOptionsItemSelected(item);
    }
}
