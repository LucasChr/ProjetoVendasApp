package com.example.lucas.projetovendas.compras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucas.projetovendas.R;

import java.util.List;

/**
 * Created by lucas on 02/12/16.
 */

public class ComprasListAdapter extends ArrayAdapter<Compras> {



    int layout;
    Context context;
    List<Compras> comprasList;

    public ComprasListAdapter(Context context, int layout, List<Compras> compraList) {
        super(context, layout, compraList);
        this.context = context;
        this.layout = layout;
        this.comprasList = compraList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(layout, null);

        TextView tvProduto = (TextView) itemView.findViewById(R.id.fragment_compra_list_item_tvProduto);
        TextView tvPreco = (TextView) itemView.findViewById(R.id.fragment_compra_list_item_tvPreco);
        TextView tvQuantidade = (TextView) itemView.findViewById(R.id.fragment_compra_list_item_tvQuantidade);

        final Compras compras = comprasList.get(position);
        tvProduto.setText(compras.getProduto());
        tvPreco.setText(compras.getPreco().toString());
        tvQuantidade.setText(compras.getQuantidade().toString());

        return itemView;
    }


}
