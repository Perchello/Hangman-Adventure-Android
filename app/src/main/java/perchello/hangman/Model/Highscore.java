package perchello.hangman.Model;

import android.content.Context;

/**
 * Created by Perchello on 22/03/2015.
 */
public class Highscore {
    private DatabaseActions mDatabaseActions;
    private String [] mNames;
    private String [] mScores;

    public Highscore() {

    }
    public void getHighscore(Context context) {
        mDatabaseActions = new DatabaseActions(context);

        String [] result = mDatabaseActions.getHighscore();
        mNames = new String [result.length/2];
        mScores = new String [result.length/2];

        for (int i = 0; i<result.length; i++){
            if (i%2==0){
                mNames [i/2] = result[i]+" ";
            }
            if (i%2==1){
                mScores [i/2] = result[i]+ " ";
            }

        }
    }

    public String[] getScores() {
        return mScores;
    }

    public void setScores(String[] scores) {
        mScores = scores;
    }

    public String[] getNames() {
        return mNames;
    }

    public void setNames(String[] names) {
        mNames = names;
    }
}
