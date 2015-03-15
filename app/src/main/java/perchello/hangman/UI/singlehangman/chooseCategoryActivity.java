package perchello.hangman.UI.singlehangman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import perchello.hangman.Model.UserInfo;
import perchello.hangman.R;

public class chooseCategoryActivity extends Activity {
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mAdvCatButton1;
    private Button mAdvCatButton2;
    private int mScore;
    private TextView mUsernameView;
    private TextView mScoreView;
    private UserInfo mUserInfo;
    private String mUsername;
    private TextView mChooseCatTextView;
    private Context mContext = this;
    private int sizeAdv1;
    private  int sizeAdv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        mButton1 = (Button) findViewById(R.id.chooseCatButton1);
        mButton2 = (Button) findViewById(R.id.chooseCatButton2);
        mButton3 = (Button) findViewById(R.id.chooseCatButton3);
        mAdvCatButton1 = (Button) findViewById(R.id.chooseAdvCatButton1);
        mAdvCatButton2 = (Button) findViewById(R.id.chooseAdvCatButton2);
        mUsernameView = (TextView) findViewById(R.id.usernameTextView);
        mScoreView = (TextView) findViewById(R.id.scoreTextView);
        mChooseCatTextView = (TextView) findViewById(R.id.chooseCatTextView);
        mUserInfo = new UserInfo(getIntent().getStringExtra("username"), mContext);
        mScore= mUserInfo.getScore(mUserInfo.getName());
        mUsername = mUserInfo.getName();
        mUsernameView.setText("Welcome " + mUsername + "!  ");
        mScoreView.setText("Score : " + mScore + "  ");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mUsernameView.setTypeface(typeface);
        mScoreView.setTypeface(typeface);
        mChooseCatTextView.setTypeface(typeface);
        sizeAdv1 = countLines (1);
        sizeAdv2 = countLines (2);
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
        mAdvCatButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUserInfo.getAdvProgress(1) < sizeAdv1){
                    Toast.makeText(mContext, "To unlock you need to finish Egypt adventure", Toast.LENGTH_LONG).show();
                }
                else {
                    String choose = "egypt.txt";
                    intent.putExtra("choose", choose);
                    intent.putExtra("username", mUsername);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mAdvCatButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUserInfo.getAdvProgress(2) < sizeAdv2){
                    Toast.makeText(mContext, "To unlock you need to finish China adventure", Toast.LENGTH_LONG).show();
                }
                else {

                    String choose = "china.txt";
                    intent.putExtra("choose", choose);
                    intent.putExtra("username", mUsername);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private int countLines(int advNumber){
        int result =0;
        AssetManager assetManager = getAssets();


        try {
            InputStream fileOpen = assetManager.open("egypt.txt");
            if (advNumber==1) {
                fileOpen = assetManager.open("egypt.txt");
            }
            else if (advNumber==2){
                fileOpen = assetManager.open("china.txt");
            }
            BufferedReader buffReader= new BufferedReader(new InputStreamReader(fileOpen));

            String test = buffReader.readLine();
            while (test != null) {
                if (test != "") {
                    result++;
                }
                test = buffReader.readLine();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
