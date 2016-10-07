package br.gov.rs.fepagro.aquasaude.controle;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import br.gov.rs.fepagro.aquasaude.R;

public class JogoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_escolher_jogo, container, false);
        View botao = view.findViewById(R.id.btComecarJogo);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentComecaJogo = new Intent(getActivity(),Jogo2Activity.class);
                startActivity(intentComecaJogo);
            }
        });

        return view;
    }


}
