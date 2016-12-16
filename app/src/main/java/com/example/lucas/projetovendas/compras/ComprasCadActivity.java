package com.example.lucas.projetovendas.compras;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lucas.projetovendas.R;
import com.example.lucas.projetovendas.lista.Lista;
import com.example.lucas.projetovendas.mercado.Mercado;
import com.example.lucas.projetovendas.services.gps.GpsService;

import java.io.ByteArrayOutputStream;

public class ComprasCadActivity extends AppCompatActivity {

    private EditText edtProduto, edtPreco, edtQuantidade;
    private ComprasDAO compraDAO;
    private String srtFoto;
    private ImageView imFoto;
    private String srtLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras_cad);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String txt = bundle.getString("txt");
        srtLista = txt;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtProduto = (EditText) findViewById(R.id.compra_cad_edtProduto);
        edtPreco = (EditText) findViewById(R.id.compra_cad_edtPreco);
        edtQuantidade = (EditText) findViewById(R.id.compra_cad_edtQuantidade);

        imFoto = (ImageView) findViewById(R.id.compra_cad_foto_preco);

        //instancia do ComprasDAO
        compraDAO = new ComprasDAO(this);
    }

    //todo evento criado para botao em tela espera uma view
    public void salvarCompra(View v) {

        Compras c = new Compras();
        c.setProduto(edtProduto.getText().toString());
        c.setPreco(Double.valueOf(edtPreco.getText().toString()));
        c.setQuantidade(Double.valueOf(edtQuantidade.getText().toString()));
        c.setFoto(srtFoto);
        c.setLista(srtLista);
        c.setTotal(0.0);
        compraDAO.salvar(c);

        Log.i("Compra", "Salva com sucesso");
        finish();
    }

    public void capturarFoto(View v) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");

            imFoto.setImageBitmap(bitmap);


            //converte a imagem para string para enviar ao banco
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

            byte[] bytes = baos.toByteArray();
            srtFoto = Base64.encodeToString(bytes, Base64.DEFAULT);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Produto", edtProduto.getText().toString());
        outState.putString("Preco", edtPreco.getText().toString());
        outState.putString("Quantidade", edtQuantidade.getText().toString());
        Log.i("bundle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        edtProduto.setText(bundle.getString("Produto"));
        edtPreco.setText(bundle.getString("Preco"));
        edtQuantidade.setText(bundle.getString("Quantidade"));
        Log.i("bundle", "restore");
    }
}
