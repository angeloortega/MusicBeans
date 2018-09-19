package beans.dreamteam.musicbeans;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import beans.dreamteam.musicbeans.model.Articulo;
import beans.dreamteam.musicbeans.model.Carrito;
import beans.dreamteam.musicbeans.utils.DBConnection;
import beans.dreamteam.musicbeans.utils.Singleton;

public class CarritoActivity extends AppCompatActivity {

    List<DataItemCarrito> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        Button btnVolver =  (Button)findViewById(R.id.btnBackToShop);
        Button btnCheckout =  (Button)findViewById(R.id.btnCheckout);
        Carrito carrito = Singleton.getInstance().carrito;
        listData = new ArrayList<>(); listData = new ArrayList<>();
        for(Articulo art : carrito.getArticulos()) {
            listData.add(new DataItemCarrito( R.drawable.productdefault, R.drawable.delete, art));

        }
        ListView listView = (ListView) findViewById(R.id.listViewCarrito);
        CustomAdapterCarrito adapter = new CustomAdapterCarrito(this, R.layout.itemlistcarrito, listData);
        listView.setAdapter(adapter);

        btnCheckout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Singleton.getInstance().carrito.checkout();
                Toast.makeText(CarritoActivity.this,"Compra realizada con exito", Toast.LENGTH_LONG).show();
                finish();
                //Redireccionar a la tienda de nuevo
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                //Redireccionar a la tienda de nuevo
            }
        });
    }
}
