package com.example.cadastroeventos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {
    private static DBGateway dbGateway;
    private static SQLiteDatabase db;

    public static DBGateway getInstance(Context context) {
        if (dbGateway == null) {
            dbGateway = new DBGateway(context);
        }
        return dbGateway;
    }

    private DBGateway(Context context) {
        DataBaseDBHelper dbHelper = new DataBaseDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static SQLiteDatabase getDatabase() {
        return db;
    }
}
