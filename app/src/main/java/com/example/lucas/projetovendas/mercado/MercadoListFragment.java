package com.example.lucas.projetovendas.mercado;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lucas.projetovendas.R;
import com.example.lucas.projetovendas.compras.Compras;
import com.example.lucas.projetovendas.compras.ComprasDAO;
import com.example.lucas.projetovendas.compras.ComprasListAdapter;

import java.util.List;

public class MercadoListFragment extends Fragment {

    List<Mercado> mercados;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mercado_list, container, false);

        ListView listView = (ListView) view.findViewById(R.id.fragment_mercado_list_listview);

        //listView.setEmptyView();

        MercadoDAO mercadoDAO = new MercadoDAO(getActivity());
        mercados = mercadoDAO.listar();

        MercadoListAdapter adapter = new MercadoListAdapter(getActivity(),
                R.layout.fragment_mercado_list_item, mercados);

        listView.setAdapter(adapter);

        return view;

    }

}
