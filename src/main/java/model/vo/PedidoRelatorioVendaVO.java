package model.vo;

import java.time.LocalDate;

public class PedidoRelatorioVendaVO {

    private String produto;
    private Long quantidadeVendida;
    private LocalDate ultimaVenda;

    public PedidoRelatorioVendaVO(String produto, Long quantidadeVendida, LocalDate ultimaVenda) {
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
        this.ultimaVenda = ultimaVenda;
    }

    public String getProduto() {
        return produto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public LocalDate getUltimaVenda() {
        return ultimaVenda;
    }
}
