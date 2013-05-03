package br.lellis.listener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;
import br.lellis.InitActivity;
import br.lellis.entity.Item;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 03/05/13
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class ItemListClickListener implements AdapterView.OnItemClickListener {

    private Activity activityCall;
    private Intent intentToGo;
    private Bitmap bitMap;

    public ItemListClickListener(Activity activity, Intent intentToGo, Bitmap bitmap) {
        activityCall = activity;
        this.intentToGo = intentToGo;
        this.bitMap = bitmap;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Item item = (Item) adapterView.getAdapter().getItem(i);
        intentToGo.putExtra(InitActivity.ITEM_SELECIONADO, item);
        intentToGo.putExtra(InitActivity.BITMAP, bitMap);
        activityCall.startActivity(intentToGo);
    }
}
