package edu.carthage.johnson.grant.aerophile;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Grant on 12/13/2015.
 */
public class FileBrowser {
    public void PopulateList(Context ctx, String path, ListView listView)
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
        ArrayAdapter adapter = new CustomFileAdapter(ctx, files);
        listView.setAdapter(adapter);
    }
}
