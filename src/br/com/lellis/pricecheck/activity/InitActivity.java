package br.com.lellis.pricecheck.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import br.com.lellis.pricecheck.R;
import br.com.lellis.pricecheck.entity.Compra;
import br.com.lellis.pricecheck.tool.PriceCheckDbHelper;

public class InitActivity extends Activity {
    public static final String ITEM_SELECIONADO = "itemdetalhado";
    public static final String BITMAP = "BitmapImage";
    private Compra compraAberta;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        if (existeCompraAberta(savedInstanceState)){
            reabrirCompra(getCurrentFocus());
        }


    }

    private Boolean existeCompraAberta(Bundle savedInstanceState) {


       if (Compra.fetchAll(getBaseContext()).isEmpty() && (savedInstanceState == null || savedInstanceState.getSerializable(Compra.TABLE_NAME) == null) ){
           return Boolean.FALSE;
       }

//        compraAberta = new Compra();
//        compraAberta.setStatus(CompraStatus.ABERTA);
//
//        List<Item> itens = new ArrayList<Item>();
//        Item item = new Item();
//        item.setDescricao("LALALAL");
//        item.setPrecoUnitario(new BigDecimal(100));
//        item.setQuantidade(2);
//        itens.add(item);
//
//        compraAberta.setItens(itens);
        return Boolean.TRUE;

    }


    public void novaCompra(View view){
        Intent novaCompra = new Intent(this, CompraActivity.class);
        startActivity(novaCompra);
    }

    public void reabrirCompra(View view){
        Intent compraAbertaIntent = new Intent(this, CompraActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Compra", compraAberta);
        startActivity(compraAbertaIntent, bundle);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(Compra.TABLE_NAME, compraAberta);
        super.onSaveInstanceState(outState);
    }
}
