package com.example.lucas.projetovendas.compras;

/**
 * Created by lucas on 01/12/16.
 */

public class Compras {


    private Long id;
    private String produto;
    private Double preco;
    private Double quantidade;
    private String foto;
    private String lista;
    private Double total;

    public static final String ID = "_id";
    public static final String PRODUTO = "cp_produto";
    public static final String PRECO = "cp_preco";
    public static final String QUANTIDADE = "cp_quantidade";
    public static final String FOTO = "cp_foto";
    public static final String LISTA = "cp_lista";
    public static final String TOTAL = "cp_total";

    public static final String TABELA = "comprasMercado";
    public static final String[] COLUNAS = {ID, PRODUTO, PRECO, QUANTIDADE, FOTO, LISTA, TOTAL};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
