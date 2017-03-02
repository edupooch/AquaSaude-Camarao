package br.gov.rs.fepagro.aquasaude.controle;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.controle.biosseguranca.BoasPraticasFragment;
import br.gov.rs.fepagro.aquasaude.controle.doencas.ListaDoencasFragment;
import br.gov.rs.fepagro.aquasaude.controle.jogo.JogoFragment;
import br.gov.rs.fepagro.aquasaude.controle.sobre.SobreFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        abreFragmentPrincipal();

    }

    private void abreFragmentPrincipal() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //Começar com a lista de doenças aberta
        navigationView.setCheckedItem(R.id.item_doencas_camarao);
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new ListaDoencasFragment())
                .commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            abreFragmentPrincipal();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        int id = item.getItemId();
        switch (id) {
            case R.id.item_doencas_camarao:
                mostraSombra();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new ListaDoencasFragment())
                        .commit();
                break;
            case R.id.item_boas_praticas:
                mostraSombra();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new BoasPraticasFragment())
                        .commit();
                break;
            case R.id.nav_jogo:
                escondeSombra();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new JogoFragment())
                        .commit();
                break;
            case R.id.nav_sobre:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new SobreFragment())
                        .commit();
                break;
            case R.id.nav_email:
                enviarEmail();
                break;
            case R.id.nav_share:
                compartilhar();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void compartilhar() {
        String message = getString(R.string.msg_share);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Compartilhar:"));
    }

    /**
     * [VIEW]
     * Ativa a sombra da app bar
     */
    private void mostraSombra() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar_layout);
            assert bar != null;
            bar.setElevation(10);
        }
    }

    /**
     * [VIEW]
     * Esconde a sombra da app bar para o fragment de escolher jogo
     */
    private void escondeSombra() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar_layout);
            assert bar != null;
            bar.setElevation(0);
        }
    }


    /**
     * Método para enviar o email para o aquassaude@gmail.com
     */
    public void enviarEmail() {
        String[] emails = {"aquassaude@gmail.com"};
        String assunto = "Aplicativo AquaSaúde";
        Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
        intentEmail.setData(Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_EMAIL, emails);
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, assunto);
        if (intentEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(intentEmail);
        }
    }

    /**
     * Método para fazer uma animação de rotacioar uma viewContent chamado no atributo onClick do XML na
     * escolha do jogo
     *
     * @param view que será rotacionado
     */
    public void rotate(View view) {
        RotateAnimation rotate = new RotateAnimation(0, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setInterpolator(new LinearInterpolator());

        view.startAnimation(rotate);
    }
}
