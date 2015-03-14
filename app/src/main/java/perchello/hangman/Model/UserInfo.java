package perchello.hangman.Model;

import android.content.Context;

/**
 * Created by Perchello on 08/03/2015.
 */
public class UserInfo {
    private DatabaseActions mDatabaseActions;
    private Context mContext;
    private String mName;
    private int mScore;
    private int [] mAdvProgress;
    private String [] mAdvDone;
    private static final int MAXADVNUMBER=2;

    public UserInfo(String name, Context context) {
        mName = name;
        setContext(context);
        setName(mName);
        mScore = getScore(mName);
        mAdvProgress = mDatabaseActions.getAdvProgress(mName, MAXADVNUMBER);
        mAdvDone = mDatabaseActions.getAdvDone(mName, MAXADVNUMBER);
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


    public int getAdvProgress(int advNumber) {

        return mAdvProgress[advNumber-1];
    }

    public String getAdvDone(int advNumber) {
        return mAdvDone[advNumber-1];
    }

    public void setAdvScore (String advDone, int advNumber, int hits) {
        mAdvDone [advNumber-1] += " " + advDone;
        mAdvProgress[advNumber-1]++;
        mScore+= hits;
        mDatabaseActions.updateScoreAdventure(mName, advNumber, mAdvDone[advNumber-1], mAdvProgress[advNumber-1], mScore);
    }

    public String getName() {
        return mName;
    }
}
