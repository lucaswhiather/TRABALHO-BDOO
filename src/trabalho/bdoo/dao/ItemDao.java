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
import trabalho.bdoo.modelo.Item;

/**
 *
 * @author Lucas Whiather
 */
public class ItemDao extends GenericDao<Item> {

    public ItemDao(ObjectContainer db) {
        this.db = db;
    }
    
    public List<Item> buscarTodos(final String nome, final Double preco) {
        ObjectSet<Item> lista = db.query(new Predicate<Item>() {

            public boolean match(Item item) {

                if (nome != null && !item.getNome().startsWith(nome)) {
                    return false;
                }

                if (preco != null && !(item.getPreco()<=preco)) {
                    return false;
                }

                return true;
            }

        });

        List<Item> listaI = new ArrayList<Item>();

        while (lista.hasNext()) {
            listaI.add(lista.next());
        }
        return listaI;
    }
    
}
