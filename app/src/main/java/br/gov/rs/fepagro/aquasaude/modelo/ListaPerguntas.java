package br.gov.rs.fepagro.aquasaude.modelo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.gov.rs.fepagro.aquasaude.R;

/**
 * Created by eduardo-pooch on 07/10/2016.
 */
public class ListaPerguntas {

    private List<Pergunta> listaPerguntasCamarao;

    public ListaPerguntas(Context context){
        listaPerguntasCamarao = new ArrayList<>();
        ArrayList<RespostaImagem> respostas;
        Pergunta pergunta;

        //Pergunta 1
        respostas = new ArrayList<>(4);
        respostas.add(new RespostaImagem(R.drawable.imagem_peixe, true));
        respostas.add(new RespostaImagem(R.drawable.imagem_caranguejo, false));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        pergunta = new Pergunta(context.getString(R.string.pergunta_1), respostas);
        listaPerguntasCamarao.add(pergunta);

        //Pergunta 2
        respostas = new ArrayList<>(4);
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        respostas.add(new RespostaImagem(R.drawable.logo_ufcspa, true));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        pergunta = new Pergunta(context.getString(R.string.pergunta_2), respostas);
        listaPerguntasCamarao.add(pergunta);

        //Pergunta 3
        respostas = new ArrayList<>(4);
        respostas.add(new RespostaImagem(R.drawable.imagem_wssv, false));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        respostas.add(new RespostaImagem(R.drawable.logo_cnpq, true));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        pergunta = new Pergunta(context.getString(R.string.pergunta_3), respostas);
        listaPerguntasCamarao.add(pergunta);

        //Pergunta 4
        respostas = new ArrayList<>(4);
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, true));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        pergunta = new Pergunta(context.getString(R.string.pergunta_4), respostas);
        listaPerguntasCamarao.add(pergunta);

        //Pergunta 5
        respostas = new ArrayList<>(4);
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, true));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        respostas.add(new RespostaImagem(R.drawable.foto_padrao, false));
        pergunta = new Pergunta("Pergunta 5", respostas);
        listaPerguntasCamarao.add(pergunta);
    }

    public List<Pergunta> getListaPerguntas() {
        return listaPerguntasCamarao;
    }

}
