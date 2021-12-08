
import classe.Conexao;
import entity.CategoriaEntity;
import entity.ProdutoEntity;
import model.vo.ProdutoConsultaVO;
import service.CategoriaService;
import service.ProdutoService;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        Conexao.abrirConexao("loja");

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setDescricao("Celular");

        CategoriaService categoriaService = new CategoriaService();
        categoriaService.insert(categoria);

        categoria.setDescricao("Celular Internacional");
        categoriaService.update(categoria);

        ProdutoEntity celular = new ProdutoEntity();
        celular.setNome("Xiaomi Redmi");
        celular.setDescricao("Muito bom!");
        celular.setPreco(800);

        ProdutoService produtoService = new ProdutoService();
        produtoService.insert(celular);

        ProdutoEntity produtoConsulta = produtoService.load(1);
        System.out.println(produtoConsulta.getPreco());

        ProdutoConsultaVO filtro = new ProdutoConsultaVO();
        filtro.setPreco(800);
        filtro.setNome("Xiaomi Redmi");
        filtro.setDescricao("Muito Bom!");
        filtro.setId(1);

        List<ProdutoEntity> vProduto = produtoService.consult(filtro);
        vProduto.forEach(p -> System.out.println(p.getNome()));

        Conexao.close();
    }
}
