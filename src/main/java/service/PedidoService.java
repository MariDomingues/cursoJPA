package service;

import classe.Conexao;
import entity.PedidoEntity;
import model.vo.PedidoRelatorioVendaVO;
import repository.PedidoDAO;

import java.util.List;

public class PedidoService {

    private final PedidoDAO pedidoDAO;

    public PedidoService() {
        this.pedidoDAO = new PedidoDAO();
    }

    public PedidoService(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public void insert(PedidoEntity pPedido) throws Exception {

        try {
            Conexao.begin();

            Conexao.insert(pPedido);

            Conexao.commit();

        } catch (Exception ex) {
            Conexao.rollback();
            throw new Exception(ex);
        }
    }

    public double getValorTotalVendido() {

        return pedidoDAO.getValorTotalVendido();
    }

    public List<PedidoRelatorioVendaVO> relatorioVenda() {

        return pedidoDAO.relatorioVenda();
    }
}
