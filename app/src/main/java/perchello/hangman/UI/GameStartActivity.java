package perchello.hangman.UI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import perchello.hangman.Model.ConnectDatabase;
import perchello.hangman.R;


public class GameStartActivity extends Activity {
    Button mStartButton;
    EditText mEnterNameEditText;
    String username;
    TextView mGameNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);
        mStartButton = (Button) findViewById(R.id.startButton);
        mEnterNameEditText = (EditText) findViewById(R.id.enterNameEditText);
        mGameNameTextView = (TextView) findViewById(R.id.nameTextView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mGameNameTextView.setTypeface(typeface);
        //ConnectDatabase db = new ConnectDatabase();
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mEnterNameEditText.getText().toString().isEmpty()) {
                    username = mEnterNameEditText.getText().toString();
                    startGame();
                }
                else {
                    Toast.makeText (GameStartActivity.this, "Please enter your name", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void startGame () {
    Intent intent = new Intent(this, ChooseGameModeActivity.class);
    intent.putExtra("username", username);
        startActivity(intent);


    }

}
