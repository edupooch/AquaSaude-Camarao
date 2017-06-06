package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        btChecklist.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ChecklistActivity.class));
        });


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

        String strUltimoResultado = sharedPref.getString(getString(R.string.string_itens), null);
        try {
            if (strUltimoResultado != null) {
                int[] itensUltimoResultado = toIntArray(strUltimoResultado);

                Button btRever = (Button) view.findViewById(R.id.bt_rever);
                btRever.setVisibility(View.VISIBLE);
                Intent intentResultados = new Intent(getActivity(), ChecklistActivity.class);
                intentResultados.putExtra("resultado", itensUltimoResultado);
                btRever.setOnClickListener(v -> startActivity(intentResultados));
            }
        } catch (Exception e){
            e.printStackTrace();
        }



    }

    public static int[] toIntArray(String input) {
        String beforeSplit = input.replaceAll("\\[|\\]|\\s", "");
        String[] split = beforeSplit.split("\\,");
        int[] result = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            result[i] = Integer.parseInt(split[i]);
        }
        return result;
    }
}
