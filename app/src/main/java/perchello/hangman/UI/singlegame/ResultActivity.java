package perchello.hangman.UI.singlegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import perchello.hangman.Model.UserInfo;
import perchello.hangman.R;
import perchello.hangman.UI.ChooseGameModeActivity;

public class ResultActivity extends Activity {
    private Button mTryAgain;
    private Button mMainMenu;
    private TextView mResult;
    private String mUsername;
    private int mScore;
    private TextView mUsernameView;
    private TextView mScoreView;
    private UserInfo mUserInfo;
    private Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mTryAgain = (Button) findViewById(R.id.tryAgainButton);
        mMainMenu = (Button) findViewById(R.id.mainMenuButton);
        mResult = (TextView) findViewById(R.id.resultTextView);
        Intent intent = getIntent();
        mResult.setText(intent.getStringExtra("result"));
        mUsername = intent.getStringExtra("username");
        mUsernameView = (TextView) findViewById(R.id.usernameTextView);
        mScoreView = (TextView) findViewById(R.id.scoreTextView);
        mUserInfo = new UserInfo(intent.getStringExtra("username"), mContext);
        mUsername = mUserInfo.getName();
        mScore = mUserInfo.getScore(mUsername);
        mUsernameView.setText("Welcome " + mUsername + "!  ");
        mScoreView.setText("Score: " + mScore + "  ");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mUsernameView.setTypeface(typeface);
        mScoreView.setTypeface(typeface);
        mResult.setTypeface(typeface);
        mTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseCat();
            }
        });
        mMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMainMenu();
            }
        });

    }
    public void chooseCat () {
        Intent intent = new Intent (this, chooseCategoryActivity.class);
        intent.putExtra("username", mUsername);
        startActivity(intent);
        finish();

    }
    public void goMainMenu () {
        Intent intent = new Intent (this, ChooseGameModeActivity.class);
        intent.putExtra("username", mUsername);
        startActivity(intent);
        finish();

    }

}
