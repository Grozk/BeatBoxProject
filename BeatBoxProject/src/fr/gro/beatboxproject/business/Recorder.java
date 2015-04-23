package fr.gro.beatboxproject.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.media.MediaRecorder;
import android.os.Environment;

public class Recorder {

    private static final String AUDIO_RECORDER_FILE_EXT_3GP = ".3gp";
    private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".mp4";
    private static final String AUDIO_RECORDER_FOLDER = "BeatBoxProject";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy_HHmmss");

    private int currentFormat = 0;
    private int output_formats[] = {MediaRecorder.OutputFormat.MPEG_4,
            MediaRecorder.OutputFormat.THREE_GPP};
    private String file_exts[] = {AUDIO_RECORDER_FILE_EXT_MP4,
            AUDIO_RECORDER_FILE_EXT_3GP};

    private MediaRecorder mRecorder = null;
    private String idButton = null;
    private String filename = null;

    private boolean isRecording = false;

    public Recorder(MediaRecorder recorder, String idButton) {
        super();
        this.mRecorder = recorder;
        this.idButton = idButton;
        createFilename(null);
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();

        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(output_formats[currentFormat]);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile(this.filename);

        // recorder.setOnErrorListener(errorListener);
        // recorder.setOnInfoListener(infoListener);

        try {
            mRecorder.prepare();
            mRecorder.start();
            this.isRecording = true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (null != mRecorder) {
            mRecorder.stop();
            mRecorder.reset();
            mRecorder.release();
            this.isRecording = false;
            mRecorder = null;
        }
    }

    private void resetRecord() {
        File fic = new File(Environment.getExternalStorageDirectory()
                + File.separator + AUDIO_RECORDER_FOLDER + File.separator
                + idButton + file_exts[currentFormat]);
        if (fic.exists()) {
            fic.delete();
        }
    }

    private String createFilename(String date) {

        File fParent = new File(Environment.getExternalStorageDirectory()
                + File.separator + AUDIO_RECORDER_FOLDER);
        String nameFic = Environment.getExternalStorageDirectory()
                + File.separator + AUDIO_RECORDER_FOLDER + File.separator
                + idButton;
        if (date != null && date != "") {
            nameFic = nameFic.concat("_" + date);
        }
        nameFic = nameFic.concat(file_exts[currentFormat]);
        File fic = new File(nameFic);
        if (!fParent.exists()) {
            fParent.mkdir();
        }

        filename = fic.getAbsolutePath().toString();

        return filename;
    }

    public boolean copie() {
        boolean result;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(filename);
            Date currDate = new Date();

            fos = new FileOutputStream(createFilename(DATE_FORMAT.format(currDate)));

            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, nbLecture);
            }
            result = true;
        } catch (Exception e) {
            System.out.print(e);
            result = false;
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (Exception e) {
                System.out.print(e);
                result = false;
            }
        }
        return result;
    }

    public void start() {
        startRecording();
    }

    public void stop() {
        stopRecording();
    }

    public boolean isRecording() {
        return isRecording;
    }

    public String getFilenameRecord() {
        return this.filename;
    }

    public void reset() {
        resetRecord();
    }
    // recorder = new MediaRecorder();
    //
    // recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    // recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    // recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    // recorder.setOutputFile("/sdcard/sample.3gp");
    //
    // recorder.setOnErrorListener(errorListener);
    // recorder.setOnInfoListener(infoListener);
    //
    //
    // try
    // {
    // recorder.prepare();
    // recorder.start();
    // } catch (IllegalStateException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    //
    //
    // recorder.stop();
    // recorder.reset();
    // recorder.release();
    //
    // recorder = null;

}
