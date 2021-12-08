package service;

import classe.Conexao;
import entity.ProdutoEntity;
import model.vo.ProdutoConsultaVO;
import repository.ProdutoDAO;

import java.util.List;

public class ProdutoService {

    private final ProdutoDAO produtoDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }

    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void insert(ProdutoEntity pProduto) throws Exception {

        try {
            Conexao.begin();

            Conexao.insert(pProduto);

            Conexao.commit();

        } catch (Exception ex) {
            Conexao.rollback();
            throw new Exception(ex);
        }
    }

    public void update(ProdutoEntity pProduto) throws Exception {

        try {
            Conexao.begin();

            Conexao.merge(pProduto);
            Conexao.synchronize();

            Conexao.commit();

        } catch (Exception ex) {
            Conexao.rollback();
            throw new Exception(ex);
        }
    }

    public ProdutoEntity load(int pIdProduto) throws Exception {

        return (ProdutoEntity) Conexao.consult(ProdutoEntity.class, pIdProduto);
    }

    public List<ProdutoEntity> consult() throws Exception {

        //o SELECT é uma linguagem que chama JPQL
        return Conexao.consult(ProdutoEntity.class, "SELECT p FROM ProdutoEntity AS p").getResultList();
    }

    public List<ProdutoEntity> consult(ProdutoConsultaVO pFiltro) throws Exception {

        return produtoDAO.consult(pFiltro);
    }
}
