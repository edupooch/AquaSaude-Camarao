package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.Arrays;

import br.gov.rs.fepagro.aquasaude.R;

/**
 * Classe que controla a tela de resultados do checklist da classe{@link ChecklistFragment}
 * Mostra a porcentagem de biossegurança da fazenda e dicas personalizadas de acordo com as marcações.
 *
 * Usar {@link ResultadoChecklistFragment#newInstance} factory method para criar uma instância desse
 * fragment, sendo necessário passar os itens que nao foram marcados no checklist.
 *
 * Created by edupooch on 22/02/2017. Contato: edupooch@gmail.com
 */


public class ResultadoChecklistFragment extends Fragment {

    private View view;
    private int[] itensDesmarcados;

    private static final String ARG_ITENS_DESMARCADOS = "desmarcados";
    public static final int NOTA_MAXIMA = 10;
    public static final int VALOR_DEFAULT = -1;

    public ResultadoChecklistFragment() {
    }

    /**
     * Cria nova instâcia do fragment que recebe um parâmetro e o salva nos arguments do fragment
     *
     * @param itensDesmarcados itensDesmarcados que não foram marcados na checklist.
     * @return A new instance of fragment ResultadoChecklistFragment.
     */
    public static Fragment newInstance(ArrayList<Integer> itensDesmarcados) {
        ResultadoChecklistFragment fragment = new ResultadoChecklistFragment();

        Bundle args = new Bundle();
        args.putIntArray(ARG_ITENS_DESMARCADOS, transformaListaEmArray(itensDesmarcados));
        fragment.setArguments(args);

        return fragment;
    }
    /**
     * Construtor alternativo, recebe um int array, então não precisa converter depois
     * é chamado quando o usuário quer rever o ultimo resultado e a activity pega o int array gravado
     * no banco
     *
     * @param itensDesmarcados itensDesmarcados que não foram marcados na checklist.
     * @return A new instance of fragment ResultadoChecklistFragment.
     */
    public static Fragment newInstance(int[] itensDesmarcados) {
        ResultadoChecklistFragment fragment = new ResultadoChecklistFragment();

        Bundle args = new Bundle();
        args.putIntArray(ARG_ITENS_DESMARCADOS, itensDesmarcados);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        escondeAppBar();
        itensDesmarcados = getArguments().getIntArray(ARG_ITENS_DESMARCADOS);
    }

    private void escondeAppBar() {
        AppBarLayout bar = (AppBarLayout) getActivity().findViewById(R.id.app_bar_layout);
        if (bar != null) bar.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_resultado_checklist, container, false);
        int nota = getNota(); //Valor vai de 0..10

        mudaTextoDoResultado(nota);
        salvarHighScore(nota);
        salvaUltimoResultado();

        Button btOk = (Button) view.findViewById(R.id.bt_ok);
        btOk.setOnClickListener(v -> getActivity().finish());
        return view;
    }

    private void salvaUltimoResultado() {
        //Salva o último resultado como um toString de array
        SharedPreferences banco = getSharedPreferences();
        SharedPreferences.Editor editor = banco.edit();
        editor.putString(getString(R.string.string_itens), Arrays.toString(itensDesmarcados));
        //apply - commit assíncrono - do high score e último resultado - pro usuário poder rever dps
        editor.apply();
    }

    private int getNota() {
        return (NOTA_MAXIMA - itensDesmarcados.length);
    }

    /**
     * Define texto do resultado na tela, mostrando quantos porcento a fazenda está biossegura e
     * chama o método de mostar dicas
     *
     * @param nota 0..10
     */
    private void mudaTextoDoResultado(int nota) {
        TextView resultado = (TextView) view.findViewById(R.id.text_resultado);
        if (nota == NOTA_MAXIMA) {
            resultado.setText(getString(R.string.resultado_10)); //Sua fazenda está biossegura!
        } else {
            String strResultado = getString(R.string.sua_fazenda_esta) + nota * 10 +
                    getString(R.string.porcento_biossegura); //Sua fazenda está nota% biossegura!
            resultado.setText(strResultado);
            mostraDicas();
        }
    }

    /**
     * Mostra card de dicas com as dicas baseadas no que o usuário não marcou no checklist, essas
     * strings estão definidas no arquivo strings.xml na pasta values do projeto.
     */
    private void mostraDicas() {
        int[] dicasResId = getDicasId();

        StringBuilder builderDicas = new StringBuilder();
        for (int indiceDoItem : itensDesmarcados) {
            String dica = getString(dicasResId[indiceDoItem]);
            builderDicas.append(dica).append("\n\n");
        }

        String dicas = builderDicas.toString();
        TextView textViewDicas = (TextView) view.findViewById(R.id.dicas);
        textViewDicas.setText(dicas);
    }

    /**
     * Salva o high score na tabela de preferences do android, que é um banco de dados simples
     *
     * @param notaAtual numa escala de 0 a 10
     */
    private void salvarHighScore(int notaAtual) {
        SharedPreferences banco = getSharedPreferences();
        int notaGravada = banco.getInt(getString(R.string.nota_checklist), VALOR_DEFAULT);

        if (notaAtual > notaGravada) {
            gravaNotaAtual(banco, notaAtual);
        }
    }

    private void gravaNotaAtual(SharedPreferences banco, int notaAtual) {
        SharedPreferences.Editor editor = banco.edit();
        editor.putInt(getString(R.string.nota_checklist), notaAtual);
        editor.apply();
    }

    private SharedPreferences getSharedPreferences() {
        return getActivity()
                .getSharedPreferences(getString(R.string.nome_shared_pref), Context.MODE_PRIVATE);
    }

    /**
     * Recuperei a informação dos ids em um array para poder recuperar num laço 'for'
     *
     * @return ID das frases de dicas que devem ser mostradas de acordo com o que o usuário deixou
     * de marcar na ChecklistFragment
     */
    private int[] getDicasId() {
        return new int[]{R.string.resposta_1, R.string.resposta_2, R.string.resposta_3,
                R.string.resposta_4, R.string.resposta_5, R.string.resposta_6, R.string.resposta_7,
                R.string.resposta_8, R.string.resposta_9, R.string.resposta_10};
    }

    /**
     * Os dados são transformados em int[] para passar para a classe de resultados
     *
     * @param listaItensDesmarcados com Integers de 0..9
     * @return int array
     */
    private static int[] transformaListaEmArray(ArrayList<Integer> listaItensDesmarcados) {
        int[] arrayItensDesmarcados = new int[listaItensDesmarcados.size()];
        for (int i = 0; i < listaItensDesmarcados.size(); i++)
            arrayItensDesmarcados[i] = listaItensDesmarcados.get(i);
        return arrayItensDesmarcados;
    }
}
