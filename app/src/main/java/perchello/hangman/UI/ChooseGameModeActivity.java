package perchello.hangman.UI;

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
import perchello.hangman.UI.adventure.ChooseAdventureActivity;
import perchello.hangman.UI.singlegame.chooseCategoryActivity;


public class ChooseGameModeActivity extends Activity {
    private Button mChooseModeButton1;
    private Button mChooseModeButton2;
    private TextView mUsernameView;
    private TextView mScoreView;
    private TextView mChooseModeTextView;
    private String mUsername;
    private UserInfo mUserInfo;
    private Context mContext = this;
    private int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game_mode);
        mChooseModeButton1 = (Button) findViewById(R.id.chooseAdvButton1);
        mChooseModeButton2 = (Button) findViewById(R.id.chooseAdvButton2);
        mChooseModeTextView = (TextView) findViewById(R.id.chooseAdvTextView);
        mUsernameView = (TextView) findViewById(R.id.usernameTextView);
        mScoreView = (TextView) findViewById(R.id.scoreTextView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mUsernameView.setTypeface(typeface);
        mScoreView.setTypeface(typeface);
        mChooseModeTextView.setTypeface(typeface);
        Intent intentget = getIntent();
        mUserInfo = new UserInfo(intentget.getStringExtra("username"), mContext);
        mUserInfo.updateDataVersion();
        mUserInfo.setName(intentget.getStringExtra("username"));
        mUsername = mUserInfo.getName();
        mScore = mUserInfo.getScore(mUsername);
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
    }

}
