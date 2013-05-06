package br.com.lellis.pricecheck.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.com.lellis.pricecheck.R;
import br.com.lellis.pricecheck.entity.Compra;

public class InitActivity extends Activity {
    public static final String ITEM_SELECIONADO = "itemdetalhado";
    public static final String BITMAP = "BitmapImage";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


    }

    public void novaCompra(View view){
        Intent novaCompra = new Intent(this, NovaCompraActivity.class);
        startActivity(novaCompra);
    }




}
