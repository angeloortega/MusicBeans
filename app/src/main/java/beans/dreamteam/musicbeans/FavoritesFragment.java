package beans.dreamteam.musicbeans;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import beans.dreamteam.musicbeans.model.Evento;
import beans.dreamteam.musicbeans.model.Noticia;
import beans.dreamteam.musicbeans.model.Usuario;
import beans.dreamteam.musicbeans.utils.DBConnection;
import beans.dreamteam.musicbeans.utils.Singleton;

public class FavoritesFragment extends Fragment {
    public static final String ARG_TITLE = "arg_title";
    private TextView textView;
    List<DataItemFavoritos> listData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_favorites, container, false);
        listData = new ArrayList<>();


        if(Singleton.getInstance().usuarioLogeado.getTipo().equals("administrador")){
            for (Usuario u : Singleton.getInstance().favoritos) {
                    listData.add(new DataItemFavoritos(R.drawable.band, u.getNombre()));
            }
        }
        else {
            for (Usuario u : Singleton.getInstance().favoritos) {
                if (u.getTipo().equals("banda")) {
                    listData.add(new DataItemFavoritos(R.drawable.band, u.getNombre()));
                }
            }
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        final ListView listView = (ListView) getView().findViewById(R.id.listView);
        CustomAdapterFavoritos adapter = new CustomAdapterFavoritos(getActivity(), R.layout.item_list_favorites, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                Singleton.getInstance().selectedUser = Singleton.getInstance().favoritos.get(position);
                ArrayList<Noticia> noticiasSeleccionadas = new ArrayList<>();
                ArrayList<Evento> eventosSeleccionadas= new ArrayList<>();
                for(Noticia n : Singleton.getInstance().noticias){
                    if (n.getIdBanda() == Singleton.getInstance().selectedUser.getIdUsuario())
                    {
                        noticiasSeleccionadas.add(n);
                    }
                }

                for(Evento n : Singleton.getInstance().eventos){
                    if (n.getIdBanda() == Singleton.getInstance().selectedUser.getIdUsuario())
                    {
                        eventosSeleccionadas.add(n);
                    }
                }
                Singleton.getInstance().eventosUsuario = eventosSeleccionadas;
                Singleton.getInstance().noticiasUsuario = noticiasSeleccionadas;

                intent.setClass(getActivity(), perfilBanda.class);
                startActivity(intent);
            }
        });
    }
}
