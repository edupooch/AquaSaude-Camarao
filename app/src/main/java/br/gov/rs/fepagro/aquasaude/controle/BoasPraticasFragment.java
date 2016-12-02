package br.gov.rs.fepagro.aquasaude.controle;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.json.JSONObject;

import br.gov.rs.fepagro.aquasaude.R;

/**
 * Created by eduardo-pooch on 28/09/2016.
 */
public class BoasPraticasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_boas_praticas, container, false);

        View btChecklist = view.findViewById(R.id.buttonChecklist);
        btChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChecklistActivity.class));
            }
        });
        return view;

    }

}
