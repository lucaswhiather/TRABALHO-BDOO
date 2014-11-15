/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho.bdoo.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import trabalho.bdoo.visao.VisaoPrincipal;

/**
 *
 * @author Lucas Whiather
 */
public class ControladorPrincipal implements ActionListener{

    VisaoPrincipal visaoPrincipal;
    
    public ControladorPrincipal() {
    }

    public void iniciar(){
        visaoPrincipal = new VisaoPrincipal();
        
        //BOTÕES
        visaoPrincipal.getjBtnCliente().addActionListener(this);
        visaoPrincipal.getjBtnItem().addActionListener(this);
        visaoPrincipal.getjBtnPedido().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(visaoPrincipal.getjBtnCliente())){
            new ControladorCliente().iniciar();
        }else if(e.getSource().equals(visaoPrincipal.getjBtnItem())){
            //new ControladorItem().inicar();
        }else if(e.getSource().equals(visaoPrincipal.getjBtnPedido())){
            //new ControladorPedido().inicar();
        }
    }
    
}
