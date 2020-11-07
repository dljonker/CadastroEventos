package com.example.cadastroeventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cadastroeventos.database.LocalDAO;

public class CadastroLocal extends AppCompatActivity {

    private int id = 0;
    private EditText editTextNome;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextCapacidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle("Cadastro de Local");
        editTextNome = findViewById(R.id.editTextNomeLocal);
        editTextBairro = findViewById(R.id.editTextBairro);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextCapacidade = findViewById(R.id.editTextCapacidade);
        carregarLocal();
    }

    public void carregarLocal() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("localEdicao") != null) {
            Local local = (Local) intent.getExtras().get("localEdicao");
            editTextNome.setText(local.getNomeLocal());
            editTextBairro.setText(local.getBairro());
            editTextCidade.setText(local.getCidade());
            editTextCapacidade.setText(local.getCapacidade());
            id = local.getId();

        }
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        String nomeLocal = editTextNome.getText().toString();
        String bairroLocal = editTextBairro.getText().toString();
        String cidadeLocal = editTextCidade.getText().toString();
        String capacidadeLocal = editTextCapacidade.getText().toString();
        Local local = new Local(id, nomeLocal, bairroLocal, cidadeLocal, capacidadeLocal);
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        boolean salvou = localDAO.salvar(local);
        if (salvou) {
            finish();
        } else {
            Toast.makeText(CadastroLocal.this, "Erro ao salvar local", Toast.LENGTH_SHORT).show();
        }
    }
}
