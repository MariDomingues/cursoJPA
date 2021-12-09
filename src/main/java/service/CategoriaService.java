package service;

import classe.Conexao;
import entity.CategoriaEntity;

import java.util.List;

public class CategoriaService {

    public void criarCategoria() throws Exception {

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setDescricao("Celular");
        insert(categoria);

        categoria.setDescricao("Celular Internacional");
        update(categoria);

        categoria = new CategoriaEntity();
        categoria.setDescricao("VideoGame");
        insert(categoria);

        categoria = new CategoriaEntity();
        categoria.setDescricao("Informatica");
        insert(categoria);
    }

    public void insert(CategoriaEntity pCategoria) throws Exception {

        try {
            Conexao.begin();

            Conexao.insert(pCategoria);

            Conexao.commit();

        } catch (Exception ex) {
            Conexao.rollback();
            throw new Exception(ex);
        }
    }

    public void update(CategoriaEntity pCategoria) throws Exception {

        try {
            Conexao.begin();

            Conexao.merge(pCategoria);
            Conexao.synchronize();

            Conexao.commit();

        } catch (Exception ex) {
            Conexao.rollback();
            throw new Exception(ex);
        }
    }

    public void delete(CategoriaEntity pCategoria) throws Exception {

        try {
            Conexao.begin();

            CategoriaEntity categoria = (CategoriaEntity) Conexao.merge(pCategoria);
            Conexao.delete(categoria);

            Conexao.commit();

        } catch (Exception ex) {
            Conexao.rollback();
            throw new Exception(ex);
        }
    }

    public CategoriaEntity load(int pIdCategoria) throws Exception {

        return (CategoriaEntity) Conexao.consult(CategoriaEntity.class, pIdCategoria);
    }

    public List<CategoriaEntity> consult() throws Exception {

        //o SELECT é uma linguagem que chama JPQL
        return Conexao.consult(CategoriaEntity.class, "SELECT c FROM CategoriaEntity AS c").getResultList();
    }
}
