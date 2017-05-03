package br.gov.rs.fepagro.aquasaude.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by eduardo-pooch on 18/08/2016.
 */
public class Doenca implements Serializable {

    private int id;
    private String nome;
    private int[] imagensResId;


    public Doenca(int id, String nome, int[] imagemResId) {
        this.id = id;
        this.nome = nome;
        this.imagensResId = imagemResId;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int[] getImagensResId() {
        return imagensResId;
    }

    public int getImagemResId(int position) {
        return imagensResId[position];
    }
}
