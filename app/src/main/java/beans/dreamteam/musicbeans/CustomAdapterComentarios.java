package beans.dreamteam.musicbeans;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapterComentarios extends ArrayAdapter<ComentariosLista> {

    Context context;
    int layoutResourceId;
    List<ComentariosLista> data = null;

    static class DataHolder{
        TextView idU;
        TextView com;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent){
        DataHolder holder = null;
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new DataHolder();
            holder.idU = (TextView) convertView.findViewById(R.id.idUsuario);
            holder.com = (TextView) convertView.findViewById(R.id.comment);
            convertView.setTag(holder);
        }
        else{
            holder = (DataHolder) convertView.getTag();
        }
        ComentariosLista dataItem = data.get(position);
        holder.idU.setText(String.valueOf(dataItem.idUsuario));
        holder.com.setText(dataItem.comentario);
        return convertView;
    }

    public CustomAdapterComentarios(@NonNull Context context, int resource, @NonNull List<ComentariosLista> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layoutResourceId=resource;
        this.data=objects;
    }
}
