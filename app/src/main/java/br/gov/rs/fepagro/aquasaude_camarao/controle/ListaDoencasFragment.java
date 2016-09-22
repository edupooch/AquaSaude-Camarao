package br.gov.rs.fepagro.aquasaude_camarao.controle;

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

import java.util.ArrayList;

import br.gov.rs.fepagro.aquasaude_camarao.R;
import br.gov.rs.fepagro.aquasaude_camarao.modelo.Doenca;
import br.gov.rs.fepagro.aquasaude_camarao.modelo.ListaDoencas;

/**
 * Created by eduardo-pooch on 25/08/2016.
 */
public class ListaDoencasFragment extends Fragment {

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
            imagem.setImageResource(doenca.getImagemResId());

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
