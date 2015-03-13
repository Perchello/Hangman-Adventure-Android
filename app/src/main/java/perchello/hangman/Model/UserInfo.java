package perchello.hangman.Model;

import android.content.Context;
import android.util.Log;

/**
 * Created by Perchello on 08/03/2015.
 */
public class UserInfo {
    private DatabaseActions mDatabaseActions;
    private Context mContext;
    private String mName;
    private int mScore;

    public UserInfo() {
        mName = "Guest";
        mScore = 0;
    }
    public UserInfo(String name, Context context) {
        mName = name;
        Log.d("Name set to", name);
        setContext(context);
        Log.d("Context set", name);
        setName(mName);
        mScore = getScore(mName);
        Log.d("Score set to", mScore + "");
    }

    public void setContext(Context context) {

        mContext = context;
        mDatabaseActions = new DatabaseActions(mContext);
    }

    public void setName(String name) {
        mName = name;
        if (mDatabaseActions.checkName(name)) {
            mDatabaseActions.setName(name);
            mScore = 0;
        } else {
            mScore = getScore(name);
        }
    }

    public void addScoreSingleGame(int score) {
        mScore += score;
        try {
            mDatabaseActions.checkName(mName);
            mDatabaseActions.updateScoreSingleGame(mName, mScore);
        } catch (Exception e) {
        }

    }

    public int getScore(String name) {
        return mDatabaseActions.getScore(name);
    }
    public void updateScoreAdventure(String name){
        mDatabaseActions.updateScoreAdventure(name, 1, "egypt", 1, 13);
    }
    public void updateDataVersion(){
        mDatabaseActions.onUpgrade(mDatabaseActions.getWritableDatabase(), 1, 2);
    }


    public String getName() {
        return mName;
    }
}
