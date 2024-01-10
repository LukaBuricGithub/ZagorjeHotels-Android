package hr.tvz.android.lukaburicproject.Miscellaneous;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class WebClientsAplikacija extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Ovo inicira Fresco
        Fresco.initialize(this);
    }
}
