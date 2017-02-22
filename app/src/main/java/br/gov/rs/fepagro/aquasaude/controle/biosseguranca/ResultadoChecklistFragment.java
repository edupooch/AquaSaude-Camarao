package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.gov.rs.fepagro.aquasaude.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultadoChecklistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadoChecklistFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ITENS_DESMARCADOS = "desmarcados";

    private int[] itens;



    public ResultadoChecklistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param itensDesmarcados itens que não foram marcados na checklist.
     * @return A new instance of fragment ResultadoChecklistFragment.
     */
    public static ResultadoChecklistFragment newInstance(int[] itensDesmarcados) {
        ResultadoChecklistFragment fragment = new ResultadoChecklistFragment();
        Bundle args = new Bundle();
        args.putIntArray(ARG_ITENS_DESMARCADOS, itensDesmarcados);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            itens = getArguments().getIntArray(ARG_ITENS_DESMARCADOS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resultado_checklist, container, false);


        return view;
    }




}
