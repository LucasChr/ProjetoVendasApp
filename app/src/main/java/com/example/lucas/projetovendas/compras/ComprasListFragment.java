package com.example.lucas.projetovendas.compras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lucas.projetovendas.Lista.ListaComprasListFragment;
import com.example.lucas.projetovendas.R;

import java.util.List;


public class ComprasListFragment extends Fragment implements AdapterView.OnItemClickListener{

    List<Compras> compras;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_compras_list,container, false);

        ListView listView = (ListView) view.findViewById(R.id.fragment_compras_list_listview);

        //listView.setEmptyView();

        ComprasDAO comprasDAO = new ComprasDAO(getActivity());
        List<Compras> comprasList = comprasDAO.listar();

        ComprasListAdapter adapter = new ComprasListAdapter(getActivity(),
                R.layout.fragment_compras_list_item, comprasList);

        listView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {

        Compras compra = compras.get(position);

        Intent it = new Intent(getActivity(),ListaComprasListFragment.class);
        String id1 = String.valueOf(compra.getId());
        it.putExtra(Compras.ID,id1);
        startActivityForResult(it,1);
        //Toast.makeText(getActivity(),denuncia.getDescricao(),Toast.LENGTH_LONG).show();
    }


}
