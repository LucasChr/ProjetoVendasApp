package com.example.lucas.projetovendas.principal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lucas.projetovendas.Lista.ListaComprasListFragment;
import com.example.lucas.projetovendas.R;
import com.example.lucas.projetovendas.compras.ComprasCadActivity;
import com.example.lucas.projetovendas.compras.ComprasDAO;
import com.example.lucas.projetovendas.compras.ComprasListFragment;
import com.example.lucas.projetovendas.mercado.MercadoCadActivity;
import com.example.lucas.projetovendas.mercado.MercadoListFragment;
import com.example.lucas.projetovendas.mercado.MercadoMapFragment;
import com.example.lucas.projetovendas.sobre.SobreFragment;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ComprasDAO comprasDAO;
    private boolean isFABOpen;
    FloatingActionButton fab, fab1, fab2, fab3, fab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(PrincipalActivity.this, MercadoCadActivity.class);
                startActivityForResult(it, 1);
            }
        });

        /*fazer*/
        //Cadastrar lista de compras nova
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent it = new Intent(PrincipalActivity.this, ListaComprasListFragment.class);
                //startActivityForResult(it, 2);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(PrincipalActivity.this, ComprasCadActivity.class);
                startActivityForResult(it, 3);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        comprasDAO = new ComprasDAO(this);
        comprasDAO.listar();

        setDisplay(R.id.nav_compras);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_mercado) {
            Intent it = new Intent(PrincipalActivity.this, MercadoCadActivity.class);
            startActivityForResult(it, 1);
        }else if (id == R.id.action_lista){
            Intent it = new Intent(PrincipalActivity.this, ComprasCadActivity.class);
            startActivityForResult(it, 1);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        setDisplay(item.getItemId());

        return true;
    }

    public void setDisplay(int pos) {
        Fragment fragment;

        switch (pos) {
            case R.id.nav_compras: {
                fragment = new ComprasListFragment();
                abrirFragment(fragment, "Compras");
                break;
            }
            case R.id.nav_mercados: {
                fragment = new MercadoListFragment();
                abrirFragment(fragment, "Mercados");
                break;
            }
            case R.id.nav_mapa: {
                fragment = new MercadoMapFragment();
                abrirFragment(fragment, "Mapa de mercados");
                break;
            }
            case R.id.nav_sobre: {
                fragment = new SobreFragment();
                abrirFragment(fragment, "Sobre");
                break;
            }
        }
    }


    public void abrirFragment(Fragment fragment, String title) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            getSupportActionBar().setTitle(title);
        }
    }


    private void showFABMenu() {
        isFABOpen = true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
    }


    private void closeFABMenu() {
        isFABOpen = false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
    }

}
