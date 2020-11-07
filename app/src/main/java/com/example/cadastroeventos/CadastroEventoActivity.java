package com.example.cadastroeventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cadastroeventos.database.EventoDAO;
import com.example.cadastroeventos.database.LocalDAO;

public class CadastroEventoActivity extends AppCompatActivity {

    private final int Result_Code_Novo_Evento = 10;
    private final int Result_Code_Evento_Editado = 11;

    private boolean edicao = false;
    private boolean exclusao = false;
    private int id = 0;

    private Spinner spinnerLocais;
    private ArrayAdapter<Local> localAdapter;
    private EditText editTextNomeEvento;
    private EditText editTextDataEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");
        spinnerLocais = findViewById(R.id.spinner_locais);
        editTextNomeEvento = findViewById(R.id.editTextNome_do_Evento);
        editTextDataEvento = findViewById(R.id.editTextDate_Data_do_Evento);
        carregarLocais();
        carregarEvento();
    }

    private void carregarLocais() {
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        localAdapter = new ArrayAdapter<Local>(CadastroEventoActivity.this, android.R.layout.simple_spinner_item, localDAO.listar());
        spinnerLocais.setAdapter(localAdapter);
    }


    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            //EditText editTextLocalEvento = findViewById(R.id.editText_Local_do_evento);
            editTextNomeEvento.setText(evento.getNomeEvento());
            editTextDataEvento.setText(String.valueOf(evento.getDataEvento()));
            //editTextLocalEvento.setText(evento.getLocalEvento());
            int posicaoLocal = obterPosicaoLocal(evento.getLocal());
            spinnerLocais.setSelection(posicaoLocal);
            edicao = true;
            id = evento.getId();
        }
        else if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoExclusao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoExclusao");
            //editTextNomeEvento = findViewById(R.id.editTextNome_do_Evento);
            //editTextDataEvento = findViewById(R.id.editTextDate_Data_do_Evento);
            //EditText editTextLocalEvento = findViewById(R.id.editText_Local_do_evento);
            editTextNomeEvento.setText(evento.getNomeEvento());
            editTextDataEvento.setText(evento.getDataEvento());
            //editTextLocalEvento.setText(evento.getLocalEvento());
            exclusao = true;
            id = evento.getId();

        }


    }

    private int obterPosicaoLocal(Local local) {
        for (int posicao = 0; posicao < localAdapter.getCount(); posicao++) {
            if (localAdapter.getItem(posicao).getId() == local.getId()) {
                return posicao;
            }
        }
        return 0;

    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        EditText editTextNomeEvento = findViewById(R.id.editTextNome_do_Evento);
        EditText editTextDataEvento = findViewById(R.id.editTextDate_Data_do_Evento);

        if (!editTextNomeEvento.getText().toString().isEmpty() && !editTextDataEvento.getText().toString().isEmpty()) {

            String nomeEvento = editTextNomeEvento.getText().toString();
            String dataEvento = editTextDataEvento.getText().toString();
            int posicaoLocal = spinnerLocais.getSelectedItemPosition();
            Local local = (Local) localAdapter.getItem(posicaoLocal);
            Evento evento = new Evento(id, nomeEvento, dataEvento, local);
            EventoDAO eventoDAO = new EventoDAO(getBaseContext());
            boolean salvou = eventoDAO.salvar(evento);
            if (salvou) {
                finish();
            } else {
                Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar evento", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(CadastroEventoActivity.this, "Todos os campos são obrigatórios!", Toast.LENGTH_LONG).show();
        }
    }

}
