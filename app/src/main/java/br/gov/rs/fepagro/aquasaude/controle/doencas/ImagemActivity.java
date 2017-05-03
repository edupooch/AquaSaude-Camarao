package br.gov.rs.fepagro.aquasaude.controle.doencas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.github.chrisbanes.photoview.PhotoView;

import br.gov.rs.fepagro.aquasaude.R;

public class ImagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_imagem);

        int imagemResId = getIntent().getIntExtra("imagem", R.drawable.foto_padrao);

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(imagemResId);

    }
}
