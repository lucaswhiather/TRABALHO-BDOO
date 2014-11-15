/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.bdoo.dao;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import com.db4o.query.QueryComparator;
import java.util.ArrayList;
import java.util.List;
import trabalho.bdoo.modelo.Cliente;

/**
 *
 * @author Lucas Whiather
 */
public class ClienteDao extends GenericDao<Cliente> {

    public List<Cliente> buscarTodos(final String nome, final Integer telefone) throws Exception {
        conectar();
        System.out.println("YTER");
        ObjectSet<Cliente> lista = db.query(new Predicate<Cliente>() {
            
            public boolean match(Cliente cliente) {
           
                if (nome != null && !cliente.getNome().startsWith(nome)) {
                    return false;
                }
                
                if (telefone != null && !cliente.getTelefone().equals(telefone)) {
                    return false;
                }

                return true;
            }

        });

        List<Cliente> listaC = new ArrayList<Cliente>();

        while (lista.hasNext()) {
            listaC.add(lista.next());
        }
        return listaC;
    }

}
