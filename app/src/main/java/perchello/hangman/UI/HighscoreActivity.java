package perchello.hangman.UI;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import perchello.hangman.Adapters.HighscoreAdapter;
import perchello.hangman.Model.Highscore;
import perchello.hangman.Model.UserInfo;
import perchello.hangman.R;


public class HighscoreActivity extends Activity {
    private TextView mHighscoreTextView;
    private Highscore mHighscore;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        mHighscoreTextView = (TextView) findViewById(R.id.highscoreTextView);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mHighscoreTextView.setTypeface(typeface);
        mHighscore = new Highscore();
        mHighscore.getHighscore(this);

        HighscoreAdapter adapter = new HighscoreAdapter(this, mHighscore);
        mRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);


    }





}
