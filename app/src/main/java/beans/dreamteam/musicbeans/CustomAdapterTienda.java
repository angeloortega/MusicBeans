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

import beans.dreamteam.musicbeans.model.Articulo;

public class CustomAdapterTienda extends ArrayAdapter<DataItemTienda>{

    Context context;
    int layoutResourceId;
    List<DataItemTienda> data = null;

    static class DataHolder{
        ImageView ivArticulo;
        TextView tvArticulo;
        TextView tvPrecio;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DataHolder holder = null;
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new DataHolder();
            holder.tvPrecio = (TextView) convertView.findViewById(R.id.tvPrecio);
            holder.ivArticulo = (ImageView) convertView.findViewById(R.id.ivArticulo);
            holder.tvArticulo = (TextView) convertView.findViewById(R.id.tvArticulo);
            convertView.setTag(holder);
        }
        else{
            holder = (DataHolder)convertView.getTag();
        }
        DataItemTienda dataItem = data.get(position);
        Articulo articulo = dataItem.articulo;
        holder.tvArticulo.setText(articulo.getNombre());

        holder.tvPrecio.setText(articulo.getPrecio() + "$");
        if(articulo.getFoto() == null || articulo.getFoto().isEmpty() || articulo.getFoto().equals("null")) {
            holder.ivArticulo.setImageResource(R.drawable.placeholder);
        }
        else{
            // Load the image using Glide
            Glide.with(parent.getContext())
                    .load(articulo.getFoto())
                    .into( holder.ivArticulo);
        }
        return convertView;
    }

    public CustomAdapterTienda(@NonNull Context context, int resource, @NonNull List<DataItemTienda> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }
}
