package com.stephenk.audionotes;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Joseph on 3/19/2016.
 */
public class AudioRecordingHandler {
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;

    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;

    // PLAYBACKS
    public void startPlaying(String audioFileName) {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/"+ audioFileName + ".3gp";

        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    // RECORDINGS

    public void startRecording(String audioFileName) {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/"+ audioFileName + ".3gp";


        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
}
