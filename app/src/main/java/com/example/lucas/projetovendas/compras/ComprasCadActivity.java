package com.example.lucas.projetovendas.compras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.lucas.projetovendas.R;

public class ComprasCadActivity extends AppCompatActivity {

    private EditText edtProduto, edtPreco, edtQuantidade;
    private ComprasDAO compraDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras_cad);

        edtProduto = (EditText) findViewById(R.id.compra_cad_edtProduto);
        edtPreco = (EditText) findViewById(R.id.compra_cad_edtPreco);
        edtQuantidade = (EditText) findViewById(R.id.compra_cad_edtQuantidade);

        //instancia do DenunciaDAO
        compraDAO = new ComprasDAO(this);

    }

    //todo evento criado para botao em tela espera uma view
    public void salvarCompra(View v){
        Compras c = new Compras();
        c.setProduto(edtProduto.getText().toString());
        c.setPreco(Double.valueOf(edtPreco.getText().toString()));
        c.setQuantidade(Integer.valueOf(edtQuantidade.getText().toString()));


        compraDAO.salvar(c);

        Log.i("Compra","Salva com sucesso");
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
