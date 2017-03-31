package br.gov.rs.fepagro.aquasaude.modelo;

import java.util.ArrayList;

/**
 * Created by eduardo-pooch on 14/09/2016.
 */
public class Pergunta {
    String titulo;
    ArrayList<Resposta> respostas;

    public Pergunta(String titulo, ArrayList<Resposta> respostas) {
        this.titulo = titulo;
        this.respostas = respostas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(ArrayList<Resposta> respostas) {
        this.respostas = respostas;
    }
}
