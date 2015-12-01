package edu.carthage.johnson.grant.aerophile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Grant on 11/30/2015.
 */
public class CustomFileAdapter extends ArrayAdapter<String>{
    Context mContext;

    public CustomFileAdapter(Context context, ArrayList<String> files){
        super(context, 0, files);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final File file = new File(getItem(position));

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_custom_file_listview, parent, false);
        }

        TextView projectName = (TextView) convertView.findViewById(R.id.fileNameTextView);
        TextView fileSize = (TextView) convertView.findViewById(R.id.fileSizeTextView);
        fileSize.setText(android.text.format.Formatter.formatShortFileSize(mContext, file.length()));
        ImageView imageView = (ImageView) convertView.findViewById(R.id.fileTypeImageView);
        if(!file.isDirectory())
        {
            imageView.setImageResource(R.drawable.ic_description);
            fileSize.setText(android.text.format.Formatter.formatShortFileSize(mContext, file.length()));
        }
        else
        {
            imageView.setImageResource(R.drawable.ic_action_folder_closed);
            fileSize.setText("");
        }

        projectName.setText(file.getName());

        return convertView;
    }

}
