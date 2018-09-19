package beans.dreamteam.musicbeans;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import beans.dreamteam.musicbeans.model.Evento;
import beans.dreamteam.musicbeans.model.Noticia;
import beans.dreamteam.musicbeans.model.Usuario;
import beans.dreamteam.musicbeans.utils.DBConnection;
import beans.dreamteam.musicbeans.utils.Singleton;

public class SearchFragment extends Fragment {

    List<Usuario> listData;
    CustomAdapterBandas adapter;
    ListView listView;
    TextInputLayout queryLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.search_bandas, container, false);
        listData = new ArrayList<>();
        //Agrego todas las bandas a la lista listData
        return rootView;
    }

    private void getData(String query)
    {
        List<Usuario> queryList;
        queryList = DBConnection.searchQuery(query);
        listData.clear();
        listData.addAll(queryList);
        CustomAdapterBandas adapter = new CustomAdapterBandas(getActivity(), R.layout.itemrow,listData);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        Button search = getView().findViewById(R.id.searchButton);
        queryLayout = (TextInputLayout) getView().findViewById(R.id.queryEntry);

        listView = (ListView) getView().findViewById(R.id.listViewBusqueda);
        CustomAdapterBandas adapter = new CustomAdapterBandas(getActivity(), R.layout.itemrow, listData);
        listView.setAdapter(adapter);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = queryLayout.getEditText().getText().toString();
                getData(query);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Singleton.getInstance().selectedUser = listData.get(position);
                ArrayList<Noticia> noticiasSeleccionadas = DBConnection.getNoticias( Singleton.getInstance().selectedUser.getIdUsuario());

                ArrayList<Evento> eventosSeleccionadas= DBConnection.getEventos( Singleton.getInstance().selectedUser.getIdUsuario());
                Singleton.getInstance().eventosUsuario = eventosSeleccionadas;
                Singleton.getInstance().noticiasUsuario = noticiasSeleccionadas;
                intent.setClass(getActivity(), perfilBanda.class);
                startActivity(intent);
            }
        });
    }

}
