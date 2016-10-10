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

import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.modelo.ListaPerguntas;
import br.gov.rs.fepagro.aquasaude.modelo.Pergunta;

public class JogoActivity extends AppCompatActivity {

    //TODO: Passar a tela somente com o link do botão
    public static ViewPager mViewPager;
    public List<Pergunta> listaPerguntas;

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
            View rootView = inflater.inflate(R.layout.fragment_jogo, container, false);
            //Número da pergunta, primeira = 0
            final int numPergunta = getArguments().getInt(ARG_SECTION_NUMBER) - 1;

            TextView textPergunta = (TextView) rootView.findViewById(R.id.text_pergunta);
            configuraRadioButtons(rootView);


            View btResponde = rootView.findViewById(R.id.bt_responde);
            btResponde.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(numPergunta + 1);
                }
            });

            return rootView;
        }

        private void configuraRadioButtons(final View rootView) {
            //Para cada resposta
            for (int i = 1; i <= 4; i++) {
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
                        for (int j = 1; j <= 4; j++) {
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
