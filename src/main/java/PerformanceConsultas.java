import repository.Conexao;
import entity.ProdutoEntity;
import model.dto.ProdutoConsultaDto;
import service.CategoriaService;
import service.ProdutoService;

import java.util.List;

public class PerformanceConsultas {

    public static void main(String[] args) throws Exception {

        Conexao.abrirConexao("loja");

        CategoriaService categoriaService = new CategoriaService();
        categoriaService.criarCategoria();

        ProdutoService produtoService = new ProdutoService();
        produtoService.criarProduto();

        ProdutoEntity produtoConsulta = produtoService.load(1);
        System.out.println(produtoConsulta.getPreco());

        ProdutoConsultaDto filtro = new ProdutoConsultaDto();
        filtro.setPreco(800);
        filtro.setNome("Xiaomi Redmi");
        filtro.setDescricao("Muito bom!");
        filtro.setId(1);

        List<ProdutoEntity> vProduto = produtoService.consult(filtro);
        vProduto.forEach(p -> System.out.println(p.getNome()));

        Conexao.close();
    }
}
