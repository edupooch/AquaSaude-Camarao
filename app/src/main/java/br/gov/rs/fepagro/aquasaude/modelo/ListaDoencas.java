package br.gov.rs.fepagro.aquasaude.modelo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.gov.rs.fepagro.aquasaude.R;

/**
 * Created by eduardo-pooch on 19/08/2016.
 */
public class ListaDoencas {

    public static final int INDICE_WSSV = 0;
    public static final int INDICE_IMNV = 1;
    public static final int INDICE_IHHNV = 2;
    public static final int INDICE_NHP = 3;

    static ArrayList<Doenca> doencas;

    public ListaDoencas(Context context) {
        doencas = new ArrayList<>();

        Doenca wssv = new Doenca(INDICE_WSSV,context.getString(R.string.wssv_nome),
                new int[]{R.drawable.imagem_wssv,R.drawable.imagem_wssv_2,R.drawable.imagem_wssv_3});
        doencas.add(INDICE_WSSV, wssv);

        Doenca imnv = new Doenca(INDICE_IMNV,context.getString(R.string.imnv_nome),
                new int[]{R.drawable.imagem_mnv,R.drawable.imagem_mnv_2});
        doencas.add(INDICE_IMNV, imnv);

        Doenca ihhnv = new Doenca(INDICE_IHHNV,context.getString(R.string.ihhnv_nome),
                new int[]{R.drawable.imagem_ihhnv,R.drawable.imagem_ihhnv_2,R.drawable.imagem_ihhnv_3});
        doencas.add(INDICE_IHHNV, ihhnv);

        Doenca nhp = new Doenca(INDICE_NHP,context.getString(R.string.nhv_nome),
                new int[]{R.drawable.imagem_nhp});
        doencas.add(INDICE_NHP, nhp);
    }

    public ArrayList<Doenca> getDoencas() {
        return doencas;
    }

    public static Doenca getDoenca(int idDoenca) {
        return doencas.get(idDoenca);
    }
}
