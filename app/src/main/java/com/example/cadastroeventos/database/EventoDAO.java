package com.example.cadastroeventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.EditText;

import com.example.cadastroeventos.Evento;
import com.example.cadastroeventos.Local;
import com.example.cadastroeventos.MainActivity;
import com.example.cadastroeventos.database.entity.EventoEntity;
import com.example.cadastroeventos.database.entity.LocalEntity;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS = "SELECT evento._id, evento.nome, data, idlocal, evento.nome, bairro, cidade, capacidade FROM " + EventoEntity.TABLE_NAME +
            " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID + " = " + EventoEntity.COLLUMN_NAME_ID_LOCAL + " ORDER BY evento.nome ASC";


    private DBGateway dbGateway;

    public EventoDAO (Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNomeEvento());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getDataEvento());
        contentValues.put(EventoEntity.COLLUMN_NAME_ID_LOCAL, evento.getLocal().getId());
        if (evento.getId() > 0) {
            return  dbGateway.getDatabase().update(EventoEntity.TABLE_NAME, contentValues, EventoEntity._ID + "=?",
                    new String[] {String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME, null, contentValues) > 0;
    }

    public List<Evento> listar() {
        List<Evento> evento = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            //String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_LOCAL));
            int idLocal = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_ID_LOCAL));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            String capacidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE));
            Local local = new Local (idLocal, nomeLocal, bairro, cidade, capacidade);
            evento.add(new Evento (id, nome, data, local));
        }
        cursor.close();
        return evento;
    }

    public List<Evento> search(String search) {
        String SQL_SEARCH = "SELECT evento._id, evento.nome, data, idlocal, evento.nome, bairro, cidade, capacidade FROM " + EventoEntity.TABLE_NAME +
                " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID + " = " + EventoEntity.COLLUMN_NAME_ID_LOCAL +
                " WHERE evento.nome LIKE '%" + search + "%' ORDER BY evento.nome ASC";

        List<Evento> evento = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_SEARCH, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            int idLocal = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_ID_LOCAL));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            String capacidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE));
            Local local = new Local (idLocal, nomeLocal, bairro, cidade, capacidade);
            evento.add(new Evento (id, nome, data, local));
        }
        cursor.close();
        return evento;
    }

    public List<Evento> searchCidade(String search) {
        String SQL_SEARCH = "SELECT evento._id, evento.nome, data, idlocal, evento.nome, bairro, local.cidade, capacidade FROM " + EventoEntity.TABLE_NAME +
                " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID + " = " + EventoEntity.COLLUMN_NAME_ID_LOCAL +
                " WHERE " + LocalEntity.COLUMN_NAME_CIDADE + " LIKE '%" + search + "%' ORDER BY evento.nome ASC";

        List<Evento> evento = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_SEARCH, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            int idLocal = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLLUMN_NAME_ID_LOCAL));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            String capacidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE));
            Local local = new Local (idLocal, nomeLocal, bairro, cidade, capacidade);
            evento.add(new Evento (id, nome, data, local));
        }
        cursor.close();
        return evento;
    }

    public boolean deletar(Evento evento) {
        return  dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME,
                EventoEntity._ID + "=?", new String[]{String.valueOf(evento.getId())}) > 0;
    }


}