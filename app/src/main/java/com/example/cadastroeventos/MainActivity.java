package com.example.cadastroeventos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.cadastroeventos.database.EventoDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int id = 0;
    private ListView listViewEventos;
    private ArrayAdapter<Evento> adapterEventos;
    private String ordem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Eventos");
        listViewEventos = findViewById(R.id.ListView_Eventos);
        definirOnClickListenerListView();
        definirOnLongClickListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventoDAO.listar());
        listViewEventos.setAdapter(adapterEventos);
        EditText editTextSearch = findViewById(R.id.editText_searchNome);
        EditText editTextSearchCidade = findViewById(R.id.editText_searchCidade);
        editTextSearch.setText("");
        editTextSearchCidade.setText("");
    }


    private void definirOnClickListenerListView() {
        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }

    private void definirOnLongClickListener(){
        listViewEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEventos.getItem(position);

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.dark_header)
                        .setTitle("Deseja Deletar?")
                        .setMessage("Deseja Deletar este Evento?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapterEventos.remove(eventoClicado);
                                adapterEventos.notifyDataSetChanged();
                                EventoDAO eventoDAO = new EventoDAO(getBaseContext());
                                eventoDAO.deletar(eventoClicado);
                                Toast.makeText(MainActivity.this, "Evento Deletado", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Não",null).show();
                return true;
            }
        });
    }


    public void onClickNovoEvento(View v) {
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivity(intent);
    }

    public void onClickLocal(View v) {
        Intent intent = new Intent(MainActivity.this, ListaLocal.class);
        startActivity(intent);
        finish();
    }

    public void onClickSearch(View v) {
        EditText editTextSearch = findViewById(R.id.editText_searchNome);
        String clickSearch = editTextSearch.getText().toString();
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventoDAO.search(clickSearch));
        listViewEventos.setAdapter(adapterEventos);
    }

    public void onClickSearchCity(View v) {
        EditText editTextSearchCidade = findViewById(R.id.editText_searchCidade);
        String clickSearch = editTextSearchCidade.getText().toString();
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventoDAO.searchCidade(clickSearch));
        listViewEventos.setAdapter(adapterEventos);
    }

    public void onRefreshPesquisa(View v) {
        onResume();
    }

}
