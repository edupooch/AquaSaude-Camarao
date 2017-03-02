package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.gov.rs.fepagro.aquasaude.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultadoChecklistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadoChecklistFragment extends android.app.Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ITENS_DESMARCADOS = "desmarcados";

    private int[] itens;
    private View view;


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
    public static android.app.Fragment newInstance(int[] itensDesmarcados) {
        ResultadoChecklistFragment fragment = new ResultadoChecklistFragment();
        Bundle args = new Bundle();
        args.putIntArray(ARG_ITENS_DESMARCADOS, itensDesmarcados);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itens = getArguments().getIntArray(ARG_ITENS_DESMARCADOS);

    }

    @Override
    public void onResume() {
        super.onResume();
        escondeBarra();
    }

    private void escondeBarra() {
        AppBarLayout bar = (AppBarLayout) getActivity().findViewById(R.id.app_bar_layout);
        if (bar != null) {
            bar.setVisibility(View.GONE);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_resultado_checklist, container, false);

        int[] stringsResId = new int[]{R.string.resposta_1, R.string.resposta_2, R.string.resposta_3,
                R.string.resposta_4, R.string.resposta_5, R.string.resposta_6, R.string.resposta_7,
                R.string.resposta_8, R.string.resposta_9, R.string.resposta_10};

        if (itens == null || itens.length == 0) {
            // O usuário marcou todas as opções
            TextView textTitulo = (TextView) view.findViewById(R.id.dicas_titulo);
            textTitulo.setText(R.string.parabens);
        } else {
            //Mostrar dicas personalizadas com base nas marcações
            StringBuilder builderDicas = new StringBuilder();
            for (int indice : itens) {
                String dica = getString(stringsResId[indice]);
                builderDicas.append(dica).append("\n\n");
            }
            String dicas = builderDicas.toString();
            TextView textViewDicas = (TextView) view.findViewById(R.id.dicas);
            textViewDicas.setText(dicas);
        }

        //Texto com quantos porcento a fazenda está biossegura
        TextView resultado = (TextView) view.findViewById(R.id.text_resultado);
        int nota = (10 - itens.length)*10;
        resultado.setText(resultado.getText().toString().replace("x", String.valueOf(nota)));


        Button btOk = (Button) view.findViewById(R.id.bt_ok);
        btOk.setOnClickListener(v -> getActivity().finish());
        return view;
    }
}
