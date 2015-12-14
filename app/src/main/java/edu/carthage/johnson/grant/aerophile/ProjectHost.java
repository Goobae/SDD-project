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


public class ProjectHost extends ActionBarActivity{

    private String baseFilePath;
    private String currentFilePath;
    private File currentFile;
    private ListView listView;
    private FileBrowser fileBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_browser);
        listView = (ListView) findViewById(R.id.list);
        fileBrowser = new FileBrowser();

        baseFilePath = Environment.getExternalStorageDirectory().getPath();

        if(getIntent().hasExtra("BasePath"))
        {
            baseFilePath = getIntent().getStringExtra("BasePath");
        }

        if(getIntent().hasExtra("ProjectTitle"))
        {
            setTitle(getIntent().getStringExtra("ProjectTitle"));
        }

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

        if(id == R.id.qr_code) {
            //THIS IS WHERE YOU INSERT YOUR IP
            String qrInputText = "FUCK YOU HASBRO";

            Intent printOut = new Intent(this, QRGenerated.class);
            printOut.putExtra(qrInputText, "toGen");
            startActivity(printOut);
        }
        return super.onOptionsItemSelected(item);
    }
}
