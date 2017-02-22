package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import br.gov.rs.fepagro.aquasaude.R;

/**
 * Created by edupooch on 22/02/2017.
 */
public class ChecklistFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_checklist, container, false);

        View btConfirma =  view.findViewById(R.id.bt_confirma_checklist);
        assert btConfirma != null;
        btConfirma.setOnClickListener(v ->
                getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new BoasPraticasFragment())
                .commit());

        return view;
    }


}
