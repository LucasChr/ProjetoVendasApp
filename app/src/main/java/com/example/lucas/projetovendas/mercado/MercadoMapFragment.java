package com.example.lucas.projetovendas.mercado;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lucas.projetovendas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MercadoMapFragment extends Fragment{

    MarkerOptions markerOptions;
    LatLng latLng;
    MercadoDAO dao;
    Context context;
    List<Mercado> mercados;
    GoogleMap map;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mercados = dao.listar();
/*        if(!mercados.isEmpty()){
            Mercado mercado = new Mercado();

            Double latitude = Double.valueOf(mercado.getLatitude());
            Double longitude = Double.valueOf(mercado.getLongitude());

            chamaLocal(map, latitude, longitude, mercado.getNome_mercado(), mercado.getTelefone());

            /*Double lon = Double.valueOf(mercado.getLatitude());
            Double lat = Double.valueOf(mercado.getLongitude());
            latLng = new LatLng(lat, lon);

            markerOptions = new MarkerOptions();
            markerOptions.title(mercado.getNome_mercado());
            markerOptions.snippet(mercado.getTelefone());
            markerOptions.position(latLng);

            map.addMarker(markerOptions);
            //move o mapa para a latitude e longitude e da zoom no mapa
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));*/
        //}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mercado_map, container, false);
        return view;
    }

    public void chamaLocal(GoogleMap map, Double lat, Double lon, String titulo, String descricao){
        latLng = new LatLng(lat, lon);

        markerOptions = new MarkerOptions();
        markerOptions.title(titulo);
        markerOptions.snippet(descricao);
        markerOptions.position(latLng);

        map.addMarker(markerOptions);
        //move o mapa para a latitude e longitude e da zoom no mapa
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
    }
}
