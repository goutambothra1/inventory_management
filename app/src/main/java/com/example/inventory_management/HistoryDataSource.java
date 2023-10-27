package com.example.inventory_management;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class HistoryDataSource {
    private SQLiteDatabase database;

    private MyDatabaseHelper dbHelper;

    public HistoryDataSource(Context context) {
        dbHelper = new MyDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Insert a history item
    public void insertHistoryItem(HistoryItem historyItem) {
        ContentValues values = new ContentValues();
        values.put("item_name", historyItem.getItemName());
        values.put("item_id", historyItem.getItemId());
        values.put("department", historyItem.getDepartment());
        values.put("quantity", historyItem.getQuantity());
        values.put("date", historyItem.getDate());

        database.insert("History", null, values);
    }

    // Query history items
    @SuppressLint("Range")
    public List<HistoryItem> getAllHistoryItems() {
        List<HistoryItem> historyItems = new ArrayList<>();
        Cursor cursor = database.query("History", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                HistoryItem historyItem = new HistoryItem();
                historyItem.setItemId(cursor.getInt(cursor.getColumnIndex("itemId")));
                historyItem.setItemName(cursor.getString(cursor.getColumnIndex("item_name")));
                historyItem.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
                historyItem.setQuantity(cursor.getInt(cursor.getColumnIndex("quantity")));
                historyItem.setDate(cursor.getString(cursor.getColumnIndex("date")));
                historyItems.add(historyItem);
            }
            cursor.close();
        }

        return historyItems;
    }
}

