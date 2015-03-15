package perchello.hangman.Model;

import android.content.Context;
import android.widget.Toast;

public class HangmanGameLogic {
    public final static  int MAX_MISSES = 7;
    String mGameName;
    String mHits;
    String mMisses;
    Context mContext;

    public void setContext(Context context) {

        mContext = context;
    }

    public String getHits() {
        return mHits;
    }

    public void setHits(String hits) {
        mHits = hits;
    }

    public void setGameName(String gameName) {
        mGameName = gameName;
    }

    public void setMisses(String misses) {
        mMisses = misses;
    }

    public HangmanGameLogic(String gameName){
        mGameName=gameName;
        mHits="";
        mMisses="";
    }

    public String getGameName(){
        return mGameName;

    }
    public String getMisses(){
        return mMisses;

    }
    public boolean validateGuess (char guess){
        boolean answer = true;
        if (mMisses.indexOf(guess)>=0 || mHits.indexOf(guess)>=0){
            Toast.makeText(mContext, "Already guessed this letter. Try again", Toast.LENGTH_SHORT).show();
            answer = false;
        }
        return answer;
    }
    public boolean checkGuess(char guess){
        Boolean result = false;
        if (validateGuess(guess)) {
            if (mGameName.indexOf(guess) >= 0) {
                mHits += guess;
                result = true;
            } else {
                mMisses += guess;
            }
        }
        return result;
        }
    public String checkProgress (){
        String progress = "";
        for (char letter : mGameName.toCharArray()){
            if (mHits.indexOf(letter)>=0){
                progress+=letter;
            }
            else {
                progress+="-";
            }
        }
        return progress;

    }
    public boolean compareAnswerProg (){
        String progress = checkProgress();
        return progress.equals(mGameName);
    }
    public int getTries (){
        return MAX_MISSES-mMisses.length();
    }
    public boolean compareMisses () {
        return mMisses.length() >=MAX_MISSES;
    }
}

