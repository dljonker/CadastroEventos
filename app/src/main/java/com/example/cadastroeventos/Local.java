package com.example.cadastroeventos;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Local implements Serializable {

    private int id;
    private String nomeLocal;
    private String bairro;
    private String cidade;
    private String capacidade;

    public Local(int id, String nomeLocal, String bairro, String cidade, String capacidade) {
        this.id = id;
        this.nomeLocal = nomeLocal;
        this.bairro = bairro;
        this.cidade = cidade;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    @NonNull
    @Override
    public String toString() {
        return "Local: " + nomeLocal  +  "\n" + "Bairro do Local: "+ bairro + "\n" + "Cidade do Local: " + cidade + "\n" + "Capacidade de público: " + capacidade;
    }
}
