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

import perchello.hangman.R;


public class GameStartActivity extends Activity {
    Button mStartButton;
    TextView mGameNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);
        mStartButton = (Button) findViewById(R.id.loginButton);
        mGameNameTextView = (TextView) findViewById(R.id.nameTextView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Fedora.ttf");
        mGameNameTextView.setTypeface(typeface);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (GameStartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
