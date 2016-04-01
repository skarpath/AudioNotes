package com.stephenk.audionotes;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Joseph on 3/19/2016.
 */
public class AudioRecordingHandler {
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;

    private static MediaRecorder mRecorder = null;
    private static MediaPlayer mPlayer = null;
    private static boolean recording = false;
    private static boolean playing = false;

    public static void makeSureDirectoryExists() {
        File makeDirectoryFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/AudioNotes/");
        makeDirectoryFile.mkdirs();
    }

    // PLAYBACKS
    public static void startPlaying(String audioFileName) {
        if (!playing) {
            playing = true;
            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFileName += "/AudioNotes/" + audioFileName + ".3gp";

            mPlayer = new MediaPlayer();
            try {
                mPlayer.setDataSource(mFileName);
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
                Log.e(LOG_TAG, e.getMessage());
                playing = false;
            }
        }
    }

    public static void stopPlaying() {
        if (playing) {
            playing = false;
            mPlayer.release();
            mPlayer = null;
        }
    }

    // RECORDINGS

    public static void startRecording(String audioFileName) {
        if (!recording) {
            recording = true;

            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFileName += "/AudioNotes/" + audioFileName + ".3gp";


            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(mFileName);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
                Log.e(LOG_TAG, e.getMessage());
                recording = false;
                return;
            }
            try {
                mRecorder.start();
            } catch (IllegalStateException e) {
                Log.e(LOG_TAG, "start() failed");
                Log.e(LOG_TAG, e.getMessage());
                recording = false;
            }
        }
    }

    public static void stopRecording() {
        if (recording) {
            recording = false;
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    // PUBLIC METHODS TO DETERMINE CURRENT STATE
    public static boolean isRecording() {
        return recording;
    }
    public static boolean isPlaying() {
        return playing;
    }
}
