package br.lellis.entity;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 02/05/13
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class Item implements Serializable {

    private BigDecimal precoUnitario;
    private Integer quantidade;
    private SerializableImage foto = new SerializableImage();
    private File imageFile;

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario.setScale(2, RoundingMode.HALF_UP);
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

    public SerializableImage getFoto() {
        return foto;
    }

    public void setFoto(SerializableImage foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "R$=" + precoUnitario.setScale(2, RoundingMode.HALF_UP) +
                " , Quantidade=" + quantidade;

    }


    public boolean comFoto() {
        return foto != null;
    }
}
