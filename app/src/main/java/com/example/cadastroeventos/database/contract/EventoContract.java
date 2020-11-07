package com.example.cadastroeventos.database.contract;

import com.example.cadastroeventos.database.entity.EventoEntity;
import com.example.cadastroeventos.database.entity.LocalEntity;

public final class EventoContract {

    private EventoContract() {}

    public static final String criarTabela() {
        return "CREATE TABLE " + EventoEntity.TABLE_NAME + " (" + EventoEntity._ID +
                " INTEGER PRIMARY KEY, " + EventoEntity.COLUMN_NAME_NOME + " TEXT, " +
                EventoEntity.COLUMN_NAME_DATA + " TEXT ," + EventoEntity.COLLUMN_NAME_ID_LOCAL +
                " TEXT," + " FOREIGN KEY (" + EventoEntity.COLLUMN_NAME_ID_LOCAL + ") REFERENCES " + LocalEntity.TABLE_NAME + "(" + LocalEntity._ID + "))";
    }

    public static final String deletarTabela() {
        return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;
    }
}