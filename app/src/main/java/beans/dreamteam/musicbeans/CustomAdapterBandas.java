package beans.dreamteam.musicbeans;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import beans.dreamteam.musicbeans.model.Usuario;

public class CustomAdapterBandas extends ArrayAdapter<Usuario> {

    Context context;
    int layoutResourceId;
    List<Usuario> data = null;

    static class DataHolder{
        ImageView imgBanda;
        TextView nameBanda;
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
            holder.imgBanda = (ImageView) convertView.findViewById(R.id.imgBand);
            holder.nameBanda = (TextView) convertView.findViewById(R.id.nameBand);
            convertView.setTag(holder);
        }
        else{
            holder = (DataHolder) convertView.getTag();
        }
        Usuario dataItem = data.get(position);
        holder.nameBanda.setText(dataItem.getNombre());
        holder.imgBanda.setImageResource(R.drawable.band);
        return convertView;
    }

    public CustomAdapterBandas(Context context, int resource,List<Usuario> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }
}
