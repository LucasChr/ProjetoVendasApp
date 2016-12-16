package com.example.lucas.projetovendas.mercado;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.lucas.projetovendas.R;
import com.example.lucas.projetovendas.services.gps.GpsService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Iterator;
import java.util.List;

public class MercadoMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MercadoDAO dao;
    private EditText edtBusca;
    private GpsService gps;
    private List<Mercado> mercadoList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado_map);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dao = new MercadoDAO(this);
        gps = new GpsService(this);

        mercadoList = dao.listar();
        edtBusca = (EditText) findViewById(R.id.fragment_mercado_mapa_edtPesquisa);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mercadoList.iterator().next();

        for (Iterator iterator = mercadoList.iterator(); iterator.hasNext(); ) {
            Mercado mercado = (Mercado) iterator.next();
            LatLng latLng = new LatLng(Double.valueOf(mercado.getLatitude()), Double.valueOf(mercado.getLongitude()));

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(mercado.getNome_mercado());
            markerOptions.snippet(mercado.getTelefone());
            markerOptions.position(latLng);

            //adiciona o marcador no mapa
            googleMap.addMarker(markerOptions);
            //move o mapa para a latitude e longitude e da zoom no mapa
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        }
        //Captura a localizacao da pessoa no momento
        googleMap.setMyLocationEnabled(true);
    }

    public void buscarMercado(View v) {
        Intent intent = new Intent(this, MercadoMapa.class);
        String txt = edtBusca.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("txt", txt);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
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
}
