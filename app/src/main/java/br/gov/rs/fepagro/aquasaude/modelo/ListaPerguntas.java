package br.gov.rs.fepagro.aquasaude.modelo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardo-pooch on 07/10/2016.
 */
public class ListaPerguntas {

    public static  List<Pergunta> listaPerguntas = new ArrayList<>();

    public void ListaPerguntasCamarao(Context context){
        Pergunta pergunta1 = new Pergunta();
        pergunta1.setTitulo("Qual das imagens abaixo tem Mionecrose Infecciosa?");
        ArrayList<RespostaImagem> respostas = new ArrayList<>(4);


        RespostaImagem resposta1 = new RespostaImagem();
        respostas.add(resposta1);
    }
}
