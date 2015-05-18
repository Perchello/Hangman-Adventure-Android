package perchello.hangman.UI.adventure;

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

import com.parse.ParseUser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import perchello.hangman.Model.UserInfo;
import perchello.hangman.ParseConstants;
import perchello.hangman.R;


public class ChooseAdventureActivity extends Activity {
    private Button mChooseAdv1;
    private Button mChooseAdv2;
    private Button mChooseAdv3;
    private Button mChooseAdv4;
    private Button mChooseAdv5;
    private Button mChooseAdv6;
    private Button mChooseAdv7;
    private TextView mUsernameView;
    private TextView mScoreView;
    private TextView mResultAdv1;
    private TextView mResultAdv2;
    private TextView mResultAdv3;
    private TextView mResultAdv4;
    private TextView mResultAdv5;
    private TextView mResultAdv6;
    private TextView mResultAdv7;
    private TextView mChooseAdvTextView;
    private UserInfo mUserInfo;
    private Context mContext = this;
    private String mUsername;
    private int sizeAdv1;
    private int sizeAdv2;
    private int sizeAdv3;
    private int sizeAdv4;
    private int sizeAdv5;
    private int sizeAdv6;
    private int sizeAdv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_adventure);
        mChooseAdv1 = (Button) findViewById(R.id.chooseCatButton1);
        mChooseAdv2 = (Button) findViewById(R.id.chooseCatButton2);
        mChooseAdv3 = (Button) findViewById(R.id.chooseCatButton3);
        mChooseAdv4 = (Button) findViewById(R.id.chooseCatButton4);
        mChooseAdv5 = (Button) findViewById(R.id.chooseCatButton5);
        mChooseAdv6 = (Button) findViewById(R.id.chooseCatButton6);
        mChooseAdv7 = (Button) findViewById(R.id.chooseCatButton7);
        mUsernameView = (TextView) findViewById(R.id.usernameTextView);
        mScoreView = (TextView) findViewById(R.id.scoreTextView);
        mResultAdv1 = (TextView) findViewById(R.id.resultAdvTextView1);
        mResultAdv2 = (TextView) findViewById(R.id.resultAdvTextView2);
        mResultAdv3 = (TextView) findViewById(R.id.resultAdvTextView3);
        mResultAdv4 = (TextView) findViewById(R.id.resultAdvTextView4);
        mResultAdv5 = (TextView) findViewById(R.id.resultAdvTextView5);
        mResultAdv6 = (TextView) findViewById(R.id.resultAdvTextView6);
        mResultAdv7 = (TextView) findViewById(R.id.resultAdvTextView7);
        mChooseAdvTextView = (TextView) findViewById(R.id.chooseCatTextView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mUsernameView.setTypeface(typeface);
        mScoreView.setTypeface(typeface);
        mChooseAdvTextView.setTypeface(typeface);
        mResultAdv1.setTypeface(typeface);
        mResultAdv2.setTypeface(typeface);
        mResultAdv3.setTypeface(typeface);
        mResultAdv4.setTypeface(typeface);
        mResultAdv5.setTypeface(typeface);
        mResultAdv6.setTypeface(typeface);
        mResultAdv7.setTypeface(typeface);
        mUsername = getIntent().getStringExtra("username");
        mUserInfo = new UserInfo();
        mUsernameView.setText("Welcome " + mUsername + "!  ");
        mScoreView.setText("Score : " + mUserInfo.getScore() + "  ");
        sizeAdv1 = countLines (1);
        sizeAdv2 = countLines (2);
        sizeAdv3 = countLines (3);
        sizeAdv4 = countLines (4);
        sizeAdv5 = countLines (5);
        sizeAdv6 = countLines (6);
        sizeAdv7 = countLines (7);
        mResultAdv1.setText(mUserInfo.getAdvProgress(1) + "/"+ sizeAdv1 +" ");
        mResultAdv2.setText(mUserInfo.getAdvProgress(2) + "/"+ sizeAdv2 +" ");
        mResultAdv3.setText(mUserInfo.getAdvProgress(3) + "/"+ sizeAdv3 +" ");
        mResultAdv4.setText(mUserInfo.getAdvProgress(4) + "/"+ sizeAdv4 +" ");
        mResultAdv5.setText(mUserInfo.getAdvProgress(5) + "/"+ sizeAdv5 +" ");
        mResultAdv6.setText(mUserInfo.getAdvProgress(6) + "/"+ sizeAdv6 +" ");
        mResultAdv7.setText(mUserInfo.getAdvProgress(7) + "/"+ sizeAdv7 +" ");


        mChooseAdv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUserInfo.getAdvProgress(1) >= sizeAdv1) {
                    Toast.makeText(mContext, "You already finished this adventure", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(mContext, AdventureGameActivity.class);
                    intent.putExtra("username", mUsername);
                    intent.putExtra("advNumber", 1);
                    String choose = "egypt.txt";
                    intent.putExtra("choose", choose);
                    finish();
                    startActivity(intent);
                }
            }
        });
        mChooseAdv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUserInfo.getAdvProgress(1) < sizeAdv1){
                    Toast.makeText(mContext, "To unlock you need to finish Egypt adventure", Toast.LENGTH_LONG).show();
                }
                else if (mUserInfo.getAdvProgress(2)>=sizeAdv2){
                    Toast.makeText(mContext, "You already finished this adventure", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(mContext, AdventureGameActivity.class);
                    intent.putExtra("username", mUsername);
                    intent.putExtra("advNumber", 2);
                    String choose = "china.txt";
                    intent.putExtra("choose", choose);
                    finish();
                    startActivity(intent);
                }
            }
        });
        mChooseAdv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUserInfo.getAdvProgress(2) < sizeAdv2){
                    Toast.makeText(mContext, "To unlock you need to finish China adventure", Toast.LENGTH_LONG).show();
                }
                else if (mUserInfo.getAdvProgress(3)>=sizeAdv3){
                    Toast.makeText(mContext, "You already finished this adventure", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(mContext, AdventureGameActivity.class);
                    intent.putExtra("username", mUsername);
                    intent.putExtra("advNumber", 3);
                    String choose = "brazil.txt";
                    intent.putExtra("choose", choose);
                    finish();
                    startActivity(intent);
                }
            }
        });
        mChooseAdv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUserInfo.getAdvProgress(3) < sizeAdv3){
                    Toast.makeText(mContext, "To unlock you need to finish Brazil adventure", Toast.LENGTH_LONG).show();
                }
                else if (mUserInfo.getAdvProgress(4)>=sizeAdv4){
                    Toast.makeText(mContext, "You already finished this adventure", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(mContext, AdventureGameActivity.class);
                    intent.putExtra("username", mUsername);
                    intent.putExtra("advNumber", 4);
                    String choose = "mexico.txt";
                    intent.putExtra("choose", choose);
                    finish();
                    startActivity(intent);
                }
            }
        });
        mChooseAdv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUserInfo.getAdvProgress(4) < sizeAdv4){
                    Toast.makeText(mContext, "To unlock you need to finish Mexico adventure", Toast.LENGTH_LONG).show();
                }
                else if (mUserInfo.getAdvProgress(5)>=sizeAdv5){
                    Toast.makeText(mContext, "You already finished this adventure", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(mContext, AdventureGameActivity.class);
                    intent.putExtra("username", mUsername);
                    intent.putExtra("advNumber", 5);
                    String choose = "italy.txt";
                    intent.putExtra("choose", choose);
                    finish();
                    startActivity(intent);
                }
            }
        });
        mChooseAdv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUserInfo.getAdvProgress(5) < sizeAdv5){
                    Toast.makeText(mContext, "To unlock you need to finish Italy adventure", Toast.LENGTH_LONG).show();
                }
                else if (mUserInfo.getAdvProgress(6)>=sizeAdv6){
                    Toast.makeText(mContext, "You already finished this adventure", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(mContext, AdventureGameActivity.class);
                    intent.putExtra("username", mUsername);
                    intent.putExtra("advNumber", 6);
                    String choose = "spain.txt";
                    intent.putExtra("choose", choose);
                    finish();
                    startActivity(intent);
                }
            }
        });
        mChooseAdv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUserInfo.getAdvProgress(6) < sizeAdv6){
                    Toast.makeText(mContext, "To unlock you need to finish Spain adventure", Toast.LENGTH_LONG).show();
                }
                else if (mUserInfo.getAdvProgress(7)>=sizeAdv7){
                    Toast.makeText(mContext, "You already finished this adventure", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(mContext, AdventureGameActivity.class);
                    intent.putExtra("username", mUsername);
                    intent.putExtra("advNumber", 7);
                    String choose = "canada.txt";
                    intent.putExtra("choose", choose);
                    finish();
                    startActivity(intent);
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
                else if (advNumber==3){
                    fileOpen = assetManager.open("brazil.txt");
                }
                else if (advNumber==4){
                    fileOpen = assetManager.open("mexico.txt");
                }
                else if (advNumber==5){
                    fileOpen = assetManager.open("italy.txt");
                }
                else if (advNumber==6){
                    fileOpen = assetManager.open("spain.txt");
                }
                else if (advNumber==7){
                    fileOpen = assetManager.open("canada.txt");
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
