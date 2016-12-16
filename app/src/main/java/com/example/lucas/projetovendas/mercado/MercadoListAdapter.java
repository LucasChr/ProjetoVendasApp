package com.example.lucas.projetovendas.mercado;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucas.projetovendas.R;

import java.util.List;

/**
 * Created by lucas on 08/12/16.
 */

public class MercadoListAdapter extends ArrayAdapter<Mercado> {


    private int layout;
    private Context context;
    private List<Mercado> mercadoList;

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
        ImageView imgMercado = (ImageView) itemView.findViewById(R.id.fragment_mercado_list_item_imgMercado);

        final Mercado mercado = mercadoList.get(position);
        tvNomeMercado.setText(mercado.getNome_mercado());
        tvTelefone.setText(mercado.getTelefone());


        if (mercado.getFoto() != null) {
            byte[] bytearray = Base64.decode(mercado.getFoto(), Base64.DEFAULT);
            Bitmap bmimage = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.length);
            imgMercado.setImageBitmap(bmimage);
        }

        return itemView;
    }

}
