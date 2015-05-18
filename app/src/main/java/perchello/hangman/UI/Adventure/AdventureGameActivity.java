package perchello.hangman.UI.adventure;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import perchello.hangman.Model.HangmanGameLogic;
import perchello.hangman.Model.UserInfo;
import perchello.hangman.ParseConstants;
import perchello.hangman.R;
import perchello.hangman.UpdateActivity;


public class AdventureGameActivity extends Activity implements View.OnClickListener {

    private TextView mTriesTextView;
    private TextView mProgressTextView;
    private HangmanGameLogic mHangmanGameLogic;
    private TextView mUsernameView;
    private TextView mScoreView;
    private UserInfo mUserInfo;
    private TextView zTextView;
    private TextView xTextView;
    private TextView cTextView;
    private TextView vTextView;
    private TextView bTextView;
    private TextView nTextView;
    private TextView mTextView;
    private TextView aTextView;
    private TextView sTextView;
    private TextView dTextView;
    private TextView fTextView;
    private TextView gTextView;
    private TextView hTextView;
    private TextView jTextView;
    private TextView kTextView;
    private TextView lTextView;
    private TextView qTextView;
    private TextView wTextView;
    private TextView eTextView;
    private TextView rTextView;
    private TextView tTextView;
    private TextView yTextView;
    private TextView uTextView;
    private TextView iTextView;
    private TextView oTextView;
    private TextView pTextView;
    private String[] mWords;
    int mWordNumber;
    private Context mContext = this;
    private String mUsername;
    private int mScore;
    private ImageView mHeroImageView;
    private ImageView mEvilGuyImageView;
    private int mAdvNumber;
    private String mChoose;
    private int mCountLines;
    private ParseUser mCurrentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        AssetManager assetManager = getAssets();
        Bundle bundle = new Bundle();
        mUsername = intent.getStringExtra("username");
        mAdvNumber = intent.getIntExtra("advNumber", 1);
        mChoose = intent.getStringExtra("choose");
        mCurrentUser = ParseUser.getCurrentUser();
        mUserInfo = new UserInfo();
        if ((bundle != null) && (bundle.getSerializable("starttime") != null)) {
            mHangmanGameLogic.setHits(bundle.getString("mHits"));
            mHangmanGameLogic.setMisses(bundle.getString("mMisses"));
            mHangmanGameLogic.setGameName(bundle.getString("mGameNAme"));
        }


        InputStream fileOpen;
        try {

            fileOpen = assetManager.open(mChoose);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(fileOpen));
            StringBuilder wordArrayBuilder = new StringBuilder();
            String test = buffReader.readLine();
            while (test != null) {
                if (test != "") {
                    wordArrayBuilder.append(test + "_");
                    mCountLines++;
                }
                test = buffReader.readLine();

            }
            mWords = wordArrayBuilder.toString().split("_");
            Log.d ("Word 1 is ", mWords[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Advdone", mUserInfo.getAdvDone(mAdvNumber));
        mWordNumber = new Random().nextInt(mWords.length);
        while (mWords[mWordNumber] == "" || mUserInfo.getAdvDone(mAdvNumber).toLowerCase().contains(mWords[mWordNumber].toLowerCase())) {
            Log.d ("Word Number is ", String.valueOf(mWordNumber));
            mWordNumber = new Random().nextInt(mWords.length);
        }
        mUsernameView = (TextView) findViewById(R.id.usernameTextView);
        mScoreView = (TextView) findViewById(R.id.scoreTextView);
        mHeroImageView = (ImageView) findViewById(R.id.heroImageView);
        mEvilGuyImageView = (ImageView) findViewById(R.id.evilGuyImageView);


        mUsernameView.setText("Welcome " + mUsername + "!  ");
        mScoreView.setText("Score: " + mUserInfo.getScore() + "  ");

        mHangmanGameLogic = new HangmanGameLogic(mWords[mWordNumber].toLowerCase());

        mTriesTextView = (TextView) findViewById(R.id.triesTextView);
        mProgressTextView = (TextView) findViewById(R.id.progressTextView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mUsernameView.setTypeface(typeface);
        mScoreView.setTypeface(typeface);
        mProgressTextView.setTypeface(typeface);
        mTriesTextView.setTypeface(typeface);


        zTextView = (TextView) findViewById(R.id.zTextView);
        zTextView.setOnClickListener(this);
        xTextView = (TextView) findViewById(R.id.xTextView);
        xTextView.setOnClickListener(this);
        cTextView = (TextView) findViewById(R.id.cTextView);
        cTextView.setOnClickListener(this);
        vTextView = (TextView) findViewById(R.id.vTextView);
        vTextView.setOnClickListener(this);
        bTextView = (TextView) findViewById(R.id.bTextView);
        bTextView.setOnClickListener(this);
        nTextView = (TextView) findViewById(R.id.nTextView);
        nTextView.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.mTextView);
        mTextView.setOnClickListener(this);
        aTextView = (TextView) findViewById(R.id.aTextView);
        aTextView.setOnClickListener(this);
        sTextView = (TextView) findViewById(R.id.sTextView);
        sTextView.setOnClickListener(this);
        dTextView = (TextView) findViewById(R.id.dTextView);
        dTextView.setOnClickListener(this);
        fTextView = (TextView) findViewById(R.id.fTextView);
        fTextView.setOnClickListener(this);
        gTextView = (TextView) findViewById(R.id.gTextView);
        gTextView.setOnClickListener(this);
        hTextView = (TextView) findViewById(R.id.hTextView);
        hTextView.setOnClickListener(this);
        jTextView = (TextView) findViewById(R.id.jTextView);
        jTextView.setOnClickListener(this);
        kTextView = (TextView) findViewById(R.id.kTextView);
        kTextView.setOnClickListener(this);
        lTextView = (TextView) findViewById(R.id.lTextView);
        lTextView.setOnClickListener(this);
        qTextView = (TextView) findViewById(R.id.qTextView);
        qTextView.setOnClickListener(this);
        wTextView = (TextView) findViewById(R.id.wTextView);
        wTextView.setOnClickListener(this);
        eTextView = (TextView) findViewById(R.id.eTextView);
        eTextView.setOnClickListener(this);
        rTextView = (TextView) findViewById(R.id.rTextView);
        rTextView.setOnClickListener(this);
        tTextView = (TextView) findViewById(R.id.tTextView);
        tTextView.setOnClickListener(this);
        yTextView = (TextView) findViewById(R.id.yTextView);
        yTextView.setOnClickListener(this);
        uTextView = (TextView) findViewById(R.id.uTextView);
        uTextView.setOnClickListener(this);
        iTextView = (TextView) findViewById(R.id.iTextView);
        iTextView.setOnClickListener(this);
        oTextView = (TextView) findViewById(R.id.oTextView);
        oTextView.setOnClickListener(this);
        pTextView = (TextView) findViewById(R.id.pTextView);
        pTextView.setOnClickListener(this);


        mTriesTextView.setText("Tries left: \n\n" + mHangmanGameLogic.getTries());
        mProgressTextView.setText("Your progress: \n\n " + mHangmanGameLogic.checkProgress());
        mHangmanGameLogic.setContext(getApplicationContext());


    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView) v;
        char input = textView.getText().toString().toLowerCase().charAt(0);

        mHangmanGameLogic.checkGuess(input);
        changePicture();
        String result = mHangmanGameLogic.getHits();
        if (result.indexOf(input) >= 0) {
            textView.setTextColor(Color.parseColor("#fffffd09"));
        } else {
            textView.setTextColor(Color.parseColor("#ffff514e"));
        }
        mTriesTextView.setText("Tries left: \n\n" + mHangmanGameLogic.getTries() + " ");
        mProgressTextView.setText("Your progress: \n\n " + mHangmanGameLogic.checkProgress());
        result();
    }

    public void result() {
        if (mHangmanGameLogic.compareMisses()) {
            Intent intent = new Intent(this, AdventureResultActivity.class);
            int adventureWordsLeft = mCountLines-mUserInfo.getAdvProgress(mAdvNumber);
            String result = ("Game over. You lost, " + mUsername + ". \n\n" +" Guess " + adventureWordsLeft + " words to open next adventure!");

            intent.putExtra("result", result);
            intent.putExtra("username", mUsername);
            intent.putExtra("advNumber", mAdvNumber);
            intent.putExtra("choose", mChoose);
            finish();
            startActivity(intent);


        }
        if (mHangmanGameLogic.compareAnswerProg()) {


            int adventureWordsLeft = mCountLines-mUserInfo.getAdvProgress(mAdvNumber);
            String result="";

            if (adventureWordsLeft<=0){
                result = ("Good work " + mUsername + " you solved all words in this adventure! \n\n Go on and try next one!");
                mAdvNumber=0;
            }
            else{
                result = ("Good work " + mUsername + " you solved it! \n\n Guess " + adventureWordsLeft + " words to open next adventure!");
            }

            final Intent intent = new Intent(this, AdventureResultActivity.class);

            intent.putExtra("username", mUsername);
            intent.putExtra("choose", mChoose);

            intent.putExtra("advNumber", mAdvNumber);
            intent.putExtra("result", result);

            if (isNetworkAvailable()) {
                if ((boolean) mCurrentUser.get(ParseConstants.KEY_OFFLINE)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AdventureGameActivity.this);
                    builder.setMessage("You played offline for a while. Do you want to update your record online?")
                            .setTitle("Network is available")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent1 = new Intent(AdventureGameActivity.this, UpdateActivity.class);
                                    finish();
                                    startActivity(intent1);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mUserInfo.setAdvScoreOffline(mHangmanGameLogic.getGameName(), mAdvNumber, mHangmanGameLogic.getHits().length());
                                    finish();
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    mUserInfo.setAdvScoreOnline(mHangmanGameLogic.getGameName(), mAdvNumber, mHangmanGameLogic.getHits().length());
                    startActivity(intent);

                }
            }
            else {
                mUserInfo.setAdvScoreOffline(mHangmanGameLogic.getGameName(), mAdvNumber, mHangmanGameLogic.getHits().length());
                startActivity(intent);
            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString("mHits", mHangmanGameLogic.getHits());
        state.putString("mMisses", mHangmanGameLogic.getMisses());
        state.putString("mGameName", mHangmanGameLogic.getGameName());
    }



    private void changePicture() {
        /*if (mGame.getMisses().length() > 0 && mGame.getMisses().length() <= 6) {
            int pictureId = mContext.getResources().getIdentifier("palach" + mGame.getMisses().length(), "drawable", mContext.getPackageName());
            mEvilGuyImageView.setImageDrawable(getResources().getDrawable(pictureId));
        }
        int check = 0;
        for (int i = 0; i < mGame.checkProgress().length(); i++) {
            if (mGame.checkProgress().charAt(i) == '-') {
                check++;
            }
        }
        Log.d("Check percentage ", (check * 5 / mGame.checkProgress().length()) + "");
        int goodGuyPic = check * 5 / mGame.checkProgress().length();
        if (goodGuyPic > 0 && goodGuyPic < 5) {
            int pictureId = mContext.getResources().getIdentifier("adventurer" + (5 - goodGuyPic), "drawable", mContext.getPackageName());
            mHeroImageView.setImageDrawable(getResources().getDrawable(pictureId));
        }*/


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
