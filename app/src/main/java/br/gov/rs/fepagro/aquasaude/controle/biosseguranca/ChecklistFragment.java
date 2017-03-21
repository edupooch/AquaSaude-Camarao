package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;


import java.util.ArrayList;

import br.gov.rs.fepagro.aquasaude.R;

/**
 * Classe que controla a tela do checklist de boas práticas de biossegurança, onde o usuário
 * marcará nos CheckBoxes quais ações são realizadas em sua fazenda.
 * Essa classe chamará depois a classe {@link ResultadoChecklistFragment} que mostrará a porcentagem
 * de biossegurança da fazenda e dicas personalizadas de acordo com as marcações.
 * <p>
 * Created by edupooch on 22/02/2017. Contato: edupooch@gmail.com
 */
public class ChecklistFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);

        Button btConfirma = (Button) view.findViewById(R.id.bt_confirma_checklist);
        btConfirma.setOnClickListener(v -> iniciaTelaDeResultados(view));

        return view;
    }

    private void iniciaTelaDeResultados(View telaChecklist) {
        ArrayList<Integer> itensDesmarcados = getItensDesmarcados(telaChecklist);

        Fragment fragment = ResultadoChecklistFragment.newInstance(itensDesmarcados);
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    /**
     * Pega-se os itens desmarcados no checklist pois serão mostradas dicas que ressaltam a
     * importância da realização dessas boas práticas.
     *
     * @return int array com os itens desmarcados
     */

    @NonNull
    private ArrayList<Integer> getItensDesmarcados(View view) {
        ArrayList<Integer> listaItensDesmarcados = new ArrayList<>();

        int[] checkBoxResId = getIdsDasCheckBoxes();
        int quantidadeDeItens = checkBoxResId.length;
        for (int i = 0; i < quantidadeDeItens; i++) {
            int resId = checkBoxResId[i];
            CheckBox checkBox = (CheckBox) view.findViewById(resId);
            if (!checkBox.isChecked()) {
                listaItensDesmarcados.add(i);
            }
        }
        return listaItensDesmarcados;
    }


    /**
     * Recuperei a informação em um array para poder verificar num laço for todas as que não estão
     * marcadas, ao invés de verificar uma por uma.
     *
     * @return um int array com os IDs das checkboxes no layout
     */
    private int[] getIdsDasCheckBoxes() {
        return new int[]{R.id.checkbox_1, R.id.checkbox_2, R.id.checkbox_3,
                R.id.checkbox_4, R.id.checkbox_5, R.id.checkbox_6, R.id.checkbox_7, R.id.checkbox_8,
                R.id.checkbox_9, R.id.checkbox_10};
    }


}
