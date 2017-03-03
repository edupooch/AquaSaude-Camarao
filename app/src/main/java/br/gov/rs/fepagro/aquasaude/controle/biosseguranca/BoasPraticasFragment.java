package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import br.gov.rs.fepagro.aquasaude.R;

/**
 * Created by eduardo-pooch on 28/09/2016.
 */
public class BoasPraticasFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_boas_praticas, container, false);

        View btChecklist = view.findViewById(R.id.buttonChecklist);
        btChecklist.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), ChecklistActivity.class)));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getActivity()
                .getSharedPreferences(getString(R.string.nome_shared_pref), Context.MODE_PRIVATE);
        int defaultValue = -1;
        int nota = sharedPref.getInt(getString(R.string.nota_checklist), defaultValue);
        if (nota != defaultValue) {
            TextView textView = (TextView) view.findViewById(R.id.text_highscore);
            String texto = "Sua maior nota: " + nota + "/10";
            textView.setText(texto);
        }
    }
}
