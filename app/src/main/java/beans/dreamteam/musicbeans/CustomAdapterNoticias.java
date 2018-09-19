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

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapterNoticias extends ArrayAdapter<NoticiasList> {
    Context context;
    int layoutResourceId;
    List<NoticiasList> data = null;

    static class DataHolder{
        ImageView img;
        TextView titulo;
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
            holder.img = (ImageView) convertView.findViewById(R.id.imgNot);
            holder.titulo = (TextView) convertView.findViewById(R.id.titulNot);
            convertView.setTag(holder);
        }
        else{
            holder = (DataHolder) convertView.getTag();
        }
        NoticiasList dataItem = data.get(position);
        holder.titulo.setText(dataItem.noticia.getTitulo());
        if(dataItem.noticia.getFoto() == null || dataItem.noticia.getFoto().isEmpty() || dataItem.noticia.getFoto().equals("null")) {
            holder.img.setImageResource(dataItem.resIdThumbnailNot);
        }
        else{
            // Load the image using Glide
            Glide.with(parent.getContext())
                    .load(dataItem.noticia.getFoto())
                    .into(holder.img);
        }
        return convertView;
    }
    public CustomAdapterNoticias(@NonNull Context context, int resource, @NonNull List<NoticiasList> objects) {
        super(context, resource, objects);
        this.context=context;
        this.data=objects;
        this.layoutResourceId=resource;
    }

}
