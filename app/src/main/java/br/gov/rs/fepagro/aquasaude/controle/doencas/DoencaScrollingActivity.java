package br.gov.rs.fepagro.aquasaude.controle.doencas;

import android.content.Intent;
import android.net.Uri;
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

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.modelo.Doenca;
import br.gov.rs.fepagro.aquasaude.modelo.ListaDoencas;


/**
 * Classe que define a activity de descrição das doenças, com uma foto no topo
 * estilo ScrollingActivity.
 */
public class DoencaScrollingActivity extends AppCompatActivity {

    View viewContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int idDoenca = (int) getIntent().getSerializableExtra("id_doenca");
        Doenca doenca = ListaDoencas.getDoenca(idDoenca);
        setContentView(R.layout.activity_doenca_scrolling);

        switch (idDoenca) {
            case ListaDoencas.INDICE_WSSV:
                viewContent = findViewById(R.id.content_smb);
                break;
            case ListaDoencas.INDICE_IMNV:
                viewContent = findViewById(R.id.content_mni);
                break;
            case ListaDoencas.INDICE_NHP:
                viewContent = findViewById(R.id.content_nhp);
                break;
            case ListaDoencas.INDICE_IHHNV:
                viewContent = findViewById(R.id.content_ihhnv);
                break;
            default:
                viewContent = null;
                break;
        }
        //Imagem da doença na toolbar
        ImageView imagem = (ImageView) findViewById(R.id.imagem_toolbar);
        assert imagem != null;
        imagem.setImageResource(doenca.getImagemResId());

        assert viewContent != null;
        viewContent.setVisibility(View.VISIBLE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Botão de voltar
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        setTitle(doenca.getNome());


        //-------------listener para aumentar e diminuir o layout clicado-------------------------//
        int[] layouts = {R.id.layout_agente, R.id.layout_sinais, R.id.layout_imagens, R.id.layout_prevencao};

        for (final int resId : layouts) {
            final LinearLayout layout = (LinearLayout) viewContent.findViewById(resId);
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


    //-------------listener para aumentar e diminuir o layout clicado-------------------------//
    public void abreLayout(View view) {
        final LinearLayout layout = (LinearLayout) view;

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


    public void abrirLinkDaTag(View view) {
        String url = (String) view.getTag();
        System.out.println(url);
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
