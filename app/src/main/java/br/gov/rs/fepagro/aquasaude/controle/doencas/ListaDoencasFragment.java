package br.gov.rs.fepagro.aquasaude.controle.doencas;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.modelo.Doenca;
import br.gov.rs.fepagro.aquasaude.modelo.ListaDoencas;

/**
 * Created by eduardo-pooch on 25/08/2016.
 */
public class ListaDoencasFragment extends Fragment {

    public static final int IMAGEM_CAPA = 0;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.content_lista_doencas, container, false);

        GridLayout gridLayout = (GridLayout) myView.findViewById(R.id.GridLayout);
        ArrayList<Doenca> doencas = new ListaDoencas(getActivity().getApplicationContext()).getDoencas();

        for (final Doenca doenca : doencas) {
            CardView card = (CardView) inflater.inflate(R.layout.item_lista, gridLayout, false);

            TextView nome = (TextView) card.findViewById(R.id.text_nome);
            nome.setText(doenca.getNome());

            ImageView imagem = (ImageView) card.findViewById(R.id.foto_principal);

            Glide.with(getActivity()).load(doenca.getImagemResId(IMAGEM_CAPA)).centerCrop().into(imagem);

            assert gridLayout != null;
            gridLayout.addView(card);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getBaseContext(),
                            DoencaScrollingActivity.class);
                    intent.putExtra("id_doenca", doenca.getId());
                    getActivity().startActivity(intent);
                }
            });
        }
        return myView;
    }
}
