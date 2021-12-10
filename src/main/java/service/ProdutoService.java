package service;

import repository.Conexao;
import entity.ProdutoEntity;
import model.dto.ProdutoConsultaDto;
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

    public void criarProduto() throws Exception {

        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome("Xiaomi Redmi");
        produto.setDescricao("Xiaomi Redmi Note Pro 10");
        produto.setPreco(800);
        insert(produto);

        produto = new ProdutoEntity();
        produto.setNome("PS5");
        produto.setDescricao("PlayStation 5");
        produto.setPreco(1800);
        insert(produto);

        produto = new ProdutoEntity();
        produto.setNome("MacBook");
        produto.setDescricao("MacBook Pro");
        produto.setPreco(3500);
        insert(produto);
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

    public List<ProdutoEntity> consult(ProdutoConsultaDto pFiltro) throws Exception {

        return produtoDAO.consult(pFiltro);
    }
}
