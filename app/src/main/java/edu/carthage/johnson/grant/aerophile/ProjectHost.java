package edu.carthage.johnson.grant.aerophile;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProjectHost extends ActionBarActivity{

    private String baseFilePath;
    private String currentFilePath;
    private File currentFile;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_host);
        listView = (ListView) findViewById(R.id.list);

        baseFilePath = Environment.getExternalStorageDirectory().getPath();
        currentFilePath = baseFilePath;
        currentFile = new File(currentFilePath);

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
                String newFilePath;
                if(currentFilePath.endsWith(File.separator))
                {
                    newFilePath = currentFilePath + fileName;
                }
                else
                {
                    newFilePath  = currentFilePath + File.separator + fileName;
                }

                File newFile = new File(fileName);
                boolean canread = newFile.canRead();
                boolean isdir = newFile.isDirectory();

                boolean canwrite = newFile.canWrite();
                boolean exists = newFile.exists();
                boolean isfile = newFile.isFile();

                String path = "./DCIM/";
                File testFile = new File(newFilePath);
                boolean canread2 = testFile.canRead();
                boolean isdir2 = testFile.isDirectory();

                boolean canwrite2 = testFile.canWrite();
                boolean exists2 = testFile.exists();
                boolean isfile2 = testFile.isFile();
                File[] arr = newFile.listFiles();
                if(testFile.isDirectory())
                {
                    currentFilePath = newFilePath;
                    currentFile = newFile;
                    PopulateList(currentFilePath);
                }
            }
        });
    }

    private void PopulateList(String path)
    {

        ArrayList files = new ArrayList();
        File directory = new File(path);
        directory.setReadable(true);
        boolean readable = directory.canRead();
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
    public void onBackPressed()
    {
        try {
            if (!currentFilePath.equals(baseFilePath)) {
                String parentPath = currentFile.getCanonicalFile().getParentFile().getCanonicalPath();

                PopulateList(parentPath);
                currentFilePath = parentPath;
                currentFile = new File(currentFilePath);
            } else {
                super.onBackPressed();
            }
        }
        catch (Exception e)
        {
            Toast toast = new Toast(this);
            toast.setText(e.getMessage());
            toast.show();
        }
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
