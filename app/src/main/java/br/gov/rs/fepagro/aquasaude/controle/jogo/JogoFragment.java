package br.gov.rs.fepagro.aquasaude.controle.jogo;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.gov.rs.fepagro.aquasaude.R;

public class JogoFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_escolher_jogo, container, false);
        View botao = view.findViewById(R.id.btComecarJogo);

        botao.setOnClickListener(v -> {
            Intent intentComecaJogo = new Intent(getActivity(), JogoActivity.class);
            startActivity(intentComecaJogo);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        configuraHighScore();
    }

    private void configuraHighScore() {
        SharedPreferences sharedPref = getActivity()
                .getSharedPreferences(getString(R.string.nome_shared_pref), Context.MODE_PRIVATE);
        int defaultValue = -1;
        int nota = sharedPref.getInt(getString(R.string.nota_quiz), defaultValue);
        if (nota != defaultValue) {
            TextView textView = (TextView) view.findViewById(R.id.text_highscore_quiz);
            textView.setVisibility(View.VISIBLE);
            String texto = "Sua maior nota: " + nota;
            textView.setText(texto);
        }
    }


}
