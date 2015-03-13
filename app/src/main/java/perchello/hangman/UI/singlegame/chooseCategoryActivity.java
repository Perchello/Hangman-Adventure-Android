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

public class chooseCategoryActivity extends Activity {
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private int mScore;
    private TextView mUsernameView;
    private TextView mScoreView;
    private UserInfo mUserInfo;
    private String mUsername;
    private TextView mChooseCatTextView;
    private Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        mButton1 = (Button) findViewById(R.id.chooseAdvButton1);
        mButton2 = (Button) findViewById(R.id.chooseAdvButton2);
        mButton3 = (Button) findViewById(R.id.catButton3);
        mUsernameView = (TextView) findViewById(R.id.usernameTextView);
        mScoreView = (TextView) findViewById(R.id.scoreTextView);
        mChooseCatTextView = (TextView) findViewById(R.id.chooseAdvTextView);
        mUserInfo = new UserInfo(getIntent().getStringExtra("username"), mContext);
        mScore= mUserInfo.getScore(mUserInfo.getName());
        mUsername = mUserInfo.getName();
        mUsernameView.setText("Welcome " + mUsername + "!  ");
        mScoreView.setText("Score : " + mScore + "  ");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mUsernameView.setTypeface(typeface);
        mScoreView.setTypeface(typeface);
        mChooseCatTextView.setTypeface(typeface);

        final Intent intent = new Intent(this, GameActivity.class);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choose = "animals.txt";
                intent.putExtra("choose", choose);
                intent.putExtra("username", mUsername);
                startActivity(intent);
                finish();
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choose = "countries.txt";
                intent.putExtra("choose", choose);
                intent.putExtra("username", mUsername);
                startActivity(intent);
                finish();
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choose = "random.txt";
                intent.putExtra("choose", choose);
                intent.putExtra("username", mUsername);
                startActivity(intent);
                finish();
            }
        });
    }

}
