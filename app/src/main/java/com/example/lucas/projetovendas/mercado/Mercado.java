package com.example.lucas.projetovendas.mercado;

/**
 * Created by lucas on 08/12/16.
 */

public class Mercado {

    private Long id;
    private String nome_mercado;
    private String telefone;
    private String latitude;
    private String longitude;
    private String foto;

    public static final String ID = "_id";
    public static final String NOMEMERCADO = "m_mercado";
    public static final String TELEFONE = "m_telefone";
    public static final String LATITUDE = "m_latitude";
    public static final String LONGITUDE = "m_longitude";
    public static final String FOTO = "m_foto";

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public static final String TABELA = "nomeMercado";
    public static final String[] COLUNAS = {ID, NOMEMERCADO, TELEFONE, LATITUDE, LONGITUDE, FOTO};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_mercado() {
        return nome_mercado;
    }

    public void setNome_mercado(String nome_mercado) {
        this.nome_mercado = nome_mercado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
