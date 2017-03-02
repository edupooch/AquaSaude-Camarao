package br.gov.rs.fepagro.aquasaude.controle.biosseguranca;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;

import br.gov.rs.fepagro.aquasaude.R;

public class PerguntaBiossegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta_biosseg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Context context = getApplicationContext();
        TextSwitcher textSwitcher = (TextSwitcher) findViewById(R.id.text_switcher);

        assert textSwitcher != null;
        textSwitcher.setInAnimation(context, R.anim.slide_in_right);
        textSwitcher.setOutAnimation(context,R.anim.slide_out_left);


        TextView child1 = new TextView(context);
        child1.setTextSize(40);child1.setTextColor(Color.WHITE);

        TextView child2 = new TextView(context);
        child2.setTextSize(40);child2.setTextColor(Color.WHITE);


        textSwitcher.addView(child1);
        textSwitcher.addView(child2);

        View btSim = findViewById(R.id.bt_sim);
        assert btSim != null;
        btSim.setOnClickListener(e -> {
            CharSequence text = getString(R.string.checklist_teste);
            textSwitcher.setText(text);
        });

    }

}
