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

    private Cliente cliente;
    private List<ItemPedido> itenspedido;
    
    public Pedido() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItenspedido() {
        return itenspedido;
    }

    public void setItenspedido(List<ItemPedido> itenspedido) {
        this.itenspedido = itenspedido;
    }
    
    public Double getTotal(){
        Double soma = 0.0;
        
        if(itenspedido==null) return soma;
        
        for(ItemPedido it: itenspedido){
            soma += it.getItem().getPreco();
        }
        return soma;
    }
    
      
}
