package com.example.cadastroeventos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cadastroeventos.database.contract.EventoContract;
import com.example.cadastroeventos.database.contract.LocalContract;

public class DataBaseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.produto v8";
    private static final int DATABASE_VERSION = 2;

    public DataBaseDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EventoContract.criarTabela());
        db.execSQL(LocalContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EventoContract.deletarTabela());
        db.execSQL(LocalContract.deletarTabela());
        db.execSQL(LocalContract.criarTabela());
        db.execSQL(LocalContract.criarTabela());
    }
}