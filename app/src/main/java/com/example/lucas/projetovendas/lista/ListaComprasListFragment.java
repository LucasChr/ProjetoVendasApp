package com.example.lucas.projetovendas.lista;

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
import com.example.lucas.projetovendas.compras.ComprasListActivity;

import java.util.List;


public class ListaComprasListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<Lista> listas;
    private ListaListAdapter adapter;
    private ListaDAO listasDAO;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_compras_list, container, false);

        ListView listView = (ListView) view.findViewById(R.id.fragment_lista_list_listview);
        listView.setEmptyView(view.findViewById(android.R.id.empty));
        listView.setOnItemClickListener(this);
        //listView.setEmptyView();

        listasDAO = new ListaDAO(getActivity());
        List<Lista> listasList = listasDAO.listar();

        adapter = new ListaListAdapter(getActivity(),
                R.layout.fragment_lista_compras_list_item, listasList);

        listView.setAdapter(adapter);

        ListaDAO listaDAO = new ListaDAO(getActivity());
        listas = listasDAO.listar();

        return view;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Lista lista1 = listas.get(position);

        Intent it = new Intent(getActivity(), ComprasListActivity.class);
        String id1 = String.valueOf(lista1.getId());
        it.putExtra(Lista.ID, id1);
        startActivityForResult(it, 1);
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
        List<Lista> tu = listasDAO.listar();
        adapter.clear();
        adapter.addAll(tu);
        adapter.notifyDataSetChanged();
    }
}
