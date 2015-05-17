package perchello.hangman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import perchello.hangman.UI.ChooseGameModeActivity;
import perchello.hangman.UI.GameStartActivity;


public class LoadingActivity extends Activity {
        private TextView mLoadingText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mLoadingText = (TextView) findViewById(R.id.loadingTextView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mLoadingText.setTypeface(typeface);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (isNetworkAvailable()) {

            if (!(boolean) currentUser.get(ParseConstants.KEY_OFFLINE)) {

                if (currentUser == null) {
                    navigateToLogin();
                } else {
                    currentUser.fetchInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
                            Log.i("Navigating to choosemod", "yes");
                            navigateToChooseGameMode();

                        }
                    });

                }
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoadingActivity.this);
                builder.setMessage("You played offline for a while. Do you want to update your record online?")
                        .setTitle("Network is available")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(LoadingActivity.this, UpdateActivity.class);
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                navigateToChooseGameMode();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
        else {
            if (currentUser == null) {
                navigateToLogin();
            } else {
                currentUser.put("playedOffline", true);
                currentUser.put(ParseConstants.KEY_SCORE, 0);
                navigateToChooseGameMode();
            }

        }
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, GameStartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void navigateToChooseGameMode() {
        Intent intent = new Intent(this, ChooseGameModeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo!=null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

}
