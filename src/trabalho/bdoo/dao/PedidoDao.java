/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trabalho.bdoo.dao;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import java.util.ArrayList;
import java.util.List;
import trabalho.bdoo.modelo.Cliente;
import trabalho.bdoo.modelo.Pedido;

/**
 *
 * @author Lucas Whiather
 */
public class PedidoDao extends GenericDao<Pedido>{

    public PedidoDao(ObjectContainer db) {
        this.db = db;
    }
    public List<Pedido> buscarTodos(final Cliente cliente, final Double total){
        ObjectSet<Pedido> lista = db.query(new Predicate<Pedido>() {

            public boolean match(Pedido pedido) {

                if (cliente != null && !pedido.getCliente().equals(cliente)) {
                    return false;
                }

                if (total != null && !(pedido.getTotal()<=total)) {
                    return false;
                }

                return true;
            }

        });

        List<Pedido> listaP = new ArrayList<Pedido>();

        while (lista.hasNext()) {
            listaP.add(lista.next());
        }
        return listaP;
    }
}
