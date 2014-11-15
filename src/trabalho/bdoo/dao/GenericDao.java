/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.bdoo.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 *
 * @author Lucas Whiather
 * @param <T>
 */
public class GenericDao<T> {

    protected ObjectContainer db;

    public GenericDao() {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BDOO.yap");
    }

    protected void conectar() throws Exception {
    }
    
    protected void desconectar() throws Exception {
        db.commit();
        db.close();
    }
    

    public void salvar(T object) throws Exception {
        conectar();
        db.store(object);
    }

    public void excluir(T object) throws Exception {
        conectar();
        db.delete(object);
    }

    @Override
    protected void finalize() throws Throwable {
        desconectar();
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
