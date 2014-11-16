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

    }

    public GenericDao(ObjectContainer db) {
        this.db = db.ext().openSession();
    }

    public void criarConexao() {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BDOO.yap");
    }

    public void salvar(T object) throws Exception {        
        db.store(object);
        db.commit();
    }

    public void excluir(T object) throws Exception {
        db.delete(object);
        db.commit();
    }

    public ObjectContainer getDb() {
        return db;
    }

    @Override
    protected void finalize() throws Throwable {
        db.commit();
        db.close();
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

}
