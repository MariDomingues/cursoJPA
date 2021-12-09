package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "pedido")
public class PedidoEntity extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate data = LocalDate.now();
    private double valorTotal;

    @ManyToOne
    private ClienteEntity cliente;

    /*precisa ser indicado para o JPA quando acontece um relacionamento bidirecional
      pois ele iria acabar criando tabelas erradas. Então para que isso não ocorra,
      precisa acrescentar o parâmetro mappedBy indicando o nome do atributo que esta
      ligando com a outra tabela. Já o parâmetro cascade é para quando salvar o pedido
      ja salve também o item
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @Column(name = "produto")
    private List<PedidoItemEntity> vProduto = new ArrayList<>();

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void adicionaItem(PedidoItemEntity pItem) {

        pItem.setPedido(this);
        this.vProduto.add(pItem);
        this.valorTotal += pItem.getPrecoUnitario() * pItem.getQuantidade();
    }
}
