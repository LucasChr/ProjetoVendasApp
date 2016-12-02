package com.example.lucas.projetovendas.compras;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lucas.projetovendas.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComprasListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComprasListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComprasListFragment extends Fragment {


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


}
