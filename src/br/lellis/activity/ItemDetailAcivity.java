package br.lellis.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import br.lellis.InitActivity;
import br.lellis.R;
import br.lellis.entity.Item;

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
        Item resultado = (Item) intent.getSerializableExtra(InitActivity.ITEM_SELECIONADO);
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra(InitActivity.BITMAP);


        ImageView imagemItemDetalhe = (ImageView) findViewById(R.id.imageDetail);
        TextView precoItemDetalhe = (TextView) findViewById(R.id.itemDetailPreco);
        TextView qtdItemDetalhe = (TextView) findViewById(R.id.itemDetailQtd);

        imagemItemDetalhe.setImageBitmap(bitmap);
        precoItemDetalhe.setText("Preco Unitário: "+resultado.getPrecoUnitario().toString());
        qtdItemDetalhe.setText("Quantidade: "+resultado.getQuantidade().toString());

    }
}
