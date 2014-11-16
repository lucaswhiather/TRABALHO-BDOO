/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.bdoo.modelo;

import java.io.Serializable;

/**
 *
 * @author Lucas Whiather
 */
public class Item implements Serializable {

    private String nome;
    private Double preco;

    public Item() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
