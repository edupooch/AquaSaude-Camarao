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

import com.bumptech.glide.Glide;

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.modelo.Doenca;
import br.gov.rs.fepagro.aquasaude.modelo.ListaDoencas;


/**
 * Classe que define a activity de descrição das doenças, com uma foto no topo
 * estilo ScrollingActivity.
 */
public class DoencaScrollingActivity extends AppCompatActivity {

    public static final int IMAGEM_CAPA = 0;
    View viewContent;
    private Doenca doenca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doenca_scrolling);

        int idDoenca = (int) getIntent().getSerializableExtra("id_doenca");
        doenca = ListaDoencas.getDoenca(idDoenca);
        setTitle(doenca.getNome());

        switch (idDoenca) {
            case ListaDoencas.INDICE_WSSV:
                viewContent = findViewById(R.id.content_wssv);
                break;
            case ListaDoencas.INDICE_IMNV:
                viewContent = findViewById(R.id.content_imnv);
                break;
            case ListaDoencas.INDICE_NHP:
                viewContent = findViewById(R.id.content_nhv);
                break;
            case ListaDoencas.INDICE_IHHNV:
                viewContent = findViewById(R.id.content_ihhnv);
                break;
            default:
                viewContent = null;
                break;
        }

        assert viewContent != null;
        viewContent.setVisibility(View.VISIBLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        //Imagem da doença na toolbar
        ImageView imageViewCapa = (ImageView) findViewById(R.id.imagem_toolbar);
        assert imageViewCapa != null;
        Glide.with(getApplicationContext()).load(doenca.getImagemResId(IMAGEM_CAPA)).centerCrop().into(imageViewCapa);

        carregaImagens();
    }

    private void carregaImagens() {
        int[] imageViewsResId = new int[]{R.id.imagem_doenca_0, R.id.imagem_doenca_1, R.id.imagem_doenca_2};
        for (int i = 0; i < doenca.getImagensResId().length; i++) {
            ImageView imageView = (ImageView) viewContent.findViewById(imageViewsResId[i]);
            imageView.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(doenca.getImagemResId(i)).centerCrop().into(imageView);
        }
    }

    private int dp2Px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public void abreParentLayout(View view) {
        String tagDaBarra = (String) view.getTag();
        String section = tagDaBarra.replace("barra", "");
        String tag = "layout" + section;
        View parentLayout = viewContent.findViewWithTag(tag);
        abreLayout(parentLayout);
    }

    //-------------listener para aumentar e diminuir o layout clicado-------------------------//
    public void abreLayout(View view) {
        final LinearLayout layout = (LinearLayout) view;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            TransitionManager.beginDelayedTransition(layout);

        int marginTop = dp2Px(5);

        if (layout.getLayoutParams().height == LinearLayout.LayoutParams.WRAP_CONTENT) {
            LinearLayout.LayoutParams posicao =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2Px(50));
            posicao.setMargins(0, marginTop, 0, 0);
            layout.setLayoutParams(posicao);
        } else {
            LinearLayout.LayoutParams posicao = new LinearLayout
                    .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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

    public void abreImagem(View view) {
        String textoId = getResources().getResourceEntryName(view.getId());
        int position = Integer.valueOf(textoId.replace("imagem_doenca_", ""));
        Intent intent = new Intent(getApplicationContext(), ImagemActivity.class);
        intent.putExtra("imagem", doenca.getImagemResId(position));
        startActivity(intent);
    }
}
