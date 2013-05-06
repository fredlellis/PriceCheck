package br.com.lellis.pricecheck.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.lellis.pricecheck.R;
import br.com.lellis.pricecheck.entity.Item;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 03/05/13
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class ItemDetailAcivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        // Get the message from the intent
        Intent intent = getIntent();
        Item itemParaDetalhar = (Item) intent.getSerializableExtra(InitActivity.ITEM_SELECIONADO);



        ImageView imagemItemDetalhe = (ImageView) findViewById(R.id.imageDetail);
        TextView precoItemDetalhe = (TextView) findViewById(R.id.itemDetailPreco);
        TextView qtdItemDetalhe = (TextView) findViewById(R.id.itemDetailQtd);

        if (itemParaDetalhar.comFoto()){
            Bitmap bitmap = (Bitmap) intent.getParcelableExtra(InitActivity.BITMAP);
            imagemItemDetalhe.setImageBitmap(bitmap);
        }
        precoItemDetalhe.setText("Preco Unitário: " + itemParaDetalhar.getPrecoUnitario().toString());
        qtdItemDetalhe.setText("Quantidade: "+itemParaDetalhar.getQuantidade().toString());

    }
}
