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

public class CustomAdapterFavoritos extends ArrayAdapter<DataItemFavoritos> {

    Context context;
    int layoutResourceId;
    List<DataItemFavoritos> data = null;

    static class DataHolder{
            ImageView ivFoto;
            TextView tvBanda;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DataHolder holder = null;
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new DataHolder();
            holder.ivFoto = (ImageView) convertView.findViewById(R.id.ivBanda);
            holder.tvBanda = (TextView) convertView.findViewById(R.id.tvBanda);
            convertView.setTag(holder);
        }
        else{
            holder = (DataHolder)convertView.getTag();
        }
        DataItemFavoritos dataItem = data.get(position);
        holder.tvBanda.setText(dataItem.bandName);
        holder.ivFoto.setImageResource(dataItem.resIdThumbnail);
        return convertView;
    }

    public CustomAdapterFavoritos(@NonNull Context context, int resource, @NonNull List<DataItemFavoritos> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }
}
