package br.gov.rs.fepagro.aquasaude.controle.sobre;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import br.gov.rs.fepagro.aquasaude.R;

/**
 * Created by eduardo-pooch on 01/09/2016.
 */
public class SobreFragment extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_sobre, container, false);

        ImageView instituicoes = (ImageView) view.findViewById(R.id.imagem_logos);
        ImageView logoApp = (ImageView) view.findViewById(R.id.logo_app);

        Glide.with(getActivity()).load(R.drawable.logos_instituicoes).into(instituicoes);
        Glide.with(getActivity()).load(R.drawable.logo_app).into(logoApp);
        return view;
    }


}
