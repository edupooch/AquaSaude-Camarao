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
    Context context;

    public ListaPerguntas(Context context) {
        listaPerguntasCamarao = new ArrayList<>();
        this.context = context;

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
        criaPerguntaTexto(R.string.pergunta_5,
                R.string.pergunta_5_resposta_certa,
                R.string.pergunta_5_resposta_2,
                R.string.pergunta_5_resposta_3,
                R.string.pergunta_5_resposta_4);

        //Pergunta 6
        criaPerguntaTexto(R.string.pergunta_6,
                R.string.pergunta_6_resposta_2,
                R.string.pergunta_6_resposta_3,
                R.string.pergunta_6_resposta_certa,
                R.string.pergunta_6_resposta_4);

        //Pergunta 7
        criaPerguntaTexto(R.string.pergunta_7,
                R.string.pergunta_7_resposta_2,
                R.string.pergunta_7_resposta_certa,
                R.string.pergunta_7_resposta_3,
                R.string.pergunta_7_resposta_4);

        //Pergunta 8
        criaPerguntaTexto(R.string.pergunta_8,
                R.string.pergunta_8_resposta_certa,
                R.string.pergunta_8_resposta_2,
                R.string.pergunta_8_resposta_3,
                R.string.pergunta_8_resposta_4);
        //Pergunta 9
        criaPerguntaTexto(R.string.pergunta_9,
                R.string.pergunta_9_resposta_2,
                R.string.pergunta_9_resposta_3,
                R.string.pergunta_9_resposta_4,
                R.string.pergunta_9_resposta_certa);
        //Pergunta 10
        criaPerguntaTexto(R.string.pergunta_10,
                R.string.pergunta_10_resposta_2,
                R.string.pergunta_10_resposta_3,
                R.string.pergunta_10_resposta_certa,
                R.string.pergunta_10_resposta_4);

    }

    private void criaPerguntaTexto(int resIdPergunta, int resIdResposta_1, int resIdResposta_2,
                                   int resIdResposta_3, int resIdResposta_4) {

        ArrayList<Resposta> respostas = new ArrayList<>(4);
        respostas.add(new Resposta(context.getString(resIdResposta_1), isCerta(resIdResposta_1)));
        respostas.add(new Resposta(context.getString(resIdResposta_2), isCerta(resIdResposta_2)));
        respostas.add(new Resposta(context.getString(resIdResposta_3), isCerta(resIdResposta_3)));
        respostas.add(new Resposta(context.getString(resIdResposta_4), isCerta(resIdResposta_4)));

        Pergunta pergunta = new Pergunta(context.getString(resIdPergunta), respostas);
        listaPerguntasCamarao.add(pergunta);
    }

    private boolean isCerta(int resId){
        return context.getResources().getResourceEntryName(resId).contains("certa");
    }

    public List<Pergunta> getListaPerguntas() {
        return listaPerguntasCamarao;
    }

}
