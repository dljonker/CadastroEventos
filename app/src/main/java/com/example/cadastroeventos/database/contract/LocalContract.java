package com.example.cadastroeventos.database.contract;

import com.example.cadastroeventos.database.entity.EventoEntity;
import com.example.cadastroeventos.database.entity.LocalEntity;

public final class LocalContract {

    private LocalContract() {}

    public static final String criarTabela() {
        return "CREATE TABLE " + LocalEntity.TABLE_NAME + " (" + LocalEntity._ID + " INTEGER PRIMARY KEY, " + LocalEntity.COLUMN_NAME_NOME + " TEXT, " + LocalEntity.COLUMN_NAME_BAIRRO + " TEXT," + LocalEntity.COLUMN_NAME_CIDADE + " TEXT," +
                LocalEntity.COLUMN_NAME_CAPACIDADE + " TEXT)";
    }

    public static final String deletarTabela() {
        return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;
    }
}
