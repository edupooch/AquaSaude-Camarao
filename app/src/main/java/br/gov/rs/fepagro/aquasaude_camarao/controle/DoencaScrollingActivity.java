package br.gov.rs.fepagro.aquasaude_camarao.controle;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import br.gov.rs.fepagro.aquasaude_camarao.R;
import br.gov.rs.fepagro.aquasaude_camarao.modelo.Doenca;
import br.gov.rs.fepagro.aquasaude_camarao.modelo.ListaDoencas;

public class DoencaScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int idDoenca = (int) getIntent().getSerializableExtra("id_doenca");
        Doenca doenca = ListaDoencas.getDoenca(idDoenca);
        setContentView(R.layout.activity_doenca_scrolling);
        View view;
        switch (idDoenca) {
            case ListaDoencas.INDICE_WSSV:
                view = findViewById(R.id.content_smb);
                ImageView imagem = (ImageView) findViewById(R.id.imagem_toolbar);
                assert imagem != null;
                imagem.setImageResource(doenca.getImagemResId());
                break;
            case ListaDoencas.INDICE_IMNV:
                view = findViewById(R.id.content_mni);
                break;
            case ListaDoencas.INDICE_NHP:
                view = findViewById(R.id.content_nhp);
                break;
            case ListaDoencas.INDICE_IHHNV:
                view = findViewById(R.id.content_nih);
                break;
            default:
                view = findViewById(R.id.content_padrao);
                break;
        }
        assert view != null;
        view.setVisibility(View.VISIBLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// Botão de voltar
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);


        //-------------escrevendo as informações--------------------------------------------------//
        setTitle(doenca.getNome());


//        TextView textAgente = (TextView) findViewById(R.id.text_agente);
//        assert textAgente != null;
//        textAgente.setText(doenca.getAgente());
//        TextView textSinais = (TextView) findViewById(R.id.text_sinais);
//        assert textSinais != null;
//        textSinais.setText(doenca.getSinaisClinicos());
//        TextView textPrevencao = (TextView) findViewById(R.id.text_prevencao);
//        assert textPrevencao != null;
//        textPrevencao.setText(doenca.getPrevencao());
//
//        if (doenca.getAtencao() != null) {
//            View tituloAtencao = findViewById(R.id.titulo_atencao);
//            assert tituloAtencao != null;
//            tituloAtencao.setVisibility(View.VISIBLE);
//            TextView textAtencao = (TextView) findViewById(R.id.text_sinais_atencao);
//            assert textAtencao != null;
//            textAtencao.setText(doenca.getAtencao());
//        }

        //-------------listener para aumentar e diminuir o layout clicado-------------------------//
        int[] layouts = {R.id.layout_agente, R.id.layout_sinais, R.id.layout_imagens, R.id.layout_prevencao};

        for (final int resId : layouts) {
            final LinearLayout layout = (LinearLayout) view.findViewById(resId);
            assert layout != null;
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                        TransitionManager.beginDelayedTransition(layout);

                    int marginTop = getPx(5);

                    if (layout.getLayoutParams().height == LinearLayout.LayoutParams.WRAP_CONTENT) {
                        LinearLayout.LayoutParams posicao = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getPx(50));
                        posicao.setMargins(0, marginTop, 0, 0);
                        layout.setLayoutParams(posicao);
                    } else {
                        LinearLayout.LayoutParams posicao = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        posicao.setMargins(0, marginTop, 0, 0);
                        layout.setLayoutParams(posicao);
                    }
                }
            });
        }
    }

    private int getPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }


}
