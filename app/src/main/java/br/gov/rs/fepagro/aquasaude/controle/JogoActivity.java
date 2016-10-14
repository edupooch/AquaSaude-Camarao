package br.gov.rs.fepagro.aquasaude.controle;

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
import android.widget.Toast;

import java.util.List;

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.modelo.ListaPerguntas;
import br.gov.rs.fepagro.aquasaude.modelo.Pergunta;
import br.gov.rs.fepagro.aquasaude.modelo.RespostaImagem;

public class JogoActivity extends AppCompatActivity {

    public static final int ULTIMA_PERGUNTA = 4;
    public static final int NENHUMA_RESPOSTA_SELECIONADA = -1;

    public static ViewPager mViewPager;
    public static List<Pergunta> listaPerguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        listaPerguntas = new ListaPerguntas(getApplicationContext()).getListaPerguntas();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        assert mViewPager != null;
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final int NUMERO_DE_RESPOSTAS = 4;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
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
            for (int i = 0; i < NUMERO_DE_RESPOSTAS; i++) {
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

                            if (pergunta.getRespostas().get(respostaSelecionada).isCerta()) {
                                //ACERTOU A RESPOSTA
                                View layout = rootView.findViewWithTag("layout_resposta_" + respostaSelecionada);
                                layout.setBackgroundResource(R.drawable.card_verde);
                                Toast.makeText(getContext(), "Acertou", Toast.LENGTH_SHORT).show();
                            } else {
                                //ERROU A RESPOSTA
//                                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
//                                rootView.findViewById(R.id.layout_game).setAnimation(animation);
                                int respostaCerta = getRespostaCerta(numPergunta);
                                View layoutSelecionado = rootView.findViewWithTag("layout_resposta_" + respostaSelecionada);
                                layoutSelecionado.setBackgroundResource(R.drawable.card_vermelho);

                                View layoutCerta = rootView.findViewWithTag("layout_resposta_" + respostaCerta);
                                layoutCerta.setBackgroundResource(R.drawable.card_verde);

                                Toast.makeText(getContext(), "Errou", Toast.LENGTH_SHORT).show();
                            }
                        }

                        } else { // O BOTÃO ESTÁ COM O TEXTO 'PRÓXIMA'
                            //passar a página
                            if (numPergunta == ULTIMA_PERGUNTA) {
                                //Termina a activity na última pergunta
                                getActivity().finish();
                            } else {
                                //Passa para a próxima página
                                mViewPager.setCurrentItem(numPergunta + 1);
                            }
                        }
                    }
            });

            return rootView;
        }

        private void configuraRadioButtons(final View rootView) {
            //Para cada resposta
            for (int i = 0; i < NUMERO_DE_RESPOSTAS; i++) {
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
                        for (int j = 0; j < NUMERO_DE_RESPOSTAS; j++) {
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
         * Verifica os radioButtons da view e retorna o número da resposta que está selecionada
         *
         * @param rootView para pegar os radioButons da view
         * @return 0-3 para numeros de respostas e -1 para nenhuma selecionada
         */
        private int getRespostaSelecionada(View rootView) {
            int[] radioButtonsResId = {R.id.radio_resposta_0, R.id.radio_resposta_0, R.id.radio_resposta_2, R.id.radio_resposta_3};
            for (int i = 0; i < NUMERO_DE_RESPOSTAS; i++) {
                RadioButton radioButton = (RadioButton) rootView.findViewById(radioButtonsResId[i]);
                if (radioButton.isChecked()) {
                    return i;
                }
            }
            return NENHUMA_RESPOSTA_SELECIONADA;
        }

        public int getRespostaCerta(int numPergunta) {
            for (int i = 0; i < NUMERO_DE_RESPOSTAS; i++) {
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
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Total de perguntas = 5
            return listaPerguntas.size();
        }
    }
}
