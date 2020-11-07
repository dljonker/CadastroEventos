package com.example.cadastroeventos;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Evento implements Serializable {

    private int id;
    private String nomeEvento;
    private String dataEvento;
    //private String localEvento;
    private Local local;

    public Evento(int id ,String nomeEvento, String dataEvento,Local local) {
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        //this.localEvento = localEvento;
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    //public String getLocalEvento() {
    //    return localEvento;
    //}


    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @NonNull
    @Override
    public String toString() {
        return "Evento: " + nomeEvento  + "\n" +  "Data do Evento: "+ dataEvento + "\n" + local;
    }
}