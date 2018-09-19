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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import beans.dreamteam.musicbeans.model.Noticia;
import beans.dreamteam.musicbeans.utils.DBConnection;
import beans.dreamteam.musicbeans.utils.Singleton;

public class NewsFragment extends Fragment {
    public static final String ARG_TITLE = "arg_title";
    public boolean deleteNoticia = false;
    private TextView textView;
    List<DataItemNews> listData;
    public class DateComparison implements Comparator<DataItemNews> {
        public int compare(DataItemNews o1, DataItemNews o2) {

            return -1 * o1.noticia.getFecha().compareTo(o2.noticia.getFecha());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_news, container, false);
        listData = new ArrayList<>();
        for(Noticia n : Singleton.getInstance().noticias) {
            listData.add(new DataItemNews(R.drawable.band, n));
        }
        Collections.sort(listData, new DateComparison());

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        final ListView listView = (ListView) getView().findViewById(R.id.newsListView);
        final CustomAdapterNews adapter = new CustomAdapterNews(getActivity(), R.layout.item_list_news, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(deleteNoticia){
                    deleteNoticia = false;
                    if(Singleton.getInstance().usuarioLogeado.getIdUsuario() == listData.get(position).noticia.getIdBanda() || Singleton.getInstance().usuarioLogeado.getTipo().toLowerCase().equals("administrador")) {
                        DBConnection.deleteNoticia(listData.get(position).noticia);
                        Singleton.getInstance().noticias.remove(listData.get(position).noticia);
                        listData.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        Toast.makeText(getActivity(), "No puedes eliminar noticias que no te pertenecen!",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Intent intent = new Intent();
                    Singleton.getInstance().selecetedNews = listData.get(position).noticia;
                    intent.setClass(getActivity(), detalle_noticia.class);
                    startActivity(intent);
                }
            }
        });
    }
}
