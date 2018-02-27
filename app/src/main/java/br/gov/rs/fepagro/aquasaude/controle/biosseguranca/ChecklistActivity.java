package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.controle.doencas.ListaDoencasFragment;

public class ChecklistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Pega o array de itens desmarcados da última vez
        // se for null é pq o usuário selecionou pra preencher o checklist
        // se não é pq ele quer rever o ultimos resultado
        int[] resultados = getIntent().getIntArrayExtra("resultado");
        if (resultados == null) {
            abreFragment(new ChecklistFragment());
        } else {
            abreFragment(ResultadoChecklistFragment.newInstance(resultados));
        }
    }

    private void abreFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }
}
