/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho.bdoo.controle;

import com.db4o.ObjectContainer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import trabalho.bdoo.dao.ItemDao;
import trabalho.bdoo.modelo.Item;
import trabalho.bdoo.visao.VisaoItem;

/**
 *
 * @author Lucas Whiather
 */
public class ControladorItem implements ActionListener{
    private VisaoItem visaoItem;
    private Item item;
    private List<Item> listaItem;
    private ItemDao itemDao;
    private String filtroNome;
    private Double filtroPreco;

    public ControladorItem() {
    }

    public void iniciar(ObjectContainer db) {

        visaoItem = new VisaoItem();
        item = new Item();
        itemDao = new ItemDao(db);
        listar();

        //BOTÕES
        visaoItem.getjBtnNovo().addActionListener(this);
        visaoItem.getjBtnCadastrar().addActionListener(this);
        visaoItem.getjBtnEditar().addActionListener(this);
        visaoItem.getjBtnExcluir().addActionListener(this);
        visaoItem.getjBtnFiltrar().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(visaoItem.getjBtnCadastrar())) {
            salvar();
        } else if (e.getSource().equals(visaoItem.getjBtnExcluir())) {
            excluir();
        }else if (e.getSource().equals(visaoItem.getjBtnEditar())) {
            editar();
        }else if (e.getSource().equals(visaoItem.getjBtnFiltrar())) {
            listar();
        }else if (e.getSource().equals(visaoItem.getjBtnNovo())) {
            novo();
        }
        

    }
    
    public void novo(){
        item = new Item();
        visaoItem.setItem(item);
    }

    private void salvar() {
        try {
            visaoItem.getItem(item);
            itemDao.salvar(item);
            listar();            
            visaoItem.apresentarMensagem("Item salvo com sucesso.");
        } catch (Exception ex) {
            visaoItem.apresentarMensagem("Ocorreu um erro.");
            System.out.println("Erro ao salvar - " + ex);
        }
    }
    
    private void editar(){
          try {
            int indice = visaoItem.getjTableItem().getSelectedRow();

            if (indice >= 0) {
                item = listaItem.get(indice);
                visaoItem.setItem(item);
            } else {
                visaoItem.apresentarMensagem("Selecione um item.");
            }

        } catch (Exception ex) {
            visaoItem.apresentarMensagem("Ocorreu um erro.");
            System.out.println("Erro ao excluir - " + ex);
        }
    }

    private void excluir() {
        try {
            int indice = visaoItem.getjTableItem().getSelectedRow();

            if (indice >= 0) {
                item = listaItem.get(indice);
                itemDao.excluir(item);
                novo();
                listar();
                visaoItem.apresentarMensagem("Item excluido com sucesso.");
            } else {
                visaoItem.apresentarMensagem("Selecione um item.");
            }

        } catch (Exception ex) {
            visaoItem.apresentarMensagem("Ocorreu um erro.");
            System.out.println("Erro ao excluir - " + ex);
        }
    }

    private void listar() {
        filtroNome = visaoItem.getFiltroNome();
        filtroPreco = visaoItem.getFiltroPreco();
        try {
            listaItem = itemDao.buscarTodos(filtroNome, filtroPreco);
            visaoItem.listar(listaItem);
        } catch (Exception ex) {
            System.out.println("Erro ao listar - " + ex.getMessage());
        }
    }
}
