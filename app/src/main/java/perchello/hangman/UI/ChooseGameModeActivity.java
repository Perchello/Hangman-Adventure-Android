package perchello.hangman.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import perchello.hangman.Model.UserInfo;
import perchello.hangman.R;
import perchello.hangman.UI.adventure.ChooseAdventureActivity;
import perchello.hangman.UI.singlehangman.chooseCategoryActivity;


public class ChooseGameModeActivity extends Activity {
    private static final String TAG = ChooseGameModeActivity.class.getSimpleName();
    private Button mChooseModeButton1;
    private Button mChooseModeButton2;
    private TextView mUsernameView;
    private TextView mScoreView;
    private TextView mChooseModeTextView;
    private String mUsername;
    private UserInfo mUserInfo;
    private Context mContext = this;
    private int mScore;
    private Button mHighscoreButton;
    private TextView mLogoutTextView;
    private ParseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game_mode);

        mLogoutTextView = (TextView) findViewById(R.id.logoutTextView);
        mLogoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                navigateToLogin();
            }
        });
        mChooseModeButton1 = (Button) findViewById(R.id.chooseCatButton1);
        mChooseModeButton2 = (Button) findViewById(R.id.chooseCatButton2);
        mChooseModeTextView = (TextView) findViewById(R.id.chooseCatTextView);
        mHighscoreButton = (Button) findViewById(R.id.highscoreButon);
        mUsernameView = (TextView) findViewById(R.id.usernameTextView);
        mScoreView = (TextView) findViewById(R.id.scoreTextView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mUsernameView.setTypeface(typeface);
        mScoreView.setTypeface(typeface);
        mChooseModeTextView.setTypeface(typeface);

        mUserInfo = new UserInfo();
        mUsername = mUserInfo.getName();
        mScore = mUserInfo.getScore();
        mUsernameView.setText("Welcome " + mUsername + "!  ");
        mScoreView.setText("Score: " + mScore+ "  ");


        mChooseModeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ChooseAdventureActivity.class);
                intent.putExtra("username", mUsername);
                startActivity(intent);
            }
        });
        mChooseModeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, chooseCategoryActivity.class);
                intent.putExtra("username", mUsername);
                startActivity(intent);

            }
        });
        mHighscoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, HighscoreActivity.class);
                intent.putExtra("username", mUsername);
                startActivity(intent);

            }
        });
    }


    private void navigateToLogin() {
        Intent intent = new Intent(this, GameStartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
