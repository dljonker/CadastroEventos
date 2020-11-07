package com.example.cadastroeventos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cadastroeventos.database.EventoDAO;
import com.example.cadastroeventos.database.LocalDAO;

public class ListaLocal extends AppCompatActivity {

    private ListView listViewLocais;
    private ArrayAdapter<Local> adapterLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_local);
        listViewLocais = findViewById(R.id.listView_locais);
        definirOnClickListenerListView();
        definirOnLongClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        adapterLocal = new ArrayAdapter<Local>(ListaLocal.this, android.R.layout.simple_list_item_1, localDAO.listar());
        listViewLocais.setAdapter(adapterLocal);

    }

    private void definirOnClickListenerListView() {
        listViewLocais.setOnItemClickListener(((parent, view, position, id) -> {
            Local localClicado = adapterLocal.getItem(position);
            Intent intent = new Intent(ListaLocal.this, CadastroLocal.class);
            intent.putExtra("localEdicao", localClicado);
            startActivity(intent);
        }));
    }

    public void onClickNovoLocal(View v) {
        Intent intent = new Intent(ListaLocal.this, CadastroLocal.class);
        startActivity(intent);
    }

    public void onClickEvento(View v) {
        Intent intent = new Intent(ListaLocal.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void definirOnLongClickListener(){
        listViewLocais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Local localClicado = adapterLocal.getItem(position);

                new AlertDialog.Builder(ListaLocal.this)
                        .setIcon(android.R.drawable.dark_header)
                        .setTitle("Deseja Deletar?")
                        .setMessage("Deseja Deletar este Local?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapterLocal.remove(localClicado);
                                adapterLocal.notifyDataSetChanged();
                                LocalDAO localDAO = new LocalDAO(getBaseContext());
                                localDAO.deletar(localClicado);
                                Toast.makeText(ListaLocal.this, "Local Deletado", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Não",null).show();
                return true;
            }
        });
    }

}

