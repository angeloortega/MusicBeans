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
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class CustomAdapterNews extends ArrayAdapter<DataItemNews> {

    Context context;
    int layoutResourceId;
    List<DataItemNews> data = null;
    static class DataHolder{
        ImageView ivFoto;
        TextView tvBanda;
        TextView tvNoticia;
        TextView tvDate;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CustomAdapterNews.DataHolder holder = null;
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new CustomAdapterNews.DataHolder();

            holder.ivFoto = (ImageView) convertView.findViewById(R.id.news_ivBanda);
            holder.tvBanda = (TextView) convertView.findViewById(R.id.news_tvBanda);
            holder.tvNoticia = (TextView) convertView.findViewById(R.id.news_tvContent);
            holder.tvDate = (TextView) convertView.findViewById(R.id.news_tvDate);
            convertView.setTag(holder);
        }
        else{
            holder = (CustomAdapterNews.DataHolder)convertView.getTag();
        }
        DataItemNews dataItem = data.get(position);
        holder.tvBanda.setText(dataItem.noticia.getTitulo());
        holder.tvDate.setText(android.text.format.DateFormat.format("dd-MM-yyyy", dataItem.noticia.getFecha()));
        holder.tvNoticia.setText(dataItem.noticia.getCuerpo());
        if(dataItem.noticia.getFoto() == null || dataItem.noticia.getFoto().isEmpty() || dataItem.noticia.getFoto().equals("null")) {
            holder.ivFoto.setImageResource(dataItem.resIdThumbnail);
        }
        else{
            holder.ivFoto.setImageResource(dataItem.resIdThumbnail);
            // Load the image using Glide
            Glide.with(parent.getContext())
                    .load(dataItem.noticia.getFoto())
                    .into(holder.ivFoto);
        }
        return convertView;
    }

    public CustomAdapterNews(@NonNull Context context, int resource, @NonNull List<DataItemNews> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }
}
