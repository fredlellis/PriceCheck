package br.lellis.intent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import br.lellis.R;
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

        compra.getItens().add(novoItem);
        String total = compra.valorCompra();

        ((TextView)findViewById(R.id.total)).setText(total);
        cleanFields();

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.list_item,compra.getItensName());

        ((ListView) findViewById(R.id.listaItens)).setAdapter(adapter);
    }

    private void cleanFields(){
        ((TextView)findViewById(R.id.qtd_item)).setText("");
        ((TextView)findViewById(R.id.precoUnitario)).setText("");
    }
}