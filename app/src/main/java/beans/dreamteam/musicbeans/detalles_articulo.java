package beans.dreamteam.musicbeans;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import beans.dreamteam.musicbeans.model.Articulo;
import beans.dreamteam.musicbeans.utils.Singleton;

public class detalles_articulo extends AppCompatActivity {

    //Usuario cliente;
    //Articulo articulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_articulo);

        final Articulo articulo = Singleton.getInstance().selecetedArticulo;
        ImageView imgArticulo = (ImageView) findViewById(R.id.ivProducto);
        TextView precioText = (TextView) findViewById(R.id.precioText);
        TextView descipcion = (TextView) findViewById(R.id.descipcionText);
        TextView nbrArticulo = (TextView) findViewById(R.id.tvNbrArticulo);
        Button agregarCarrito = (Button) findViewById(R.id.btnAgregarCarrito);

        precioText.setText(articulo.getPrecio() + "$");
        descipcion.setText(articulo.getDescripcion());
        nbrArticulo.setText(articulo.getNombre());
        if(articulo.getFoto() == null || articulo.getFoto().isEmpty() || articulo.getFoto().equals("null")) {
            imgArticulo.setImageResource(R.drawable.placeholder);
        }
        else{
            // Load the image using Glide
            Glide.with(this)
                    .load(articulo.getFoto())
                    .into(imgArticulo);
        }

        agregarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Singleton.getInstance().carrito.addArticulo(articulo)){
                    Toast.makeText(detalles_articulo.this,"Articulo añadido al carrito", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(detalles_articulo.this,"No se pueden añadir duplicados al carrito", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
    }
}
