/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho.bdoo.controle;

import com.db4o.ObjectContainer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabalho.bdoo.dao.ClienteDao;
import trabalho.bdoo.dao.ItemDao;
import trabalho.bdoo.dao.PedidoDao;
import trabalho.bdoo.modelo.Cliente;
import trabalho.bdoo.modelo.Item;
import trabalho.bdoo.modelo.ItemPedido;
import trabalho.bdoo.modelo.Pedido;
import trabalho.bdoo.visao.VisaoPedido;

/**
 *
 * @author Lucas Whiather
 */
public class ControladorPedido implements ActionListener{
   
    private VisaoPedido visaoPedido;
    private Pedido pedido;
    private List<Pedido> listaPedido;
    private List<Cliente> listaCliente;
    private List<Item> listaItem;
    private PedidoDao pedidoDao;
    private Cliente filtroCliente;
    private Double filtroTotal;

    public ControladorPedido() {
    }

    public void iniciar(ObjectContainer db) {

        visaoPedido = new VisaoPedido();
        novo();
        pedidoDao = new PedidoDao(db);
        
        try {
            listaCliente = new ClienteDao(db).buscarTodos(null, null);
            visaoPedido.listarCliente(listaCliente);
            listaItem = new ItemDao(db).buscarTodos(null, null);
            visaoPedido.listarItem(listaItem);
        } catch (Exception ex) {
            System.out.println("Erro ao listar - "+ex);
        }

//listar();

        //BOTÕES
        visaoPedido.getjBtnCadastrar().addActionListener(this);
        visaoPedido.getjBtnAdicionarItem().addActionListener(this);        
        visaoPedido.getjBtnEditar().addActionListener(this);
        visaoPedido.getjBtnExcluir().addActionListener(this);
        visaoPedido.getjBtnFiltrar().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(visaoPedido.getjBtnCadastrar())) {
            salvar();
        } else if (e.getSource().equals(visaoPedido.getjBtnExcluir())) {
            excluir();
        }else if (e.getSource().equals(visaoPedido.getjBtnEditar())) {
            editar();
        }else if (e.getSource().equals(visaoPedido.getjBtnFiltrar())) {
            listar();
        }else if (e.getSource().equals(visaoPedido.getjBtnAdicionarItem())) {
            adicionarItem();
        }
        

    }
    
    private void adicionarItem(){
        Item item = (Item) visaoPedido.getjCmbBoxItem().getSelectedItem();
        if(item==null){
            visaoPedido.apresentarMensagem("Selecione um item.");
            return;
        }
        
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setItem(item);
        
        
    }
    
    private void salvar() {
        
        Cliente cliente = (Cliente) visaoPedido.getjCmbBoxCliente().getSelectedItem();
        if(cliente==null){
            visaoPedido.apresentarMensagem("Selecione um cliente.");
            return;
        }
        
        if(pedido.getItenspedido().isEmpty()){
            visaoPedido.apresentarMensagem("No mínimo um item no pedido.");
            return;
        }
        
        
        try {
            pedido.setCliente(cliente);
            pedidoDao.salvar(pedido);
            listar();            
            visaoPedido.apresentarMensagem("Pedido salvo com sucesso.");
        } catch (Exception ex) {
            visaoPedido.apresentarMensagem("Ocorreu um erro.");
            System.out.println("Erro ao salvar - " + ex);
        }
    }
    
    private void editar(){
          try {
            int indice = visaoPedido.getjTablePedido().getSelectedRow();

            if (indice >= 0) {
                pedido = listaPedido.get(indice);
                visaoPedido.setPedido(pedido);
            } else {
                visaoPedido.apresentarMensagem("Selecione um pedido.");
            }

        } catch (Exception ex) {
            visaoPedido.apresentarMensagem("Ocorreu um erro.");
            System.out.println("Erro ao excluir - " + ex);
        }
    }

    private void excluir() {
        try {
            int indice = visaoPedido.getjTablePedido().getSelectedRow();

            if (indice >= 0) {
                pedido = listaPedido.get(indice);
                pedidoDao.excluir(pedido);
                novo();
                visaoPedido.setPedido(pedido);
                listar();
                visaoPedido.apresentarMensagem("Pedido excluido com sucesso.");
            } else {
                visaoPedido.apresentarMensagem("Selecione um pedido.");
            }

        } catch (Exception ex) {
            visaoPedido.apresentarMensagem("Ocorreu um erro.");
            System.out.println("Erro ao excluir - " + ex);
        }
    }
    
    private void novo(){
        pedido = new Pedido();
        pedido.setItenspedido(new ArrayList<ItemPedido>());
    }

    private void listar() {
        filtroCliente = null;
        filtroTotal = null;
        try {
            listaPedido = pedidoDao.buscarTodos(filtroCliente, filtroTotal);
            //visaoPedido.listar(listaPedido);
        } catch (Exception ex) {
            System.out.println("Erro ao listar - " + ex.getMessage());
        }
    }
}
