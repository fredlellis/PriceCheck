package br.com.lellis.pricecheck.entity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.lellis.pricecheck.enums.CompraStatus;
import br.com.lellis.pricecheck.tool.PriceCheckDbHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 02/05/13
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */
public class Compra implements Serializable, BaseColumns{

    public static final String TABLE_NAME = "compra";
    public static final String COLUMN_NAME_COMPRA_ID = "compraid";
    public static final String COLUMN_NAME_DATA_DA_COMPRA = "dataDaCompra";
    public static final String COLUMN_NAME_STATUS = "status";

    private List<Item> itens = new ArrayList<Item>();
    private Calendar dataDaCompra;

    private CompraStatus status;

    public Compra() {
        dataDaCompra = Calendar.getInstance();
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

    public Calendar dataDaCompra() {
        return dataDaCompra;
    }

    public void dataDaCompra(Calendar dataCompra) {
        this.dataDaCompra = dataCompra;
    }

    public String valorCompra() {
        BigDecimal total = new BigDecimal(0);

        for (Item iten : itens) {
            total = total.add(iten.valorTotal());
        }

        return "Total ate o momento: R$" +total.setScale(2, RoundingMode.HALF_UP).toString();
    }


    public CompraStatus getStatus() {
        return status;
    }

    public void setStatus(CompraStatus status) {
        this.status = status;
    }

    public static List<Compra> fetchAll(Context context) {

        PriceCheckDbHelper mDbHelper = new PriceCheckDbHelper(context);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                Compra.COLUMN_NAME_COMPRA_ID,
                Compra.COLUMN_NAME_DATA_DA_COMPRA,
                Compra.COLUMN_NAME_STATUS
        };

        String selection = "";
        String[] selectionArgs = {};


        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Compra.COLUMN_NAME_COMPRA_ID + " DESC";

        Cursor cursor = db.query(
                Compra.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return new ArrayList<Compra>();
    }
}
