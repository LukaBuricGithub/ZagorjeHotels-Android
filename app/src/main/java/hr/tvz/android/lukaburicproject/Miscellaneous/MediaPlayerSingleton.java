package hr.tvz.android.lukaburicproject.Miscellaneous;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerSingleton {
    private static MediaPlayerSingleton instance;
    private MediaPlayer mediaPlayer;
    private boolean isInitialized = false;

    private MediaPlayerSingleton() {}

    public static MediaPlayerSingleton getInstance() {
        if (instance == null) {
            instance = new MediaPlayerSingleton();
        }
        return instance;
    }

    public void initializeMediaPlayer(Context context, int resourceId) {
        if (!isInitialized) {
            mediaPlayer = MediaPlayer.create(context, resourceId);
            mediaPlayer.setLooping(true);
            isInitialized = true;
        }
    }

    public void start() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isInitialized = false;
        }
    }

}
