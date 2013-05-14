package br.com.lellis.pricecheck.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import br.com.lellis.pricecheck.R;
import br.com.lellis.pricecheck.adapter.ItemArrayAdapter;
import br.com.lellis.pricecheck.entity.Compra;
import br.com.lellis.pricecheck.entity.Item;
import br.com.lellis.pricecheck.entity.SerializableImage;
import br.com.lellis.pricecheck.listener.ItemListClickListener;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 02/05/13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class NovaCompraActivity   extends Activity implements Serializable {

    public static final String QR_CODE_DESC_ITEM = "descItem";
    public Compra compra;
    private SerializableImage foto;
    private static final int ACTION_TAKE_PHOTO_S = 2;
    public static final int ACTION_SCAN_QRCODE = 3;


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
        EditText ETDesc = (EditText) findViewById(R.id.desc_item);

        try{
            novoItem.setPrecoUnitario(new BigDecimal(ETPreco.getText().toString()));
            novoItem.setQuantidade(new Integer(ETQtd.getText().toString()));
            novoItem.setDescricao(ETDesc.getText().toString());
            novoItem.setFoto(foto);
            compra.getItens().add(novoItem);

            atualizarTotal();
            cleanFields();
            listarItens();
        }
        catch (Exception ex){

        }

    }


    private void listarItens() {
        ArrayAdapter adapter = new ItemArrayAdapter(this,compra.getItens());

        final Intent resultadoDisplay = new Intent(this, ItemDetailAcivity.class);

        ((ListView) findViewById(R.id.listaItens)).setAdapter(adapter);
        ((ListView) findViewById(R.id.listaItens)).setOnItemClickListener(new ItemListClickListener(this, resultadoDisplay));
        ((ListView) findViewById(R.id.listaItens)).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = (Item) adapterView.getAdapter().getItem(i);
                Toast.makeText(getApplicationContext(),
                        "Item Removido: "+item.toString(), Toast.LENGTH_SHORT).show();
                ((ItemArrayAdapter)adapterView.getAdapter()).remove(item);
                compra.getItens().remove(item);
                atualizarTotal();
                return false;
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
        ((TextView)findViewById(R.id.desc_item)).setText("");
        foto = null;
    }

    public void tirarFoto(View view){
        dispatchTakePictureIntent(ACTION_TAKE_PHOTO_S);
    }

    public void scanQRCode(View view){
        Intent scanItent =  new Intent(this, CameraTestActivity.class);
        startActivityForResult(scanItent, ACTION_SCAN_QRCODE);
    }

    private void dispatchTakePictureIntent(int actionCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, actionCode);

    }

    private void handleSmallCameraPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
        foto = new SerializableImage();
        foto.setImage((Bitmap) extras.get("data"));
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
            case ACTION_SCAN_QRCODE:{
                if (resultCode != RESULT_CANCELED) {
                    ((EditText)findViewById(R.id.desc_item)).setText(data.getStringExtra(QR_CODE_DESC_ITEM));
                }
                break;

            }
        }
    }


}