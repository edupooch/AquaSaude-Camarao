package br.gov.rs.fepagro.aquasaude.controle.jogo;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.controle.jogo.JogoActivity;

public class JogoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_escolher_jogo, container, false);
        View botao = view.findViewById(R.id.btComecarJogo);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentComecaJogo = new Intent(getActivity(),JogoActivity.class);
                startActivity(intentComecaJogo);
            }
        });

        return view;
    }


}
