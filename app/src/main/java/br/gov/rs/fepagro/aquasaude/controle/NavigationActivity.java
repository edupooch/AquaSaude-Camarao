package br.gov.rs.fepagro.aquasaude.controle;

import android.annotation.SuppressLint;
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

import br.gov.rs.fepagro.aquasaude.R;

public class NavigationActivity extends AppCompatActivity
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
        //---------------------METODOS PARA TROCAR A SOMBRA -------------------------------------//

        //---------------------------------------------------------------------------------------//
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
                String[] endereco = {"aquassaude@gmail.com"};
                composeEmail(endereco, "Aplicativo AquaSaúde");
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void mostraSombra() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar_layout);
            assert bar != null;
            bar.setElevation(10);
        }
    }

    @SuppressLint("NewApi")
    private boolean temSombraNaAppBar() {
        AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar_layout);
        assert bar != null;
        return bar.getElevation() != 0;
    }

    private void escondeSombra() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar_layout);
            assert bar != null;
            bar.setElevation(0);
        }
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
