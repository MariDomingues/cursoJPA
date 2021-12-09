package repository;

import classe.Conexao;
import entity.ProdutoEntity;
import model.dto.ProdutoConsultaDto;

import javax.persistence.Query;
import java.util.List;

public class ProdutoDAO {

    public List<ProdutoEntity> consult(ProdutoConsultaDto pFiltro) throws Exception {

        //o SELECT é uma linguagem que chama JPQL
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p");
        jpql.append(" FROM ProdutoEntity AS p");
        jpql.append(" WHERE 1 = 1");

        if (pFiltro.getId() >= 0) {
            jpql.append(" AND p.id = :id");
        }

        if (!pFiltro.getDescricao().isEmpty()) {
            jpql.append(" AND p.descricao = :descricao");
        }

        if (!pFiltro.getNome().isEmpty()) {
            jpql.append(" AND p.nome = :nome");
        }

        if (pFiltro.getPreco() > 0) {
            jpql.append(" AND p.preco = :preco");
        }

        if (!pFiltro.getCategoria().isEmpty()) {
            jpql.append(" AND p.categoria.nome = :categoria");
        }

        Query query = Conexao.consult(ProdutoEntity.class, jpql.toString());

        if (pFiltro.getId() >= 0) {
            query.setParameter("id", pFiltro.getId());
        }

        if (!pFiltro.getDescricao().isEmpty()) {
            query.setParameter("descricao", pFiltro.getDescricao());
        }

        if (!pFiltro.getNome().isEmpty()) {
            query.setParameter("nome", pFiltro.getNome());
        }

        if (pFiltro.getPreco() > 0) {
            query.setParameter("preco", pFiltro.getPreco());
        }

        if (!pFiltro.getCategoria().isEmpty()) {
            query.setParameter("categoria", pFiltro.getCategoria());
        }

        return query.getResultList();
    }
}
