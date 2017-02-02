package br.gov.rs.fepagro.aquasaude.controle.jogo;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.controle.jogo.CustomViewPager;
import br.gov.rs.fepagro.aquasaude.modelo.ListaPerguntas;
import br.gov.rs.fepagro.aquasaude.modelo.Pergunta;
import br.gov.rs.fepagro.aquasaude.modelo.RespostaImagem;

public class JogoActivity extends AppCompatActivity {

    public static final int NENHUMA_RESPOSTA_SELECIONADA = -1;

    public static ViewPager mViewPager;
    public static List<Pergunta> listaPerguntas;

    public static boolean[] acertos;
    private static SectionsPagerAdapter mSectionsPagerAdapter;

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
        mSectionsPagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());


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
            final int numPergunta = getArguments().getInt(ARG_SECTION_NUMBER) - 1;
            //Pergunta oject
            final Pergunta pergunta = listaPerguntas.get(numPergunta);
            //Título da página - titulo da pergunta
            TextView textPergunta = (TextView) rootView.findViewById(R.id.text_pergunta);
            textPergunta.setText(pergunta.getTitulo());
            configuraRadioButtons(rootView);
            //Imagem das respostas
            int[] imageViewResId = {R.id.foto_resposta_0, R.id.foto_resposta_1, R.id.foto_resposta_2, R.id.foto_resposta_3};
            for (int i = 0; i < NUMERO_DE_ALTERNATIVAS; i++) {
                ImageView imagem = (ImageView) rootView.findViewById(imageViewResId[i]);
                imagem.setImageResource(pergunta.getRespostas().get(i).getResIdFoto());
            }

            //Ação do botão de responder
            final Button btResponde = (Button) rootView.findViewById(R.id.bt_responde);
            btResponde.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btResponde.getText().toString().contains("Confirmar")) {
                        // MOSTRAR SE A RESPOSTA ESTÁ CERTA

                        //Pega a resposta selecionada pelo usuário
                        int respostaSelecionada = getRespostaSelecionada(rootView);

                        //VERIFICAR se o usuário acertou ou não
                        if (respostaSelecionada != NENHUMA_RESPOSTA_SELECIONADA) {

                            //Muda o texto do botão para "Próxima"
                            btResponde.setText(R.string.proxima);
                            bloqueiaRadios(rootView);

                            if (pergunta.getRespostas().get(respostaSelecionada).isCerta()) {
                                //ACERTOU A RESPOSTA
                                acertos[numPergunta] = true;

                                //o fundo da questao selecionada fica verde
                                View layout = rootView.findViewWithTag("layout_resposta_" + respostaSelecionada);
                                layout.setBackgroundResource(R.drawable.card_verde);
                            } else {
                                //ERROU A RESPOSTA
                                acertos[numPergunta] = false;
                                //animação de balançar a viewContent
                                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
                                rootView.findViewById(R.id.layout_game).startAnimation(animation);

                                //deixa o fundo da selecionada vermelho
                                int respostaCerta = getRespostaCerta(numPergunta);
                                View layoutSelecionado = rootView.findViewWithTag("layout_resposta_" + respostaSelecionada);
                                layoutSelecionado.setBackgroundResource(R.drawable.card_vermelho);

                                //o fundo da questão certa fica verde
                                View layoutCerta = rootView.findViewWithTag("layout_resposta_" + respostaCerta);
                                layoutCerta.setBackgroundResource(R.drawable.card_verde);
                            }
                        }

                    } else {
                        // O BOTÃO ESTÁ COM O TEXTO 'PRÓXIMA'
                        if (numPergunta == listaPerguntas.size() - 1) {
                            //última pergunta
                            ResultadosJogoFragment.calculaNota(); //atualiza a nota no fragment de resultados
                        }
                        //Passa para a próxima página
                        mViewPager.setCurrentItem(numPergunta + 1);
                    }
                }
            });
            return rootView;
        }

        /**
         * Método que bloqueia os radio buttons após a confirmação da resposta
         *
         * @param rootView viewContent principal
         */
        private void bloqueiaRadios(View rootView) {
            for (int j = 0; j < NUMERO_DE_ALTERNATIVAS; j++) {
                RadioButton radio = (RadioButton) rootView.findViewWithTag("radio" + j);
                radio.setClickable(false);
                View layout = rootView.findViewWithTag("layout_resposta_" + j);
                layout.setClickable(false);
            }
        }


        private void configuraRadioButtons(final View rootView) {
            //Para cada resposta
            for (int i = 0; i < NUMERO_DE_ALTERNATIVAS; i++) {
                final int nResposta = i;
                View layout = rootView.findViewWithTag("layout_resposta_" + nResposta);
                final RadioButton radio = (RadioButton) layout.findViewWithTag("radio" + nResposta);

                //Listener no layout e no botao para quando for clicado ativar o radiobutton e
                //desativar os outros botoes
                View.OnClickListener onRadioClick = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        radio.setChecked(true);
                        //Retira retira o check dos outros radio buttons
                        for (int j = 0; j < NUMERO_DE_ALTERNATIVAS; j++) {
                            if (j != nResposta) {
                                RadioButton outrosRadios =
                                        (RadioButton) rootView.findViewWithTag("radio" + j);
                                outrosRadios.setChecked(false);
                            }
                        }
                    }
                };
                layout.setOnClickListener(onRadioClick);
                radio.setOnClickListener(onRadioClick);
            }
        }

        /**
         * Verifica os radioButtons da viewContent e retorna o número da resposta que está selecionada
         *
         * @param rootView para pegar os radioButons da viewContent
         * @return 0-3 para numeros de respostas e -1 para nenhuma selecionada
         */
        private int getRespostaSelecionada(View rootView) {
            int[] radioButtonsResId = {R.id.radio_resposta_0, R.id.radio_resposta_1, R.id.radio_resposta_2, R.id.radio_resposta_3};
            for (int i = 0; i < NUMERO_DE_ALTERNATIVAS; i++) {
                RadioButton radioButton = (RadioButton) rootView.findViewById(radioButtonsResId[i]);
                if (radioButton.isChecked()) {
                    return i;
                }
            }
            return NENHUMA_RESPOSTA_SELECIONADA;
        }

        public int getRespostaCerta(int numPergunta) {
            for (int i = 0; i < NUMERO_DE_ALTERNATIVAS; i++) {
                RespostaImagem resposta = listaPerguntas.get(numPergunta).getRespostas().get(i);
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
        private View view;
        private static TextView textViewNota;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.content_resultados, container, false);

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
            calculaNota();
        }

        public static void calculaNota() {
            int countAcertos = 0;
            for (boolean nota : acertos) {
                if (nota) {
                    countAcertos++;
                }
            }
            textViewNota.setText(String.valueOf(countAcertos));
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
