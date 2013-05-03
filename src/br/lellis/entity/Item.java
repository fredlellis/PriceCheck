package br.lellis.entity;

import android.graphics.Bitmap;

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
    private Bitmap foto;

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

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    @Override
    public String toString() {
        return "R$=" + precoUnitario.setScale(2, RoundingMode.HALF_UP) +
                " , Quantidade=" + quantidade;

    }


}
