/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.bdoo.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import trabalho.bdoo.dao.ClienteDao;
import trabalho.bdoo.modelo.Cliente;
import trabalho.bdoo.visao.VisaoCliente;

/**
 *
 * @author Lucas Whiather
 */
public class ControladorCliente implements ActionListener {

    private VisaoCliente visaoCliente;
    private Cliente cliente;
    private List<Cliente> listaCliente;
    private ClienteDao clienteDao;
    private String filtroNome;
    private Integer filtroTelefone;

    public ControladorCliente() {
    }

    public void iniciar() {

        visaoCliente = new VisaoCliente();
        cliente = new Cliente();
        clienteDao = new ClienteDao();
        listar();

        //BOTÕES
        visaoCliente.getjBtnCadastrar().addActionListener(this);
        visaoCliente.getjBtnEditar().addActionListener(this);
        visaoCliente.getjBtnExcluir().addActionListener(this);
        visaoCliente.getjBtnFiltrar().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(visaoCliente.getjBtnCadastrar())) {
            salvar();
        } else if (e.getSource().equals(visaoCliente.getjBtnExcluir())) {
            excluir();
        }else if (e.getSource().equals(visaoCliente.getjBtnEditar())) {
            editar();
        }else if (e.getSource().equals(visaoCliente.getjBtnFiltrar())) {
            listar();
        }
        

    }

    private void salvar() {
        try {
            visaoCliente.getCliente(cliente);
            clienteDao.salvar(cliente);
            listar();            
            visaoCliente.apresentarMensagem("Cliente salvo com sucesso.");
        } catch (Exception ex) {
            visaoCliente.apresentarMensagem("Ocorreu um erro.");
            System.out.println("Erro ao salvar - " + ex);
        }
    }
    
    private void editar(){
          try {
            int indice = visaoCliente.getjTableCliente().getSelectedRow();

            if (indice >= 0) {
                cliente = listaCliente.get(indice);
                visaoCliente.setCliente(cliente);
            } else {
                visaoCliente.apresentarMensagem("Selecione um cliente.");
            }

        } catch (Exception ex) {
            visaoCliente.apresentarMensagem("Ocorreu um erro.");
            System.out.println("Erro ao excluir - " + ex);
        }
    }

    private void excluir() {
        try {
            int indice = visaoCliente.getjTableCliente().getSelectedRow();

            if (indice >= 0) {
                cliente = listaCliente.get(indice);
                System.out.println("nome = "+cliente.getNome());
                System.out.println("telefone = "+cliente.getTelefone());
                clienteDao.excluir(cliente);
                cliente = new Cliente();
                visaoCliente.setCliente(cliente);
                listar();
                visaoCliente.apresentarMensagem("Cliente excluido com sucesso.");
            } else {
                visaoCliente.apresentarMensagem("Selecione um cliente.");
            }

        } catch (Exception ex) {
            visaoCliente.apresentarMensagem("Ocorreu um erro.");
            System.out.println("Erro ao excluir - " + ex);
        }
    }

    private void listar() {
        filtroNome = visaoCliente.getFiltroNome();
        filtroTelefone = visaoCliente.getFiltroTelefone();
        try {
            listaCliente = clienteDao.buscarTodos(filtroNome, filtroTelefone);
            visaoCliente.listar(listaCliente);
        } catch (Exception ex) {
            System.out.println("Erro ao listar - " + ex.getMessage());
        }
    }

}
