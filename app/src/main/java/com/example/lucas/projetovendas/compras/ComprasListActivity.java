package com.example.lucas.projetovendas.compras;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.projetovendas.R;
import com.example.lucas.projetovendas.lista.Lista;
import com.example.lucas.projetovendas.lista.ListaCadActivity;
import com.example.lucas.projetovendas.lista.ListaDAO;

import java.util.List;

public class ComprasListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ComprasDAO comprasDAO;
    private ComprasListAdapter adapter;
    private List<Compras> comprasList;
    private FloatingActionButton fabCompras;
    private Compras compra;
    private String nomeLista;
    private String nomeMercado;
    private Lista lista;
    private ListaDAO listaDAO;
    private TextView tvNomeLista, tvMercado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        comprasDAO = new ComprasDAO(this);
        listaDAO = new ListaDAO(this);
        Intent intent = getIntent();

        if (intent != null) {
            lista = new Lista();
            lista = listaDAO.buscar(intent.getStringExtra(Lista.ID));
            nomeLista = lista.getNome();
            nomeMercado = lista.getMercado();
        }

        tvNomeLista = (TextView) findViewById(R.id.activity_compras_list_tvNome);
        tvMercado = (TextView) findViewById(R.id.activity_compras_list_tvMercado);

        tvNomeLista.setText(nomeLista);
        tvMercado.setText(nomeMercado);

        fabCompras = (FloatingActionButton) findViewById(R.id.fabCompra);

        fabCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ComprasListActivity.this, ComprasCadActivity.class);
                String nome = String.valueOf(lista.getNome());
                Bundle bundle = new Bundle();
                bundle.putString("txt", nome);
                it.putExtras(bundle);
                startActivityForResult(it, 1);
            }
        });

        //Implementa a lista de itens usando AppCompactActivity
        comprasDAO = new ComprasDAO(this);
        comprasList = comprasDAO.listarPorLista(lista.getNome());
        adapter = new ComprasListAdapter(this, R.layout.activity_compras_list_item, comprasList);

        ListView listView = (ListView) findViewById(R.id.activity_compras_list_listview);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.compras, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            /*case 1:
                Intent it = new Intent(this, ComprasCadActivity.class);
                it.putExtra("ID", "");
                startActivityForResult(it, 1);
                break;*/
            case android.R.id.home:
                finish();
                break;
            case R.id.action_compra:
                Intent it = new Intent(ComprasListActivity.this, ComprasCadActivity.class);
                String nome = String.valueOf(lista.getNome());
                Bundle bundle = new Bundle();
                bundle.putString("txt", nome);
                it.putExtras(bundle);
                startActivityForResult(it, 1);
                break;
            case R.id.action_editar:
                Intent it1 = new Intent(this, ListaCadActivity.class);
                String id = String.valueOf(lista.getId());
                Bundle bundle1 = new Bundle();
                bundle1.putString("txt", id);
                it1.putExtras(bundle1);
                startActivityForResult(it1, 2);
                break;


        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Compras compras = comprasList.get(position);

        Intent it = new Intent(this, ComprasActivity.class);
        String id1 = String.valueOf(compras.getId());
        it.putExtra(Compras.ID, id1);
        startActivityForResult(it, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        atualizaLista();
        if (resultCode == 1) {
            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG).show();
        } else if (resultCode == 2) {
            Toast.makeText(this, "Modificado com sucesso", Toast.LENGTH_LONG).show();
        } else if (resultCode == 3) {
            Toast.makeText(this, "Excluído com sucesso", Toast.LENGTH_LONG).show();
        }
    }

    public void atualizaLista() {
        List<Compras> tu = comprasDAO.listar();
        adapter.clear();
        adapter.addAll(tu);
        adapter.notifyDataSetChanged();
    }

}
