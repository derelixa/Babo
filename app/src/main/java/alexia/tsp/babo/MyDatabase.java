package alexia.tsp.babo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_TABLE_NAME = "vocabDatabase";
    private static final String KEY_ID = "id";
    private static final String KEY_WORD = "kword";
    private static final String KEY_TRAD = "enWord";

    public MyDatabase(Context context) {
        super(context, DATABASE_TABLE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_WORD + " TEXT, " + KEY_TRAD + " TEXT);";
        db.execSQL(DATABASE_TABLE_CREATE);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertData(Vocab v){
        Log.i("alexia"," Insert in database");
        Log.i("alexia", v.enVoc);
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, v.kVoc);
        values.put(KEY_TRAD, v.enVoc);
        db.insertOrThrow(DATABASE_TABLE_NAME,null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }


    public ArrayList<Vocab> readData(){
        ArrayList<Vocab> listV = new ArrayList<Vocab>();
        Log.i("alexia", "Reading database...");
        String select = new String("SELECT DISTINCT *  from " + DATABASE_TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("alexia", "Number of entries: " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();do {
                Vocab v = new Vocab(cursor.getString(cursor.getColumnIndex(KEY_WORD)), cursor.getString(cursor.getColumnIndex(KEY_TRAD)));
                listV.add(v);
                Log.i("alexia", "Reading: " + cursor.getString(cursor.getColumnIndex(KEY_WORD)));
            } while (cursor.moveToNext());
        }
        return listV;
    }

    public boolean CheckIsDataAlreadyInDBorNot(String fieldValue) {
        SQLiteDatabase db = getReadableDatabase();
        String select = new String("SELECT DISTINCT *  from " + DATABASE_TABLE_NAME);
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();do {
                if (cursor.getString(cursor.getColumnIndex(KEY_WORD)).equals(fieldValue)) {
                    return false;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return true;
    }


    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


}
