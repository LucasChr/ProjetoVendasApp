package com.example.lucas.projetovendas.compras;

/**
 * Created by lucas on 01/12/16.
 */

public class Compras {


    private Long id;
    private String produto;
    private Double preco;
    private Integer quantidade;
    private String nome_mercado;
    private Double valor_gasto;

    public static final String ID = "_id";
    public static final String PRODUTO = "cp_produto";
    public static final String PRECO = "cp_preco";
    public static final String QUANTIDADE = "cp_quantidade";
    public static final String NOMEMERCADO = "cp_nome_mercado";
    public static final String VALORGASTO = "cp_valor_gasto";

    public static final String TABELA = "comprasMercado";
    public static final String[] COLUNAS = {ID, PRODUTO, PRECO, QUANTIDADE, NOMEMERCADO, VALORGASTO};

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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome_mercado() {
        return nome_mercado;
    }

    public void setNome_mercado(String nome_mercado) {
        this.nome_mercado = nome_mercado;
    }

    public Double getValor_gasto() {
        return valor_gasto;
    }

    public void setValor_gasto(Double valor_gasto) {
        this.valor_gasto = valor_gasto;
    }
}
