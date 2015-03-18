package perchello.hangman.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseActions extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="HighscoreDatabase";
    private static final String NAME="name";
    private static final String SCORE ="score";
    private static final String ADVPROGRESS1 ="advprogress1";
    private static final String ADVWORDSDONE1 ="advdwordsdone1";
    private static final String ADVPROGRESS2 ="advprogress2";
    private static final String ADVWORDSDONE2 ="advdwordsdone2";
    private static final String ADVPROGRESS3 ="advprogress3";
    private static final String ADVWORDSDONE3 ="advdwordsdone3";


    public DatabaseActions(Context context) {
    super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE if not exists "
                + DATABASE_NAME + "( "
                + NAME + ", "
                + SCORE + ", "
                + ADVPROGRESS1 + ", "
                + ADVWORDSDONE1 + ", "
                + ADVPROGRESS2 + ", "
                + ADVWORDSDONE2 + ", "
                + ADVPROGRESS3 + ", "
                + ADVWORDSDONE3 +");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d ("Old version", String.valueOf(oldVersion));
        Log.d ("New version", String.valueOf(newVersion));
        if (oldVersion==3){
            db.execSQL("ALTER TABLE "
                    + DATABASE_NAME + " ADD COLUMN "
                    + ADVPROGRESS3 + " INTEGER DEFAULT 0;");
            db.execSQL("ALTER TABLE "
                    + DATABASE_NAME + " ADD COLUMN "
                    + ADVWORDSDONE3 + " TEXT;");
        }
        else {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
            onCreate(db);
        }

        }

    public void setName(String name) {
        ContentValues values=new ContentValues(6);
        values.put(NAME, name);
        values.put (SCORE, 0);
        values.put (ADVPROGRESS1, 0);
        values.put (ADVWORDSDONE1, "");
        values.put (ADVPROGRESS2, 0);
        values.put (ADVWORDSDONE2, "");
        values.put (ADVPROGRESS3, 0);
        values.put (ADVWORDSDONE3, "");
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
        return isNewName;
    }
    public int getScore (String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_NAME,
                new String[] { NAME, SCORE},
                NAME + " = ?",
                new String [] {name}
                , null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }


        return cursor.getInt(1);
    }
    public int [] getAdvProgress (String name, int maxAdvNumber){
        SQLiteDatabase db = this.getReadableDatabase();
        int [] result = new int[maxAdvNumber];
        Cursor cursor = db.query(DATABASE_NAME,
                new String[] { NAME, ADVPROGRESS1, ADVPROGRESS2, ADVPROGRESS3},
                NAME + " = ?",
                new String [] {name}
                , null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        for (int i = 0; i<maxAdvNumber; i++){
            result [i] = cursor.getInt(i+1);
        }
        return result;
    }
    public String [] getAdvDone (String name, int maxAdvNumber){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] result = new String [maxAdvNumber];
        Cursor cursor = db.query(DATABASE_NAME,
                new String[] { NAME, ADVWORDSDONE1, ADVWORDSDONE2, ADVWORDSDONE3 },
                NAME + " = ?",
                new String [] {name}
                , null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        for (int i = 0; i<maxAdvNumber; i++){
            result [i] = cursor.getString(i+1);
        }

        return result;
    }
    public String getHighscore (){
        SQLiteDatabase db = this.getReadableDatabase();
        String result = new String();
        Cursor cursor = db.query(DATABASE_NAME, new String[] { NAME,
                        SCORE }, null, null,
                null, null, SCORE + " DESC");;
        if (cursor != null) {
            cursor.moveToFirst();
        }
        try {
            while(cursor.getString(0)!= null) {
                result += cursor.getString(0)+ " " +cursor.getInt(1) + " ";
                Log.d ("Cursor name and score",cursor.getString(0) +" " +cursor.getInt(1));
                cursor.moveToNext();
            }
        } catch (NullPointerException npe){
            Log.d ("Exception", npe.getMessage());
        } catch (CursorIndexOutOfBoundsException cioobe){
            Log.d ("Exception", cioobe.getMessage());
        }
        return result;
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
                new String[]{name});
    }
}
