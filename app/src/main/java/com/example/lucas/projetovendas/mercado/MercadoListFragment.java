package com.example.lucas.projetovendas.mercado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucas.projetovendas.R;


import java.util.List;

public class MercadoListFragment extends Fragment implements AdapterView.OnItemClickListener{

    private List<Mercado> mercados;
    private MercadoDAO mercadoDAO;
    private MercadoListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mercado_list, container, false);

        ListView listView = (ListView) view.findViewById(R.id.fragment_mercado_list_listview);
        listView.setEmptyView(view.findViewById(android.R.id.empty));
        listView.setOnItemClickListener(this);
        //listView.setEmptyView();

        mercadoDAO = new MercadoDAO(getActivity());
        mercados = mercadoDAO.listar();

        adapter = new MercadoListAdapter(getActivity(),
                R.layout.fragment_mercado_list_item, mercados);

        listView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Mercado mercado = mercados.get(position);

        Intent it = new Intent(getActivity(), MercadoActivity.class);
        String id1 = String.valueOf(mercado.getId());
        it.putExtra(Mercado.ID, id1);
        startActivityForResult(it,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        atualizaLista();
        if (resultCode == 1) {
            Toast.makeText(getActivity(), "Salvo com sucesso", Toast.LENGTH_LONG).show();
        } else if (resultCode == 2) {
            Toast.makeText(getActivity(), "Modificado com sucesso", Toast.LENGTH_LONG).show();
        } else if (resultCode == 3 ){
            Toast.makeText(getActivity(), "Excluído com sucesso", Toast.LENGTH_LONG).show();
        }
    }

    public void atualizaLista() {
        List<Mercado> tu = mercadoDAO.listar();
        adapter.clear();
        adapter.addAll(tu);
        adapter.notifyDataSetChanged();
    }

}
