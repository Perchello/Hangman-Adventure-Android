package perchello.hangman.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import perchello.hangman.ParseConstants;

public class UserInfo {
    private String mName;
    private int mScore;
    private int [] mAdvProgress;
    private String [] mAdvDone;
    private static final int MAXADVNUMBER=7;
    private ParseUser mCurrentUser;


    public UserInfo() {
        mCurrentUser = ParseUser.getCurrentUser();
        mName = mCurrentUser.getUsername();
        mScore = (int) mCurrentUser.get(ParseConstants.KEY_SCORE);
        setAdvProgress();
    }

    private void setAdvProgress() {
        mAdvProgress = new int [MAXADVNUMBER];
        mAdvDone = new String [MAXADVNUMBER];

        for (int i = 0; i<MAXADVNUMBER; i++){
            Log.i ("Check", ParseConstants.KEY_ADVPROGRESS+(i+1));
            mAdvProgress[i]= (int) mCurrentUser.get(ParseConstants.KEY_ADVPROGRESS+(i+1));
            mAdvDone[i]= (String) mCurrentUser.get(ParseConstants.KEY_ADVWORDSDONE+(i+1));
            Log.i ("Setting adv prog:", mAdvProgress[i]+"");

        }

    }

    public void addScoreSingleGame(int score, boolean isNetworkAvailable) {
        mScore += score;
        mCurrentUser.put(ParseConstants.KEY_SCORE, mScore);

        if (isNetworkAvailable) {
            mCurrentUser.saveInBackground();
        }
    }


    public int getAdvProgress(int advNumber) {

        return mAdvProgress[advNumber-1];
    }

    public String getAdvDone(int advNumber) {

        return mAdvDone[advNumber-1];
    }

    public void setAdvScoreOnline(String advDone, int advNumber, int hits) {
        mAdvDone[advNumber - 1] += " " + advDone;
        mAdvProgress[advNumber - 1]++;
        mScore += hits;
        mCurrentUser.put(ParseConstants.KEY_ADVWORDSDONE + advNumber, mAdvDone[advNumber - 1]);
        mCurrentUser.put(ParseConstants.KEY_ADVPROGRESS + advNumber, mAdvProgress[advNumber - 1]);
        mCurrentUser.put(ParseConstants.KEY_SCORE, mScore);
        mCurrentUser.saveInBackground();
    }

    public void setAdvScoreOffline (String advDone, int advNumber, int hits) {
        mAdvDone[advNumber - 1] += " " + advDone;
        mAdvProgress[advNumber - 1]++;

        if (!(boolean) mCurrentUser.get(ParseConstants.KEY_OFFLINE)) {
            mScore = hits;
            mCurrentUser.put(ParseConstants.KEY_OFFLINE, true);

        } else {
            mScore +=hits;
        }
        mCurrentUser.put(ParseConstants.KEY_ADVWORDSDONE + advNumber, mAdvDone[advNumber - 1]);
        mCurrentUser.put(ParseConstants.KEY_ADVPROGRESS + advNumber, mAdvProgress[advNumber - 1]);
        mCurrentUser.put(ParseConstants.KEY_SCORE, mScore);
    }


    public String getName() {
        return mName;
    }
    public int getScore(){
        return mScore;
    }


}
