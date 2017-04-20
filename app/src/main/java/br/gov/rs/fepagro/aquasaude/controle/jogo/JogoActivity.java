package br.gov.rs.fepagro.aquasaude.controle.jogo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.controle.jogo.adapter.RespostasAdapter;
import br.gov.rs.fepagro.aquasaude.modelo.ListaPerguntas;
import br.gov.rs.fepagro.aquasaude.modelo.Pergunta;
import br.gov.rs.fepagro.aquasaude.modelo.Resposta;

public class JogoActivity extends AppCompatActivity {

    public static final int NENHUMA_RESPOSTA_SELECIONADA = -1;

    public static ViewPager mViewPager;
    public static List<Pergunta> listaPerguntas;

    public static boolean[] acertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        listaPerguntas = new ListaPerguntas(getApplicationContext()).getListaPerguntas();
        acertos = new boolean[listaPerguntas.size()];

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        assert mViewPager != null;
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    public static class PerguntaFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final int NUMERO_DE_ALTERNATIVAS = 4;


        public PerguntaFragment() {
        }

        public static PerguntaFragment newInstance(int sectionNumber) {
            PerguntaFragment fragment = new PerguntaFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //View principal
            final View rootView = inflater.inflate(R.layout.fragment_jogo, container, false);
            //Número da pergunta, primeira = 0
            final int nPerguntaAtual = getArguments().getInt(ARG_SECTION_NUMBER) - 1;
            //Pergunta oject
            final Pergunta pergunta = listaPerguntas.get(nPerguntaAtual);
            //Número da pergunta no topo da página
            TextView textNumPergunta = (TextView) rootView.findViewById(R.id.text_num_pergunta);
            textNumPergunta.setText(String.format(Locale.getDefault(), "%d -", nPerguntaAtual + 1));
            //Título da página - titulo da pergunta
            TextView textPergunta = (TextView) rootView.findViewById(R.id.text_pergunta);
            textPergunta.setText(pergunta.getTitulo());

            GridView gridRespostas = (GridView) rootView.findViewById(R.id.grid_respostas);
            RespostasAdapter respostasAdapter =
                    new RespostasAdapter(getContext(), pergunta.getRespostas(), gridRespostas);
            gridRespostas.setAdapter(respostasAdapter);

            final Button btResponde = (Button) rootView.findViewById(R.id.bt_responde);
            btResponde.setOnClickListener(v -> {
                if (btResponde.getText().toString().contains("Confirmar")) {
                    // MOSTRAR SE A RESPOSTA ESTÁ CERTA

                    int respostaSelecionada = respostasAdapter.getSelectedPosition();

                    //VERIFICAR se o usuário acertou ou não
                    if (respostaSelecionada != NENHUMA_RESPOSTA_SELECIONADA) {
                        btResponde.setText(R.string.proxima);  //Muda o texto do botão para "Próxima"

                        bloqueiaRadios(gridRespostas);

                        boolean acertou = pergunta.getRespostas().get(respostaSelecionada).isCerta();
                        acertos[nPerguntaAtual] = acertou;
                        if (acertou) {
                            //o fundo da questao selecionada fica verde
                            View layout = getViewByPosition(respostaSelecionada, gridRespostas);
                            layout.setBackgroundResource(R.drawable.card_verde);
                        } else {
                            //balança a tela
                            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
                            rootView.findViewById(R.id.layout_game).startAnimation(animation);
                            //deixa o fundo da selecionada vermelho
                            int respostaCerta = getRespostaCerta(nPerguntaAtual);
                            View layoutSelecionado = getViewByPosition(respostaSelecionada, gridRespostas);
                            layoutSelecionado.setBackgroundResource(R.drawable.card_vermelho);
                            //o fundo da questão certa fica verde
                            View layoutCerta = getViewByPosition(respostaCerta, gridRespostas);
                            layoutCerta.setBackgroundResource(R.drawable.card_verde);
                        }
                    }

                } else {
                    // O BOTÃO ESTÁ COM O TEXTO 'PRÓXIMA'
                    int ultimaPergunta = listaPerguntas.size() - 1;
                    if (nPerguntaAtual == ultimaPergunta) {
                        ResultadosJogoFragment.calculaNota(); //atualiza a nota no fragment de resultados

                    }
                    //Passa para a próxima página
                    mViewPager.setCurrentItem(nPerguntaAtual + 1);
                }
            });
            return rootView;
        }

        public View getViewByPosition(int position, GridView gridView) {
            final int firstListItemPosition = gridView.getFirstVisiblePosition();
            final int lastListItemPosition = firstListItemPosition + gridView.getChildCount() - 1;

            if (position < firstListItemPosition || position > lastListItemPosition) {
                return gridView.getAdapter().getView(position, null, gridView);
            } else {
                final int childIndex = position - firstListItemPosition;
                return gridView.getChildAt(childIndex);
            }
        }

        /**
         * Método que bloqueia os radio buttons após a confirmação da resposta
         *
         * @param gridView
         */
        private void bloqueiaRadios(GridView gridView) {
            for (int j = 0; j < NUMERO_DE_ALTERNATIVAS; j++) {
                View layout = getViewByPosition(j, gridView);
                layout.setClickable(false);
                View radioButton = layout.findViewWithTag(j);
                radioButton.setClickable(false);
            }
        }


        public int getRespostaCerta(int numPergunta) {
            for (int i = 0; i < NUMERO_DE_ALTERNATIVAS; i++) {
                Resposta resposta = listaPerguntas.get(numPergunta).getRespostas().get(i);
                if (resposta.isCerta()) {
                    return i;
                }
            }
            return -1;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PerguntaFragment (defined as a static inner class below).

            if (position == listaPerguntas.size()) {
                //o último fragment é o de resultados do jogo
                return ResultadosJogoFragment.newInstace();
            }

            return PerguntaFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Total de perguntas = 5 + 1 fragment de resultados
            return listaPerguntas.size() + 1;
        }
    }

    public static class ResultadosJogoFragment extends Fragment {
        public static final int DEF_VALUE = -1;
        private View view;
        private static TextView textViewNota;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.content_resultado_jogo, container, false);

            textViewNota = (TextView) view.findViewById(R.id.text_nota);

            //Finalizar activity no click do botão
            view.findViewById(R.id.bt_finalizar_jogo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
            return view;
        }

        /**
         * Calcula a nota novamente na mudança de estado (por exemplo orientação alterada)
         */
        @Override
        public void onResume() {
            super.onResume();
            int nota = calculaNota();
            int totalPerguntas = listaPerguntas.size();
            String resultado = String.format(Locale.getDefault(), "%d/%d", nota, totalPerguntas);
            textViewNota.setText(resultado);
            salvarHighScore(nota);
        }

        public static int calculaNota() {
            int countAcertos = 0;
            for (boolean acertou : acertos) {
                if (acertou) {
                    countAcertos++;
                }
            }
            return countAcertos;
        }

        private void salvarHighScore(int notaAtual) {
            SharedPreferences banco = getSharedPreferences();
            int notaGravada = banco.getInt(getString(R.string.nota_quiz), DEF_VALUE);

            if (notaAtual > notaGravada) {
                gravaNotaAtual(banco, notaAtual);
            }
        }

        private void gravaNotaAtual(SharedPreferences banco, int notaAtual) {
            SharedPreferences.Editor editor = banco.edit();
            editor.putInt(getString(R.string.nota_quiz), notaAtual);
            editor.apply();
        }

        private SharedPreferences getSharedPreferences() {
            return getActivity()
                    .getSharedPreferences(getString(R.string.nome_shared_pref), Context.MODE_PRIVATE);
        }

        public static Fragment newInstace() {
            return new ResultadosJogoFragment();
        }
    }

    /**
     * Salva o array de acertos no restart da activity, mudança de orientação da tela
     *
     * @param savedState bundle que salvará o array de booleans
     */
    @Override
    public void onSaveInstanceState(Bundle savedState) {
        savedState.putBooleanArray("acertos", acertos);
        super.onSaveInstanceState(savedState);

    }

    /**
     * Recupera o array de acertos na mudança de estado
     *
     * @param savedState bundle com o array de booleans acertos
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        acertos = savedState.getBooleanArray("acertos");
    }

    /**
     * Exibe mensagem de alerta para abandonar o jogo, caso o usuario clique sim a activity é
     * finalizada e volta para o menu principal
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.atencao_sair);
        builder.setMessage(getString(R.string.dialog_abandonar_voltar));
        builder.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }
}
