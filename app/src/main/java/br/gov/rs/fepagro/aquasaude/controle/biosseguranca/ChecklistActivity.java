package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import br.gov.rs.fepagro.aquasaude.R;

public class ChecklistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View btConfirma = findViewById(R.id.bt_confirma_checklist);
        assert btConfirma != null;
        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChecklistActivity.this, "TODO: realizar análise das respostas", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
