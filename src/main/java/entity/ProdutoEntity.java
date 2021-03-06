package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "produto")
//cria uma unica tabela para todos os atributos que herdam da classe produto
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//cria uma tabela para cada classe que herda da classe produto
//@Inheritance(strategy = InheritanceType.JOINED)
public class ProdutoEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(name = "nome")
    private String nome;
    private String descricao;
    private double preco;
    private LocalDate dataCadastro = LocalDate.now();

//    @Enumerated(EnumType.STRING)
    //so carrega quando chamar pelo atributo
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoriaEntity categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }
}
