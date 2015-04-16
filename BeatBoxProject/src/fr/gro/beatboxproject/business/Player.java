package fr.gro.beatboxproject.business;

import java.io.IOException;

import android.media.MediaPlayer;

public class Player {

    private MediaPlayer mPlayer = null;
    private String filename = null;
    private boolean isStarted = false;
    private Thread playerThread = null;

    public Player(MediaPlayer mplayer, String filename) {
        super();
        this.mPlayer = mplayer;
        this.filename = filename;
    }

    private void startPlay() {
        mPlayer = new MediaPlayer();

        mPlayer.stop();
        mPlayer.reset();

        try {
            isStarted = true;
            mPlayer.setDataSource(filename);
            mPlayer.prepare();

            playerThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (isStarted) {
                        mPlayer.start();
                    }

                }

            });

            playerThread.start();

        } catch (IllegalArgumentException e) {
            isStarted = false;
            e.printStackTrace();
        } catch (IllegalStateException e) {
            isStarted = false;
            e.printStackTrace();
        } catch (SecurityException e) {
            isStarted = false;
            e.printStackTrace();
        } catch (IOException e) {
            isStarted = false;
            e.printStackTrace();
        }

    }

    private void stopPlay() {
        if (playerThread != null) {
            playerThread.interrupt();
            playerThread = null;
        }
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.reset();
        }
        isStarted = false;
    }

    private void resetPlayer() {
        stopPlay();
        mPlayer.release();
        mPlayer.reset();
    }

    public void start() {
        startPlay();
    }

    public void stop() {
        stopPlay();
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void reset() {
        resetPlayer();
    }
}
