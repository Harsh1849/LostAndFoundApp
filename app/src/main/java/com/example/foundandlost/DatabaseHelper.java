package com.example.foundandlost;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LostFoundDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ITEMS = "items";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_DATE = "date";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_STATUS = "status";  // 'lost' or 'found'

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_LOCATION + " TEXT,"
                + KEY_STATUS + " TEXT" + ")";
        db.execSQL(CREATE_ITEMS_TABLE);
    }


    @SuppressLint("Range")
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ITEMS, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                item.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                item.setPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
                item.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                item.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                item.setLocation(cursor.getString(cursor.getColumnIndex(KEY_LOCATION)));
                item.setStatus(cursor.getString(cursor.getColumnIndex(KEY_STATUS)));
                itemList.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return itemList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_PHONE, item.getPhone());
        values.put(KEY_DESCRIPTION, item.getDescription());
        values.put(KEY_DATE, item.getDate());
        values.put(KEY_LOCATION, item.getLocation());
        values.put(KEY_STATUS, item.getStatus());

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }
}
