package br.lellis.intent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import br.lellis.R;
import br.lellis.adapter.ItemArrayAdapter;
import br.lellis.entity.Compra;
import br.lellis.entity.Item;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 02/05/13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class NovaCompraActivity   extends Activity {

    private Compra compra;
    private Bitmap foto;
    private static final int ACTION_TAKE_PHOTO_S = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novacompra);
        compra = new Compra();
    }

    public void incluirItem(View view){
        Item novoItem = new Item();
        EditText ETPreco = (EditText) findViewById(R.id.precoUnitario);
        EditText ETQtd = (EditText) findViewById(R.id.qtd_item);

        novoItem.setPrecoUnitario(new BigDecimal(ETPreco.getText().toString()));
        novoItem.setQuantidade(new Integer(ETQtd.getText().toString()));
        novoItem.setFoto(foto);
        compra.getItens().add(novoItem);

        atualizarTotal();
        cleanFields();
        listarItens();
    }

    private void listarItens() {
        ArrayAdapter adapter = new ItemArrayAdapter(this,compra.getItens());

        ((ListView) findViewById(R.id.listaItens)).setAdapter(adapter);
        ((ListView) findViewById(R.id.listaItens)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Item item = (Item) parent.getAdapter().getItem(position);
                System.out.println(item);
                Toast.makeText(getApplicationContext(),
                        "item Removido: "+item.toString(), Toast.LENGTH_SHORT).show();
                ((ItemArrayAdapter)parent.getAdapter()).remove(item);
                compra.getItens().remove(item);
                atualizarTotal();
            }
        });
    }

    private void atualizarTotal() {
        String total = compra.valorCompra();
        ((TextView)findViewById(R.id.total)).setText(total);

    }

    private void cleanFields(){
        ((TextView)findViewById(R.id.qtd_item)).setText("");
        ((TextView)findViewById(R.id.precoUnitario)).setText("");
        foto = null;
    }

    public void tirarFoto(View view){
        dispatchTakePictureIntent(ACTION_TAKE_PHOTO_S);
    }

    private void dispatchTakePictureIntent(int actionCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, actionCode);

    }

    private void handleSmallCameraPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
        foto = (Bitmap) extras.get("data");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTION_TAKE_PHOTO_S: {
                if (resultCode == RESULT_OK) {
                    handleSmallCameraPhoto(data);
                }
                break;
            }
        }
    }

}