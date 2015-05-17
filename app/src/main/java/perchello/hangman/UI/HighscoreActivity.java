package perchello.hangman.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.List;

import perchello.hangman.Adapters.HighscoreAdapter;
import perchello.hangman.Model.Highscore;
import perchello.hangman.Model.UserInfo;
import perchello.hangman.ParseConstants;
import perchello.hangman.R;


public class HighscoreActivity extends Activity {
    private static final String TAG = HighscoreActivity.class.getSimpleName();
    private TextView mHighscoreTextView;
    private RecyclerView mRecyclerView;
    private int [] mScores;
    private String [] mUsernames;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        mHighscoreTextView = (TextView) findViewById(R.id.highscoreTextView);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mProgressBar = (ProgressBar) findViewById(R.id.highscoreProgress);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HighscoreActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mHighscoreTextView.setTypeface(typeface);
        if (isNetworkAvailable()) {
            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.addDescendingOrder(ParseConstants.KEY_SCORE);
            mProgressBar.setVisibility(View.VISIBLE);
            query.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> users, ParseException e) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    if (e == null) {

                        int i = 0;
                        mUsernames = new String[users.size()];
                        mScores = new int[users.size()];

                        for (ParseUser user : users) {
                            mUsernames[i] = user.getUsername();
                            mScores[i] = (int) user.get(ParseConstants.KEY_SCORE);
                            i++;


                        }


                        HighscoreAdapter adapter = new HighscoreAdapter(HighscoreActivity.this, mUsernames, mScores);
                        mRecyclerView.setAdapter(adapter);
                    } else {
                        Log.e(TAG, e.getMessage());
                    }
                }
            });
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(HighscoreActivity.this);
            builder.setMessage("Please make sure you have internet connection")
                    .setTitle("Oops!")
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }




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
