package perchello.hangman.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.DatabaseUtils;
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
    private static final String ADVPROGRESS4 ="advprogress4";
    private static final String ADVWORDSDONE4 ="advdwordsdone4";
    private static final String ADVPROGRESS5 ="advprogress5";
    private static final String ADVWORDSDONE5 ="advdwordsdone5";
    private static final String ADVPROGRESS6 ="advprogress6";
    private static final String ADVWORDSDONE6 ="advdwordsdone6";
    private static final String ADVPROGRESS7 ="advprogress7";
    private static final String ADVWORDSDONE7 ="advdwordsdone7";


    public DatabaseActions(Context context) {
    super(context, DATABASE_NAME, null, 6);
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
                + ADVWORDSDONE3 + ", "
                + ADVPROGRESS4 + ", "
                + ADVWORDSDONE4 + ", "
                + ADVPROGRESS5 + ", "
                + ADVWORDSDONE5 + ", "
                + ADVPROGRESS6 + ", "
                + ADVWORDSDONE6 + ", "
                + ADVPROGRESS7 + ", "
                + ADVWORDSDONE7+");");

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
            oldVersion=4;
        }
        if (oldVersion==4){
            db.execSQL("ALTER TABLE "
                    + DATABASE_NAME + " ADD COLUMN "
                    + ADVPROGRESS4 + " INTEGER DEFAULT 0;");
            db.execSQL("ALTER TABLE "
                    + DATABASE_NAME + " ADD COLUMN "
                    + ADVWORDSDONE4 + " TEXT;");
            db.execSQL("ALTER TABLE "
                    + DATABASE_NAME + " ADD COLUMN "
                    + ADVPROGRESS5 + " INTEGER DEFAULT 0;");
            db.execSQL("ALTER TABLE "
                    + DATABASE_NAME + " ADD COLUMN "
                    + ADVWORDSDONE5 + " TEXT;");
            oldVersion=5;

        }
        if (oldVersion ==5) {
            db.execSQL("ALTER TABLE "
                    + DATABASE_NAME + " ADD COLUMN "
                    + ADVPROGRESS6 + " INTEGER DEFAULT 0;");
            db.execSQL("ALTER TABLE "
                    + DATABASE_NAME + " ADD COLUMN "
                    + ADVWORDSDONE6 + " TEXT;");
            db.execSQL("ALTER TABLE "
                    + DATABASE_NAME + " ADD COLUMN "
                    + ADVPROGRESS7 + " INTEGER DEFAULT 0;");
            db.execSQL("ALTER TABLE "
                    + DATABASE_NAME + " ADD COLUMN "
                    + ADVWORDSDONE7 + " TEXT;");
            oldVersion=6;
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
        values.put (ADVPROGRESS4, 0);
        values.put (ADVWORDSDONE4, "");
        values.put (ADVPROGRESS5, 0);
        values.put (ADVWORDSDONE5, "");
        values.put (ADVPROGRESS6, 0);
        values.put (ADVWORDSDONE6, "");
        values.put (ADVPROGRESS7, 0);
        values.put (ADVWORDSDONE7, "");
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
                new String[] { NAME, ADVPROGRESS1, ADVPROGRESS2, ADVPROGRESS3, ADVPROGRESS4, ADVPROGRESS5
                               , ADVPROGRESS6, ADVPROGRESS7},
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
                new String[] { NAME, ADVWORDSDONE1, ADVWORDSDONE2, ADVWORDSDONE3, ADVWORDSDONE4, ADVWORDSDONE5
                               , ADVWORDSDONE6, ADVWORDSDONE7},
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
    public String[] getHighscore (){
        SQLiteDatabase db = this.getReadableDatabase();
        long numRows = DatabaseUtils.queryNumEntries(db, DATABASE_NAME);
        Log.d ("Number of rows is ", numRows+"");

        String [] result = new String [(int) numRows*2];
        Cursor cursor = db.query(DATABASE_NAME, new String[] { NAME,
                        SCORE }, null, null,
                null, null, SCORE + " DESC");;
        if (cursor != null) {
            cursor.moveToFirst();
        }
        try {
            int k=0;
            for (int i = 0; i<numRows; i++){

                result[k] = cursor.getString(0);
                result[k+1] = cursor.getInt(1)+"";
                k+=2;
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
