/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho.bdoo.modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Lucas Whiather
 */
public class Pedido implements Serializable{
    private Integer id;
    private Cliente cliente;
    private List<ItemPedido> itenspedido;
    
    public Pedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
}
