package beans.dreamteam.musicbeans;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import beans.dreamteam.musicbeans.model.Evento;
import beans.dreamteam.musicbeans.model.Noticia;
import beans.dreamteam.musicbeans.model.Usuario;
import beans.dreamteam.musicbeans.utils.DBConnection;
import beans.dreamteam.musicbeans.utils.Singleton;

public class perfilBanda extends AppCompatActivity {

    List<EventList> listDataEvents;
    List<NoticiasList> listDataNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_banda);
        listDataEvents = new ArrayList<>();
        listDataNoticias = new ArrayList<>();
        for(Evento ev : Singleton.getInstance().eventosUsuario){
            listDataEvents.add(new EventList(R.drawable.ic_person, ev));
        }
        for(Noticia nt : Singleton.getInstance().noticiasUsuario){
            listDataNoticias.add(new NoticiasList(R.drawable.ic_person, nt));
        }
        TextView nombre = findViewById(R.id.PerfilNombreBanda);
        Usuario banda = Singleton.getInstance().selectedUser;
        ImageView fotoPerfil = (ImageView) findViewById(R.id.imgBand);
        if(!(banda.getFoto() == null || banda.getFoto().isEmpty() || banda.getFoto().equals("null"))) {
            // Load the image using Glide
            Glide.with(perfilBanda.this)
                    .load(banda.getFoto())
                    .into(fotoPerfil);
        }

        Button shopButton = (Button) findViewById(R.id.shopButton);
        final Button favoriteButton = (Button) findViewById(R.id.favoritesButton);

        nombre.setText(Singleton.getInstance().selectedUser.getNombre());

        ListView eventos = (ListView) findViewById(R.id.eventosList);
        CustomAdapterEvents adapter = new CustomAdapterEvents(this, R.layout.itemeventos, listDataEvents);
        eventos.setAdapter(adapter);

        ListView noticiasView = (ListView) findViewById(R.id.noticiasList);
        CustomAdapterNoticias adapterNot = new CustomAdapterNoticias(this, R.layout.itemsnoticias, listDataNoticias);
        noticiasView.setAdapter(adapterNot);

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tienda = new Intent(perfilBanda.this, TiendaActivity.class);
                startActivity(tienda);
            }
        });

        ArrayList<Usuario> favoritos = Singleton.getInstance().favoritos;
        boolean appears = false;
        for(Usuario user : favoritos){
            if(user.getIdUsuario() == Singleton.getInstance().selectedUser.getIdUsuario()){
                appears = true;
                break;
            }
        }
        if(appears){
            favoriteButton.setText("Quitar de favoritos");
        }
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(favoriteButton.getText().toString().toLowerCase().equals("quitar de favoritos")){
                    //quitar de favoritos
                    Singleton.getInstance().quitarFavorito();
                    favoriteButton.setText("Agregar a favoritos");
                }
                else{
                    Singleton.getInstance().agregarFavorito();
                    favoriteButton.setText("Quitar de favoritos");
                }
            }
        });

        noticiasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Singleton.getInstance().selecetedNews = listDataNoticias.get(position).noticia;
                intent.setClass(perfilBanda.this, detalle_noticia.class);
                startActivity(intent);
            }
        });

        RatingBar rb = findViewById(R.id.ratingBand);
        rb.setRating(DBConnection.getCalificacion(Singleton.getInstance().selectedUser.getIdUsuario(),Singleton.getInstance().usuarioLogeado.getIdUsuario()));
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                boolean result = DBConnection.rating(Singleton.getInstance().usuarioLogeado.getIdUsuario(), Singleton.getInstance().selectedUser.getIdUsuario(),Math.round(rating));
                if(result){
                    Toast.makeText(perfilBanda.this,"Calificacion actualizada exitosamente", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(perfilBanda.this,"Error al actualizar", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
