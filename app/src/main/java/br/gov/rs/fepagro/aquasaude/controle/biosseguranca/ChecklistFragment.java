package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import br.gov.rs.fepagro.aquasaude.R;

/**
 * Created by edupooch on 22/02/2017.
 */
public class ChecklistFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_checklist, container, false);

        View btConfirma = view.findViewById(R.id.bt_confirma_checklist);
        assert btConfirma != null;
        btConfirma.setOnClickListener(v -> {
            int[] desmarcados = getItensDesmarcados();
            getFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, ResultadoChecklistFragment.newInstance(desmarcados))
                    .commit();
        });
        return view;
    }

    private int[] getItensDesmarcados() {
        int[] checkBoxResId = new int[]{R.id.checkbox_1, R.id.checkbox_2, R.id.checkbox_3,
                R.id.checkbox_4, R.id.checkbox_5, R.id.checkbox_6, R.id.checkbox_7, R.id.checkbox_8,
                R.id.checkbox_9, R.id.checkbox_10};

        ArrayList<Integer> lista = new ArrayList<>();

        for (int i = 0; i < checkBoxResId.length; i++) {
            int resId = checkBoxResId[i];
            CheckBox check = (CheckBox) view.findViewById(resId);
            if (!check.isChecked()) {
                lista.add(i);
            }
        }

        int[] array = new int[lista.size()];
        for (int i = 0; i < lista.size(); i++) array[i] = lista.get(i);
        return array;
    }


}
