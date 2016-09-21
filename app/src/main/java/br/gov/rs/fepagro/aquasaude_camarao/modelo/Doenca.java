package br.gov.rs.fepagro.aquasaude_camarao.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardo-pooch on 18/08/2016.
 */
public class Doenca implements Serializable {

    private int id;
    private String nome;
    private Integer imagemResId;


    public Doenca(int id, String nome, Integer imagemResId) {
        this.id = id;
        this.nome = nome;
        this.imagemResId = imagemResId;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getImagemResId() {
        return imagemResId;
    }

}
