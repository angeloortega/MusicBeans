package beans.dreamteam.musicbeans;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import beans.dreamteam.musicbeans.model.Articulo;
import beans.dreamteam.musicbeans.utils.DBConnection;
import beans.dreamteam.musicbeans.utils.Singleton;

public class TiendaActivity extends AppCompatActivity {

    List<DataItemTienda> listData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        Button btnShopCart =  (Button)findViewById(R.id.btnCart);
        ArrayList<Articulo> articulos = DBConnection.getArticulos(Singleton.getInstance().selectedUser.getIdUsuario());
        listData = new ArrayList<>();
        for(Articulo art : articulos){
            listData.add(new DataItemTienda( R.drawable.band, art));
        }
        ListView listView = (ListView) findViewById(R.id.listViewTienda);
        CustomAdapterTienda adapter = new CustomAdapterTienda(this, R.layout.itemlisttienda, listData);
        listView.setAdapter(adapter);

        btnShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent carrito = new Intent(TiendaActivity.this, CarritoActivity.class);
                startActivity(carrito);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Singleton.getInstance().selecetedArticulo = listData.get(position).articulo;
                intent.setClass(TiendaActivity.this, detalles_articulo.class);
                startActivity(intent);
            }
        }); //Accion para redirigir a pagina de articulo.
    }
}
