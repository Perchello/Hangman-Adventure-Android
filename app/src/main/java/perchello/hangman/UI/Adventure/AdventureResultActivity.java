package perchello.hangman.UI.adventure;

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


public class AdventureResultActivity extends Activity {

    private Button mTryAgain;
    private Button mMainMenu;
    private Button mNextWord;
    private TextView mResult;
    private String mUsername;
    private int mScore;
    private TextView mUsernameView;
    private TextView mScoreView;
    private UserInfo mUserInfo;
    private Context mContext = this;
    private String mChoose;
    private int mAdvNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_result);

        mTryAgain = (Button) findViewById(R.id.tryAgainButton);
        mMainMenu = (Button) findViewById(R.id.mainMenuButton);
        mResult = (TextView) findViewById(R.id.resultTextView);
        mNextWord = (Button) findViewById(R.id.nextWordButton);
        Intent intent = getIntent();
        mChoose = intent.getStringExtra("choose");
        mResult.setText(intent.getStringExtra("result"));
        mUsername = intent.getStringExtra("username");
        mAdvNumber = intent.getIntExtra("advNumber", 1);
        if (mAdvNumber == 0){
            mNextWord.setVisibility(View.GONE);
        }
        mUsernameView = (TextView) findViewById(R.id.usernameTextView);
        mScoreView = (TextView) findViewById(R.id.scoreTextView);
        mUserInfo = new UserInfo();
        mUsername = mUserInfo.getName();
        mScore = mUserInfo.getScore();
        mUsernameView.setText("Welcome " + mUsername + "!  ");
        mScoreView.setText("Score: " + mScore + "  ");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mUsernameView.setTypeface(typeface);
        mScoreView.setTypeface(typeface);
        mResult.setTypeface(typeface);
        mTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAdv();
            }
        });
        mMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMainMenu();
            }
        });
        mNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextWord();
            }
        });

    }
    public void chooseAdv () {
        Intent intent = new Intent (this, ChooseAdventureActivity.class);
        intent.putExtra("username", mUsername);
        startActivity(intent);
        finish();

    }
    public void goMainMenu () {
        Intent intent = new Intent (this, ChooseGameModeActivity.class);
        intent.putExtra("username", mUsername);
        finish();
        startActivity(intent);


    }
    public  void nextWord() {
        Intent intent = new Intent (this, AdventureGameActivity.class);
        intent.putExtra("username", mUsername);
        intent.putExtra("choose", mChoose);
        intent.putExtra("advNumber", mAdvNumber);
        finish();
        startActivity(intent);

    }

}
