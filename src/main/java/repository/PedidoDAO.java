package repository;

import entity.PedidoEntity;
import model.vo.PedidoRelatorioVendaVO;

import java.util.List;

public class PedidoDAO {

    public double getValorTotalVendido() {

        StringBuilder jpql = new StringBuilder()
                .append("SELECT SUM(p.valorTotal)")
                .append(" FROM PedidoEntity p");

        return (Double) Conexao.consult(Double.class, jpql.toString()).getSingleResult();
    }

    public List<PedidoRelatorioVendaVO> relatorioVenda() {

        StringBuilder jpql = new StringBuilder()
                .append("SELECT new model.vo.PedidoRelatorioVendaVO(")
                .append(" pr.nome,")
                .append(" SUM(pi.quantidade),")
                .append(" MAX(pe.data))")
                .append(" FROM PedidoEntity pe")
                .append(" JOIN pe.vProduto pi")
                .append(" JOIN pi.produto pr")
                .append(" GROUP BY pr.nome")
                .append(" ORDER BY pi.quantidade DESC");

        return Conexao.consult(PedidoRelatorioVendaVO.class, jpql.toString())
                .getResultList();
    }

    public PedidoEntity getPedidoCliente(int pIdPedido) {

        /*
        quando os atributos estão como LAZY, ele deixa de carregar as tabelas
        que tem uma ligação, então um jeito de acessa-las é com o JOIN FETCH.
        Então especificamente nessa consulta é como se o relacionamente entre
        as tabelas voltassem a ser EAGER
         */
        StringBuilder jpql = new StringBuilder()
                .append("SELECT p")
                .append(" JOIN FETCH p.cliente")
                .append(" FROM PedidoEntity p")
                .append(" WHERE p.id = :id");

        return (PedidoEntity) Conexao.consult(PedidoEntity.class, jpql.toString())
                .setParameter("id", pIdPedido)
                .getSingleResult();
    }
}
