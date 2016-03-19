package com.stephenk.audionotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static DBhandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler = new DBhandler(this, null, null, 1);

        // Opens dialog fragment to save file
        Button mBtn_Done = (Button) findViewById(R.id.btn_Done);
        mBtn_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveFragment saveFragment = new SaveFragment();
                saveFragment.show(getFragmentManager(), "Save Fragment");
            }
        });

        // Will add note will timestamp
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_addNew);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        //Temp change so you can view saved files
        if (id == R.id.load_files) {
            Intent LoadFilesIntent = new Intent(this, BrowseAudio.class);
            startActivity(LoadFilesIntent);

        }

        return super.onOptionsItemSelected(item);
    }

    public void onRecordButtonClick(View view) {
        Button button = (Button)findViewById(R.id.recordButton);
        // attempt to record or stop recording (success not granted)
        if (AudioRecordingHandler.isRecording()) {
            AudioRecordingHandler.stopRecording();
        } else {
            AudioRecordingHandler.startRecording("test");
        }
        // based on audioRecordingHandler's state, updated the ui
        if (AudioRecordingHandler.isRecording()) {
            button.setText("Stop");
        } else {
            button.setText("Record");
        }
    }

    public void onPlayButtonClick(View view) {
        Button button = (Button)findViewById(R.id.playButton);
        // attempt to play or stop playing (success not granted)
        if (AudioRecordingHandler.isPlaying()) {
            AudioRecordingHandler.stopPlaying();
        } else {
            AudioRecordingHandler.startPlaying("test");
        }
        // based on audioRecordingHandler's state, updated the ui
        if (AudioRecordingHandler.isPlaying()) {
            button.setText("Pause");
        } else {
            button.setText("Play");
        }
    }
}
