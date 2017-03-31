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
        ArrayList<Resposta> respostas;
        Pergunta pergunta;

        //Pergunta 1
        respostas = new ArrayList<>(4);
        respostas.add(new Resposta(R.drawable.imagem_peixe, true));
        respostas.add(new Resposta(R.drawable.imagem_caranguejo, false));
        respostas.add(new Resposta(R.drawable.imagem_larva, false));
        respostas.add(new Resposta(R.drawable.imagem_densidade, false));
        pergunta = new Pergunta(context.getString(R.string.pergunta_1), respostas);
        listaPerguntasCamarao.add(pergunta);

        //Pergunta 2
        respostas = new ArrayList<>(4);
        respostas.add(new Resposta(R.drawable.imagem_densidade, false));
        respostas.add(new Resposta(R.drawable.imagem_temperatura, false));
        respostas.add(new Resposta(R.drawable.imagem_greenhouse, true));
        respostas.add(new Resposta(R.drawable.imagem_salinidade, false));
        pergunta = new Pergunta(context.getString(R.string.pergunta_2), respostas);
        listaPerguntasCamarao.add(pergunta);

        //Pergunta 3
        respostas = new ArrayList<>(4);
        respostas.add(new Resposta(R.drawable.imagem_wssv, false));
        respostas.add(new Resposta(R.drawable.foto_padrao, true));
        respostas.add(new Resposta(R.drawable.imagem_ihhnv, false));
        respostas.add(new Resposta(R.drawable.imagem_mnv, false));
        pergunta = new Pergunta(context.getString(R.string.pergunta_3), respostas);
        listaPerguntasCamarao.add(pergunta);

        //Pergunta 4
        respostas = new ArrayList<>(4);
        respostas.add(new Resposta(R.drawable.imagem_greenhouse, true));
        respostas.add(new Resposta(R.drawable.imagem_probiotico, false));
        respostas.add(new Resposta(R.drawable.imagem_pediluvio, false));
        respostas.add(new Resposta(R.drawable.imagem_animais_domesticos, true));
        pergunta = new Pergunta(context.getString(R.string.pergunta_4), respostas);
        listaPerguntasCamarao.add(pergunta);

        //Pergunta 5
        respostas = new ArrayList<>(4);
        respostas.add(new Resposta(R.drawable.foto_padrao, true));
        respostas.add(new Resposta(R.drawable.foto_padrao, false));
        respostas.add(new Resposta(R.drawable.foto_padrao, false));
        respostas.add(new Resposta(R.drawable.foto_padrao, false));
        pergunta = new Pergunta("Pergunta 5", respostas);
        listaPerguntasCamarao.add(pergunta);
    }

    public List<Pergunta> getListaPerguntas() {
        return listaPerguntasCamarao;
    }

}
