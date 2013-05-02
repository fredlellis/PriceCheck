package br.lellis.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 02/05/13
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */
public class Compra {

    private List<Item> itens = new ArrayList<Item>();
    private Calendar dataCompra;

    public Compra() {
        dataCompra = Calendar.getInstance();
    }

    public List<Item> getItens() {
        return itens;
    }

    public List<String> getItensName(){
        List<String> nomes = new ArrayList<String>();
        for (Item iten : itens) {

            nomes.add(iten.toString());
        }

        return nomes;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String valorCompra() {
        BigDecimal total = new BigDecimal(0);

        for (Item iten : itens) {
            total = total.add(iten.valorTotal());
        }

        return "Total ate o momento: R$" +total.setScale(2, RoundingMode.HALF_UP).toString();
    }
}
