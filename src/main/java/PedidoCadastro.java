import classe.Conexao;
import entity.ClienteEntity;
import entity.PedidoEntity;
import entity.PedidoItemEntity;
import entity.ProdutoEntity;
import model.vo.PedidoRelatorioVendaVO;
import service.CategoriaService;
import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;

import java.util.List;

public class PedidoCadastro {

    public static void main(String[] args) throws Exception {

        Conexao.abrirConexao("loja");

        CategoriaService categoriaService = new CategoriaService();
        categoriaService.criarCategoria();

        ProdutoService produtoService = new ProdutoService();
        produtoService.criarProduto();

        ClienteService clienteService = new ClienteService();
        clienteService.criarCliente();

        PedidoItemEntity pedidoItem = new PedidoItemEntity();
        pedidoItem.setProduto(produtoService.consult().stream().findFirst().orElse(new ProdutoEntity()));
        pedidoItem.setQuantidade(5);

        PedidoEntity pedido = new PedidoEntity();
        pedido.setCliente(clienteService.consult().stream().findFirst().orElse(new ClienteEntity()));
        pedido.adicionaItem(pedidoItem);

        PedidoService pedidoService = new PedidoService();
        pedidoService.insert(pedido);

        pedidoItem = new PedidoItemEntity();
        pedidoItem.setProduto(produtoService.load(2));
        pedidoItem.setQuantidade(10);

        pedido = new PedidoEntity();
        pedido.setCliente(clienteService.consult().stream().findFirst().orElse(new ClienteEntity()));
        pedido.adicionaItem(pedidoItem);

        pedidoItem = new PedidoItemEntity();
        pedidoItem.setProduto(produtoService.load(3));
        pedidoItem.setQuantidade(2);
        pedido.adicionaItem(pedidoItem);

        pedidoService.insert(pedido);

        double valorTotal = pedidoService.getValorTotalVendido();
        System.out.println("Valor Vendido: " + valorTotal);

        List<PedidoRelatorioVendaVO> vRelatorio = pedidoService.relatorioVenda();
        vRelatorio.forEach(r -> {
            System.out.println("Produto: " + r.getProduto());
            System.out.println("Quantidade: " + r.getQuantidadeVendida());
            System.out.println("Ultima Venda: " + r.getUltimaVenda());
            System.out.println("");
        });

        Conexao.close();
    }
}
