package com.stephenk.audionotes;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;

public class BrowseAudio extends AppCompatActivity {


    //Temp data
    String[] items;
    ListView ListViewLoadNotes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_browse_audio);


        try
        {
        //Populate list view with temp data
        items = new String[] { "Vegetables","Fruits","Flower Buds","Legumes","Bulbs","Tubers" };

        //Assign list view note to layout
        ListViewLoadNotes= (ListView)findViewById(R.id.ListViewLoadNotes);




            //Create a new Adapter with passing ArrayList as 3rd parameter
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);

            // Set The Adapter
            ListViewLoadNotes.setAdapter(arrayAdapter);
        }
        catch(Exception e)
        {

        }






        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        */


        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        path += "/AudioNotes/";
        Log.d("Files", "Path: " + path);
        File f = new File(path);
        File file[] = f.listFiles();
        Log.d("Files", "Size: "+ file.length);
        for (int i=0; i < file.length; i++)
        {
            Log.d("Files", "FileName:" + file[i].getName());
        }


    }

}
