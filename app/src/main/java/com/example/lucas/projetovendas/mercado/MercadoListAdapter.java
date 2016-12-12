package com.example.lucas.projetovendas.mercado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucas.projetovendas.R;

import java.util.List;

/**
 * Created by lucas on 08/12/16.
 */

public class MercadoListAdapter extends ArrayAdapter<Mercado> {


    int layout;
    Context context;
    List<Mercado> mercadoList;

    public MercadoListAdapter(Context context, int layout, List<Mercado> mercadosList) {
        super(context, layout, mercadosList);
        this.context = context;
        this.layout = layout;
        this.mercadoList = mercadosList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(layout, null);

        TextView tvNomeMercado = (TextView) itemView.findViewById(R.id.fragment_mercado_list_item_tvNomeMercado);
        TextView tvTelefone = (TextView) itemView.findViewById(R.id.fragment_mercado_list_item_tvTelefone);
        TextView tvLatitude = (TextView) itemView.findViewById(R.id.fragment_mercado_list_item_tvLatitude);

        final Mercado mercado = mercadoList.get(position);
        tvNomeMercado.setText(mercado.getNome_mercado());
        tvTelefone.setText(mercado.getTelefone());
        tvLatitude.setText(mercado.getLatitude());

        return itemView;
    }

}
