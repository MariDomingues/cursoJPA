package repository;

import classe.Conexao;
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

        return Conexao.consult(PedidoRelatorioVendaVO.class, jpql.toString()).getResultList();
    }
}
