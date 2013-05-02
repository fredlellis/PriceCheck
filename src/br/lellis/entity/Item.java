package br.lellis.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 02/05/13
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class Item {

    private BigDecimal precoUnitario;
    private Integer quantidade;

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal valorTotal(){
        return precoUnitario.multiply(new BigDecimal(quantidade.intValue()));
    }

    @Override
    public String toString() {
        return "Item{" +
                "precoUnitario=" + precoUnitario.setScale(2, RoundingMode.HALF_UP) +
                ", quantidade=" + quantidade +
                '}';
    }
}
