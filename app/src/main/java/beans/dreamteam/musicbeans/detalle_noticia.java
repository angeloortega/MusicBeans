package beans.dreamteam.musicbeans;


import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.support.design.widget.*;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import beans.dreamteam.musicbeans.model.Noticia;
import beans.dreamteam.musicbeans.utils.Singleton;

public class detalle_noticia extends AppCompatActivity {

    //Usuario usuario;
    //Noticia noticia;
    //TextInputLayout comentario;
    List<ComentariosLista> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_noticia);
        Noticia seleccionada = Singleton.getInstance().selecetedNews;
        listData = new ArrayList<>();
        listData.add(new ComentariosLista(1, "Eminem"));
        listData.add(new ComentariosLista(3, "Bad Bunny"));
        listData.add(new ComentariosLista(2, "XxxTentacion"));
        ImageView imgNoticia = (ImageView) findViewById(R.id.imgNoticia);
        TextView tituloNoticia = (TextView) findViewById(R.id.tituloNoticiaDetalle);
        TextView contenidosNoticia = findViewById(R.id.detalleNoticia);
        Button agregarComment = findViewById(R.id.agregarComentario);
        //comentario = (TextInputLayout) findViewById(R.id.entryComentario);
        ListView comentariosLista = (ListView) findViewById(R.id.commentsList);
        CustomAdapterComentarios adapter = new CustomAdapterComentarios(this, R.layout.itemscomentarios, listData);
        comentariosLista.setAdapter(adapter);

        if(seleccionada.getFoto() == null || seleccionada.getFoto().isEmpty() || seleccionada.getFoto().equals("null")) {
            imgNoticia.setImageResource(R.drawable.placeholder);
        }
        else{
            // Load the image using Glide
            Glide.with(this)
                    .load(seleccionada.getFoto())
                    .into(imgNoticia);
        }
        tituloNoticia.setText(seleccionada.getTitulo());
        contenidosNoticia.setText(seleccionada.getCuerpo());

        /*agregarComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = comentario.getEditText().getText().toString();
                Comentario comentarionuevo = new Comentario(noticia.getIdNoticia(),usuario.getIdUsuario(),comment);
            }
        });*/


    }
}
