package com.shwethasp.cameraimageload;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.SparseBooleanArray;

import java.util.ArrayList;

/**
 * Created by shwethap on 28-06-2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Save_ImagePath";
    public static final String TABLE_SAVEIMAGE = "saveimage";
    public static final String SAVEIMAGE_COLUMN_ID = "id";
    public static final String SAVEIMAGE_COLUMN_PATH = "path";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SAVEIMAGE_TABLE = "CREATE TABLE " + TABLE_SAVEIMAGE + "("
                + SAVEIMAGE_COLUMN_ID + " INTEGER PRIMARY KEY," + SAVEIMAGE_COLUMN_PATH + " TEXT)";
        db.execSQL(CREATE_SAVEIMAGE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS saveimage");
        // Create tables again
        onCreate(db);
    }

    // code to add the new imagepath
    public String insertImagePath(String imagepath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SAVEIMAGE_COLUMN_PATH, String.valueOf(imagepath));
        // Inserting Row
        long i = db.insert(TABLE_SAVEIMAGE, null, contentValues); //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        return null;
    }

    public ArrayList<String> getAllImages() {
        ArrayList<String> imagelist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try {
            c = db.query(false, TABLE_SAVEIMAGE, null, null, null, null, null, null, null, null);


            if (c != null) {
                if (c.moveToFirst()) {
                    do {

                        imagelist.add(c.getString(c.getColumnIndex(SAVEIMAGE_COLUMN_PATH)));
                    } while (c.moveToNext());
                }
            }
        } finally {
            c.close();
            db.close();
        }
        return imagelist;
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_SAVEIMAGE, null);
        return res;
    }

    public String getImageBypath(int id) {
        String image = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        try {
            c = db.query(false, TABLE_SAVEIMAGE, new String[]{SAVEIMAGE_COLUMN_PATH}, SAVEIMAGE_COLUMN_ID + "=?",
                    new String[]{String.valueOf(id + 1)}, null, null, null, null, null);

            if (c != null) {
                if (c.moveToFirst()) {
                    do {

                        image = c.getString(c.getColumnIndex(SAVEIMAGE_COLUMN_PATH));

                    } while (c.moveToNext());
                }
            }
        } catch (Exception e) {
            Log.e("getImagebyid Exception", e.getMessage().toString());

        } finally {
            c.close();
            db.close();
        }

        return image;
    }
}
