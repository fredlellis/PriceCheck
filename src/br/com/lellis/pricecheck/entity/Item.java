package br.com.lellis.pricecheck.entity;

import android.provider.BaseColumns;

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
public class Item implements Serializable, BaseColumns {

    public static final Object COLUMN_NAME_ITEM_ID = "itemID";
    public static String TABLE_NAME = "item";
    public static String COLUMN_NAME_PRECO_UNITARIO = "precoUnitario";
    public static String COLUMN_NAME_QUANTIDADE = "quantidade";
    public static String COLUMN_NAME_DESCRICAO = "descricao";
    public static String COLUMN_NAME_FOTO = "foto";
    public static String COLUMN_NAME_COMPRA_ID = "compraID";

    private BigDecimal precoUnitario;
    private Integer quantidade;
    private SerializableImage foto = new SerializableImage();
    private File imageFile;
    private String descricao;
    private long compraID;

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
        return descricao+" R$=" + precoUnitario.setScale(2, RoundingMode.HALF_UP) +
                " , Quantidade=" + quantidade;

    }


    public boolean comFoto() {
        return foto != null;
    }

    public void setDescricao(String s) {
        descricao = s;
    }

    public String getDescricao() {
        return descricao;
    }

    public long getCompraID() {
        return compraID;
    }

    public void setCompraID(long compraID) {
        this.compraID = compraID;
    }
}
