package com.example.lucas.projetovendas.compras;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucas.projetovendas.R;

public class ComprasActivity extends AppCompatActivity {

    private ComprasDAO comprasDAO;
    private Compras compra;
    private TextView tvProduto, tvPreco, tvQuantidade, tvTotal;
    private ImageView imgCompra;
    private Bitmap ivFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        comprasDAO = new ComprasDAO(this);
        Intent it = getIntent();

        if (it != null) {
            compra = new Compras();
            compra = comprasDAO.buscar(it.getStringExtra(Compras.ID));
        }

        tvProduto = (TextView) findViewById(R.id.activity_compras_tvProduto);
        tvPreco = (TextView) findViewById(R.id.activity_compras_tvPreco);
        tvQuantidade = (TextView) findViewById(R.id.activity_compras_tvQuantidade);
        //tvTotal = (TextView) findViewById(R.id.activity_compras_tvTotal);
        imgCompra = (ImageView) findViewById(R.id.activity_compras_imgCompra);

        byte[] bytearray = Base64.decode(compra.getFoto(), Base64.DEFAULT);
        ivFoto = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.length);
        imgCompra.setImageBitmap(ivFoto);

        tvProduto.setText(compra.getProduto());
        tvPreco.setText(String.valueOf(compra.getPreco()));
        tvQuantidade.setText(String.valueOf(compra.getQuantidade()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    /*public Double calculaTotal(Double preco, Double qnt) {
        Double total = null;
        preco = Double.valueOf(tvPreco.getText().toString());
        qnt = Double.valueOf(tvQuantidade.getText().toString());
        total = preco * qnt;

        return total;
    }*/


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Produto", tvProduto.getText().toString());
        outState.putString("Preco", tvPreco.getText().toString());
        outState.putString("Quantidade", tvQuantidade.getText().toString());
        Log.i("bundle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        tvProduto.setText(bundle.getString("Produto"));
        tvPreco.setText(bundle.getString("Preco"));
        tvQuantidade.setText(bundle.getString("Quantidade"));
        Log.i("bundle", "restore");
    }

}
