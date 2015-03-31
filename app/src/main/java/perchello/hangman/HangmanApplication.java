package perchello.hangman;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Perchello on 30/03/2015.
 */
public class HangmanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "Rga9eyQxxLCEdzZHRllcXztwnvVFp2MsafKdFSN1", "uriLu4BOnlIGiDhTqAoICVqfIARLoeD5bzuHk7ot");
    }
}
