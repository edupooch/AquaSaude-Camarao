package br.gov.rs.fepagro.aquasaude.modelo;

import java.util.ArrayList;

/**
 * Created by eduardo-pooch on 14/09/2016.
 */
public class Pergunta {
    String pergunta;
    ArrayList<Resposta> respostas;

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public ArrayList<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(ArrayList<Resposta> respostas) {
        this.respostas = respostas;
    }
}
