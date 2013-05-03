package br.lellis.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import br.lellis.R;
import br.lellis.adapter.ItemArrayAdapter;
import br.lellis.entity.Compra;
import br.lellis.entity.Item;
import br.lellis.entity.SerializableImage;
import br.lellis.factory.AlbumStorageDirFactory;
import br.lellis.factory.BaseAlbumDirFactory;
import br.lellis.factory.FroyoAlbumDirFactory;
import br.lellis.listener.ItemListClickListener;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 02/05/13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class NovaCompraActivity   extends Activity implements Serializable {

    private Compra compra;
    private SerializableImage foto;
    private static final int ACTION_TAKE_PHOTO_S = 2;
    private AlbumStorageDirFactory mAlbumStorageDirFactory;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novacompra);
        compra = new Compra();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
        } else {
            mAlbumStorageDirFactory = new BaseAlbumDirFactory();
        }
    }

    public void incluirItem(View view){
        Item novoItem = new Item();
        EditText ETPreco = (EditText) findViewById(R.id.precoUnitario);
        EditText ETQtd = (EditText) findViewById(R.id.qtd_item);

        try{
            novoItem.setPrecoUnitario(new BigDecimal(ETPreco.getText().toString()));
            novoItem.setQuantidade(new Integer(ETQtd.getText().toString()));
            novoItem.setFoto(foto);
            compra.getItens().add(novoItem);

            atualizarTotal();
            cleanFields();
            listarItens();
        }
        catch (Exception ex){

        }

    }

    private File setUpPhotoFile() throws IOException {

        File f = createImageFile();
        mCurrentPhotoPath = f.getAbsolutePath();

        return f;
    }

    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File albumF = getAlbumDir();
        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
        return imageF;
    }

    private File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());

            if (storageDir != null) {
                if (! storageDir.mkdirs()) {
                    if (! storageDir.exists()){
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }

        } else {
            Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
        }

        return storageDir;
    }

    /* Photo album for this application */
    private String getAlbumName() {
        return getString(R.string.album_name);
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
        }
    }

}