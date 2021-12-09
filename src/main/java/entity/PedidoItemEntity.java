package entity;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "pedidoitem")
public class PedidoItemEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double precoUnitario;
    private int quantidade;

    @ManyToOne
    private PedidoEntity pedido;

    @ManyToOne
    private ProdutoEntity produto;

    public int getId() {
        return id;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    protected void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
        this.precoUnitario = produto.getPreco();
    }
}
