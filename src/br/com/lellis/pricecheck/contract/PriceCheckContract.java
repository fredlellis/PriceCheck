package br.com.lellis.pricecheck.contract;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import br.com.lellis.pricecheck.entity.Compra;
import br.com.lellis.pricecheck.entity.Item;

/**
 * Created by lellis on 17/10/14.
 */
public class PriceCheckContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public PriceCheckContract() {
    }


    private static final String TEXT_TYPE = " TEXT";
    private static final String DATE_TYPE = " DATE";
    private static final String LONG_TYPE = " LONG";
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String COMMA_SEP = ",";
    private static final String BLOB_TYPE = "BLOB";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Compra.TABLE_NAME + " (" +
                    Compra.COLUMN_NAME_COMPRA_ID + " INTEGER PRIMARY KEY," +
                    Compra.COLUMN_NAME_DATA_DA_COMPRA + DATE_TYPE + COMMA_SEP +
                    Compra.COLUMN_NAME_STATUS + TEXT_TYPE +
                    " ); " +

                    " CREATE TABLE " + Item.TABLE_NAME + " (" +
                    Item.COLUMN_NAME_ITEM_ID + " INTEGER PRIMARY KEY," +
                    Item.COLUMN_NAME_DESCRICAO + TEXT_TYPE + COMMA_SEP +
                    Item.COLUMN_NAME_PRECO_UNITARIO + FLOAT_TYPE + COMMA_SEP +
                    Item.COLUMN_NAME_COMPRA_ID + LONG_TYPE + COMMA_SEP +
                    Item.COLUMN_NAME_QUANTIDADE + LONG_TYPE + COMMA_SEP +
                    Item.COLUMN_NAME_FOTO + BLOB_TYPE +
                    " ); ";


    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Compra.TABLE_NAME +
            " , " +"DROP TABLE IF EXISTS " + Item.TABLE_NAME;


}