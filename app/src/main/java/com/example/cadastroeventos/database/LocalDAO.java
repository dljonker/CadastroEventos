package com.example.cadastroeventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.cadastroeventos.Local;
import com.example.cadastroeventos.database.entity.EventoEntity;
import com.example.cadastroeventos.database.entity.LocalEntity;

import java.util.ArrayList;
import java.util.List;

public class LocalDAO {

    private static final String SQL_LISTAR_TODOS = "SELECT * FROM " + LocalEntity.TABLE_NAME;
    //private final String SQL_LISTAR_ID = "SELECT _ID FROM " + LocalEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public LocalDAO(Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Local local) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalEntity.COLUMN_NAME_NOME, local.getNomeLocal());
        contentValues.put(LocalEntity.COLUMN_NAME_BAIRRO, local.getBairro());
        contentValues.put(LocalEntity.COLUMN_NAME_CIDADE, local.getCidade());
        contentValues.put(LocalEntity.COLUMN_NAME_CAPACIDADE, local.getCapacidade());
        if (local.getId() > 0) {
            return dbGateway.getDatabase().update(LocalEntity.TABLE_NAME, contentValues, LocalEntity._ID + "=?", new String[] {String.valueOf(local.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(LocalEntity.TABLE_NAME, null, contentValues) > 0;

    }

    public List<Local> listar() {
        List<Local> local = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(LocalEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            String capacidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE));
            local.add(new Local (id, nome, bairro, cidade, capacidade));
        }
        cursor.close();
        return local;
    }


    public boolean deletar(Local local) {
        return dbGateway.getDatabase().delete(LocalEntity.TABLE_NAME, EventoEntity._ID + "=?", new String[] {String.valueOf(local.getId())}) > 0;
    }
}
