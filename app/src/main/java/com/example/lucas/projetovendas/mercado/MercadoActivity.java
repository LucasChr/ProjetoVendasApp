package com.example.lucas.projetovendas.mercado;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucas.projetovendas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MercadoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MercadoDAO mercadoDAO;
    private TextView tvNome, tvTelefone;
    private ImageView imgMercado;
    private Bitmap ivFoto;
    private Mercado mercado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mercadoDAO = new MercadoDAO(this);
        Intent it = getIntent();

        if (it != null) {
            mercado = new Mercado();
            mercado = mercadoDAO.buscar(it.getStringExtra(Mercado.ID));
        }

        tvNome = (TextView) findViewById(R.id.activity_mercado_tvMercadoNome);
        tvTelefone = (TextView) findViewById(R.id.activity_mercado_tvTelefone);
        imgMercado = (ImageView) findViewById(R.id.activity_mercado_imgMercado);

        tvNome.setText(mercado.getNome_mercado());
        tvTelefone.setText(mercado.getTelefone());

        byte[] bytearray = Base64.decode(mercado.getFoto(), Base64.DEFAULT);
        ivFoto = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.length);
        imgMercado.setImageBitmap(ivFoto);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Nome", tvNome.getText().toString());
        outState.putString("Telefone", tvTelefone.getText().toString());
        Log.i("bundle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        tvNome.setText(bundle.getString("Nome"));
        tvTelefone.setText(bundle.getString("Telefone"));
        Log.i("bundle", "restore");
    }
}
