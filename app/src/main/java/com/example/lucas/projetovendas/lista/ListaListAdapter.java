package com.example.lucas.projetovendas.lista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucas.projetovendas.R;

import java.util.List;

/**
 * Created by lucas on 13/12/16.
 */

public class ListaListAdapter extends ArrayAdapter<Lista> {

    private int layout;
    private Context context;
    private List<Lista> listasList;

    public ListaListAdapter(Context context, int layout, List<Lista> listaList) {
        super(context, layout, listaList);
        this.context = context;
        this.layout = layout;
        this.listasList = listaList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(layout, null);

        TextView tvNome = (TextView) itemView.findViewById(R.id.fragment_lista_list_item_tvLista);
        TextView tvMercado = (TextView) itemView.findViewById(R.id.fragment_lista_list_item_tvNomeMercado);
        TextView tvData = (TextView) itemView.findViewById(R.id.fragment_lista_list_item_tvData);

        final Lista listas = listasList.get(position);
        tvNome.setText(listas.getNome());
        tvMercado.setText(listas.getMercado());
        tvData.setText(listas.getData());

        return itemView;
    }

}
