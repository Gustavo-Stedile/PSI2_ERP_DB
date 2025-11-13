package br.edu.ifsp.hto.cooperativa.vendas.modelo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoVO {
    private long id;
    private long projetoId;
    private long associadoId;
    private LocalDateTime dataCriacao;
    private long statusPedidoId;
    private BigDecimal valorTotal;

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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public long getStatusPedidoId() {
        return statusPedidoId;
    }

    public void setStatusPedidoId(long statusPedidoId) {
        this.statusPedidoId = statusPedidoId;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
