package classe;

import entity.GenericEntity;

import javax.persistence.*;

public class Conexao {

    //nome do banco
    private static EntityManagerFactory factory;
    private static EntityManager em;

    public static void abrirConexao(String pNomeBanco) {
        //começa com o estado TRANSIENT
        //nome do banco
        factory = Persistence.createEntityManagerFactory(pNomeBanco);
        Conexao.em = factory.createEntityManager();
    }

    public static void begin() {

        /*como no persistence, esta configurado como transaction-type="RESOURCE_LOCAL", as transações devem ser feitas na mão,
        ou seja, vai precisar dar um begin e um commit.
         */
        Conexao.em.getTransaction().begin();
    }

    public static void insert(GenericEntity pEntity) {
        //muda para o estado MANAGED
        //faz o insert no banco na tabela produto
        Conexao.em.persist(pEntity);
    }

    public static void commit() {
        //grava a alteração no banco de dados
        Conexao.em.getTransaction().commit();
    }

    public static void synchronize() {
        //sincroniza a informação no banco de dados
        Conexao.em.flush();
    }

    public static void rollback() {
        Conexao.em.getTransaction().rollback();
    }

    public static GenericEntity merge(GenericEntity pEntity) {
        //volta o estado de DETACHED para MANAGED
        //é feito um SELECT para buscar no banco o objeto para que possa ser feito uma nova operação
        return Conexao.em.merge(pEntity);
    }

    public static Object consult(Class pClass, Object pId) {
        //busca a informação do banco de dados
        return Conexao.em.find(pClass, pId);
    }

    public static Query consult(Class pClass, String pQuery) {
        //busca a informação do banco de dados
        return Conexao.em.createQuery(pQuery, pClass);
    }

    public static void delete(GenericEntity pEntity) {
        //passa do estado MANAGED para o REMOVED
        //remove o objeto do banco
        Conexao.em.remove(pEntity);
    }

    public static void close() {
        //muda para o estado DETACHED
        Conexao.em.close();
    }

    public static void clear() {
        //muda para o estado DETACHED
        Conexao.em.clear();
    }
}
