package com.example.lucas.projetovendas.mercado;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lucas.projetovendas.R;
import com.example.lucas.projetovendas.services.gps.GpsService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;

public class MercadoCadActivity extends AppCompatActivity implements OnMapReadyCallback {

    private EditText edtNome, edtTelefone;
    private ImageView imFoto;
    private MercadoDAO mercadoDAO;
    private String srtFoto;
    private GpsService gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado_cad);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        edtNome = (EditText) findViewById(R.id.mercado_cad_edtNome);
        edtTelefone = (EditText) findViewById(R.id.mercado_cad_edtTelefone);

        imFoto = (ImageView) findViewById(R.id.mercado_cad_foto_mercado);

        //instancia do ComprasDAO
        mercadoDAO = new MercadoDAO(this);
        gps = new GpsService(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void salvarMercado(View v) {
        Mercado m = new Mercado();
        m.setNome_mercado(edtNome.getText().toString());
        m.setTelefone(edtTelefone.getText().toString());
        m.setLatitude(String.valueOf(gps.getLatitude()));
        m.setLongitude(String.valueOf(gps.getLongitude()));
        m.setFoto(srtFoto);

        mercadoDAO.salvar(m);

        Log.i("Mercado", "Salvo com sucesso");
        finish();
    }

    public void capturarFotoMercado(View v) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");

            imFoto.setImageBitmap(bitmap);

            //converte a imagem para string para enviar ao banco
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

            byte[] bytes = baos.toByteArray();
            srtFoto = Base64.encodeToString(bytes, Base64.DEFAULT);
        }

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
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(gps.getLatitude(), gps.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(edtNome.getText().toString());
        markerOptions.snippet(edtTelefone.getText().toString());
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
        outState.putString("Nome", edtNome.getText().toString());
        outState.putString("Telefone", edtNome.getText().toString());
        Log.i("bundle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        edtNome.setText(bundle.getString("Nome"));
        edtTelefone.setText(bundle.getString("Telefone"));
        Log.i("bundle", "restore");
    }
}
