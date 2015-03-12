package perchello.hangman.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Perchello on 07/03/2015.
 */
public class DatabaseActions extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="HighscoreDatabase";
    private static final String NAME="name";
    private static final String SCORE ="score";
    private static final String ADVPROGRESS1 ="advprogress1";
    private static final String ADVWORDSDONE1 ="advdwordsdone1";


    public DatabaseActions(Context context) {
    super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE if not exists "
                + DATABASE_NAME + "( "
                + NAME + ", "
                + SCORE + ", "
                + ADVPROGRESS1 + ", "
                + ADVWORDSDONE1 + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  DATABASE_NAME);
        onCreate(db);
    }
    public void setName(String name) {
        ContentValues values=new ContentValues(4);
        values.put(NAME, name);
        values.put (SCORE, 0);
        values.put (ADVPROGRESS1, 0);
        values.put (ADVWORDSDONE1, "");
        getWritableDatabase().insert(DATABASE_NAME, null, values);
        close();
    }

    public boolean checkName (String name){
        boolean isNewName = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_NAME, new String[] { NAME, SCORE}, NAME + "=?", new String [] {name} , null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                if (!cursor.moveToFirst()){
                    isNewName = true;

                }
            }

        Log.d ("isNEwName is: ", Boolean.toString(isNewName));
        return isNewName;
    }
    public int getScore (String name){
        int score = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_NAME,
                new String[] { NAME, SCORE},
                NAME + " = ?",
                new String [] {name}
                , null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        score = cursor.getInt(1);

        return score;
    }
    public void updateScoreSingleGame (String name, int score){
        ContentValues values=new ContentValues(2);
        values.put(NAME, name);
        values.put(SCORE, score);
        getWritableDatabase().update(DATABASE_NAME,
                values,
                NAME + "=?",
                new String[] {name});
    }
    public void updateScoreAdventure (String name, int adventure, String advDone, int advProg, int score) {
        ContentValues values=new ContentValues(2);
        values.put(NAME, name);
        values.put(SCORE, score);
        values.put("advdwordsdone"+adventure, advDone);
        values.put("advprogress"+adventure, advProg);
        getWritableDatabase().update(DATABASE_NAME,
                values,
                NAME + "=?",
                new String[] {name});
    }
}
