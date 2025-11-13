package br.edu.ifsp.hto.cooperativa.vendas.modelo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VendaVO {
    private long id;
    private long projetoId;
    private long associadoId;
    private long pedidoId;
    private LocalDateTime dataCompra;
    private BigDecimal valorTotal;
    private LocalDateTime dataEntrega;
    private long formaPagamentoId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(long projetoId) {
        this.projetoId = projetoId;
    }

    public long getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(long associadoId) {
        this.associadoId = associadoId;
    }

    public long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public long getFormaPagamentoId() {
        return formaPagamentoId;
    }

    public void setFormaPagamentoId(long formaPagamentoId) {
        this.formaPagamentoId = formaPagamentoId;
    }

}
