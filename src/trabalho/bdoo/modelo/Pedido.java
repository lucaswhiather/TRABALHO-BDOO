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
    private List<Item> itens;
    
    public Pedido() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
    
    


    public Double getTotal(){
        Double soma = 0.0;
        
        if(itens==null) return soma;
        
        for(Item it: itens){
            soma += it.getPreco();
        }
        return soma;
    }
    
      
}
