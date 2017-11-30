package com.recycler.patrick.recyclerpatrick.model;

/**
 * Created by trickstival on 23/11/17.
 */

public class Zenfone {

    private String nome, modelo, key;
    private Integer ano;

    public Zenfone(){

    }

    public Zenfone(String nome, Integer ano, String modelo) {
        this.nome = nome;
        this.ano = ano;
        this.modelo = modelo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
