package com.example.lucas.projetovendas.lista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lucas.projetovendas.R;
import com.example.lucas.projetovendas.mercado.Mercado;
import com.example.lucas.projetovendas.mercado.MercadoDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ListaCadActivity extends AppCompatActivity {

    private EditText edtNome, edtData;
    private Spinner spnMercados;
    private ListaDAO listaDAO;
    private MercadoDAO mercadoDAO;
    private Mercado mercado;
    private String nome;
    private List<String> nomes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cad);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNome = (EditText) findViewById(R.id.lista_cad_edtNome);
        edtData = (EditText) findViewById(R.id.lista_cad_edtData);
        spnMercados = (Spinner) findViewById(R.id.lista_cad_spinner);

        mercadoDAO = new MercadoDAO(this);
        //Adicionando Nomes no ArrayList
        List<Mercado> mercadoList = mercadoDAO.listar();
        mercadoList.iterator().next();


        //usado para preeencher o spinner
        for (Iterator iterator = mercadoList.iterator(); iterator.hasNext(); ) {
            Mercado mercado = (Mercado) iterator.next();
            String nome = mercado.getNome_mercado();
            nomes.add(nome);
        }

        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnMercados.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        spnMercados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                nome = parent.getItemAtPosition(posicao).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edtData.setText(getDateTime());

        listaDAO = new ListaDAO(this);
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void salvarLista(View v) {
        Lista l = new Lista();
        l.setNome(edtNome.getText().toString());
        l.setData(edtData.getText().toString());
        l.setMercado(spnMercados.getSelectedItem().toString());
        l.setTotal(0.0);

        listaDAO.salvar(l);

        Log.i("Lista", "Salva com sucesso");
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Nome", edtNome.getText().toString());
        outState.putString("Data", edtData.getText().toString());
        Log.i("bundle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        edtNome.setText(bundle.getString("Nome"));
        edtData.setText(bundle.getString("Data"));
        Log.i("bundle", "restore");
    }

}
