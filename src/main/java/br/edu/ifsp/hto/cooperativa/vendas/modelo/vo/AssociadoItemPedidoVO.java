package br.edu.ifsp.hto.cooperativa.vendas.modelo.vo;

import java.math.BigDecimal;

public class AssociadoItemPedidoVO {
    private long id;
    private long associadoId;
    private long itemPedidoId;
    private BigDecimal quantidadeAtribuida;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(long associadoId) {
        this.associadoId = associadoId;
    }

    public long getItemPedidoId() {
        return itemPedidoId;
    }

    public void setItemPedidoId(long itemPedidoId) {
        this.itemPedidoId = itemPedidoId;
    }

    public BigDecimal getQuantidadeAtribuida() {
        return quantidadeAtribuida;
    }

    public void setQuantidadeAtribuida(BigDecimal quantidadeAtribuida) {
        this.quantidadeAtribuida = quantidadeAtribuida;
    }
}
