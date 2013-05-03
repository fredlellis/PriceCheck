package br.lellis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.lellis.R;
import br.lellis.entity.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Y3WG
 * Date: 03/05/13
 * Time: 09:56
 * To change this template use File | Settings | File Templates.
 */
public class ItemArrayAdapter extends ArrayAdapter<Item> {
    private final Context context;
    private final List<Item> values;

    public ItemArrayAdapter(Context context, List<Item> values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values.get(position).toString());
        imageView.setImageBitmap(values.get(position).getFoto());
        return rowView;
    }
}