package br.gov.rs.fepagro.aquasaude.controle;


import android.app.Fragment;
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
        View view = inflater.inflate(R.layout.content_jogo, container, false);

        ImageView camarao = (ImageView) view.findViewById(R.id.icon_camarao);
        camarao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v.getAnimation() == null) {
                    rotate(v);
                }
            }
        });
        return view;
    }

    private void rotate(View camarao) {
        RotateAnimation rotate = new RotateAnimation(0, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setInterpolator(new LinearInterpolator());

        camarao.startAnimation(rotate);
    }


}
