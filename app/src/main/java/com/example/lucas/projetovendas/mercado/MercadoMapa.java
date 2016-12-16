package com.example.lucas.projetovendas.mercado;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucas.projetovendas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MercadoMapa extends AppCompatActivity implements OnMapReadyCallback {

    private String srtBusca;
    private TextView tvMercado, tvTelefone;
    private MercadoDAO mercadoDAO;
    private ImageView imgMercado;
    private Bitmap ivFoto;
    private Mercado mercado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado_mapa);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String txt = bundle.getString("txt");
        srtBusca = txt;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mercadoDAO = new MercadoDAO(this);
        Intent it = getIntent();

        //fazer buscar no banco
        if (it != null) {
            mercado = new Mercado();
            mercado = mercadoDAO.buscarNome(srtBusca);
        }

        tvMercado = (TextView) findViewById(R.id.activity_mercado_mapa_tvMercado);
        tvTelefone = (TextView) findViewById(R.id.activity_mercado_mapa_tvTelefone);
        imgMercado = (ImageView) findViewById(R.id.activity_mercado_mapa_imgMercado);

        tvMercado.setText(mercado.getNome_mercado());
        tvTelefone.setText(mercado.getTelefone());

        byte[] bytearray = Base64.decode(mercado.getFoto(), Base64.DEFAULT);
        ivFoto = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.length);
        imgMercado.setImageBitmap(ivFoto);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(Double.valueOf(mercado.getLatitude()), Double.valueOf(mercado.getLongitude()));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(mercado.getNome_mercado());
        markerOptions.snippet(mercado.getTelefone());
        markerOptions.position(latLng);

        //adiciona o marcador no mapa
        googleMap.addMarker(markerOptions);
        //move o mapa para a latitude e longitude e da zoom no mapa
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

        //Captura a localizacao da pessoa no momento
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
