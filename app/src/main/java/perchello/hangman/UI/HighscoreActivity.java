package perchello.hangman.UI;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import perchello.hangman.Model.UserInfo;
import perchello.hangman.R;


public class HighscoreActivity extends Activity {
    private TextView mHighscoreTextView;
    private UserInfo mUserInfo;
    private TextView mHighscoreNameTextView;
    private TextView mHighscoreScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        mHighscoreTextView = (TextView) findViewById(R.id.highscoreTextView);
        mHighscoreNameTextView = (TextView) findViewById(R.id.highscoreNameTextView);
        mHighscoreScoreTextView = (TextView) findViewById(R.id.highscoreScoreTextView);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mHighscoreTextView.setTypeface(typeface);
        mHighscoreNameTextView.setTypeface(typeface);
        mHighscoreScoreTextView.setTypeface(typeface);
        mUserInfo = new UserInfo(getIntent().getStringExtra("username"), this);
        getHighscore();

    }
    private void getHighscore(){
        String score = mUserInfo.getHighscore();
        String [] highscoreList = score.split(" ");
        String names = "";
        String scores = "";
        int k = 1;
        for (int i = 0; i<highscoreList.length; i++){
            if (i%2==0){
                names+=k+". " + highscoreList[i]+" \n\n";
                k++;
            }
            if (i%2==1){
                scores += highscoreList[i]+" \n\n";
            }

        }

        for (int i = highscoreList.length; i<20; i++){
            if (i%2==0){
                names += k +". No player \n\n";
                k++;
            }
            if (i%2==1){
                scores+= "0 \n\n";
            }
        }
        mHighscoreNameTextView.setText(names);
        mHighscoreScoreTextView.setText(scores);



    }




}
