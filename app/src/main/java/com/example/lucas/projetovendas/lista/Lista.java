package com.example.lucas.projetovendas.lista;

/**
 * Created by lucas on 13/12/16.
 */

public class Lista {

    private Long id;
    private String nome;
    private String mercado;
    private String data;
    private Double total;

    public static final String ID = "_id";
    public static final String NOME = "l_nome";
    public static final String MERCADO = "l_mercado";
    public static final String DATA = "l_data";
    public static final String TOTALLISTA = "l_total";
    public static final String TABELA = "nomeLista";
    public static final String[] COLUNAS = {ID, NOME, MERCADO, DATA, TOTALLISTA};


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
