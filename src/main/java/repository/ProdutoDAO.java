package repository;

import classe.Conexao;
import entity.ProdutoEntity;
import model.dto.ProdutoConsultaDto;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public List<ProdutoEntity> consultCriteria(ProdutoConsultaDto pFiltro) throws Exception {

        CriteriaBuilder criteriaBuilder = Conexao.getCriteriaBuilder();

        CriteriaQuery<ProdutoEntity> query = criteriaBuilder.createQuery(ProdutoEntity.class);
        Root<ProdutoEntity> from = query.from(ProdutoEntity.class);

        Predicate filtro = criteriaBuilder.and();
        if (pFiltro.getId() >= 0) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.equal(from.get("id"), pFiltro.getId()));
        }

        if (!pFiltro.getDescricao().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.equal(from.get("descricao"), pFiltro.getDescricao()));
        }

        if (!pFiltro.getNome().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.equal(from.get("nome"), pFiltro.getNome()));
        }

        if (pFiltro.getPreco() > 0) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.equal(from.get("preco"), pFiltro.getPreco()));
        }

        if (!pFiltro.getCategoria().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.equal(from.get("categoria"), pFiltro.getCategoria()));
        }

        query.where(filtro);

        return Conexao.consult(query).getResultList();
    }
}
