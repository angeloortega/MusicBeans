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

public class CustomAdapterEvents extends ArrayAdapter<EventList> {

    Context context;
    int layoutResourceId;
    List<EventList> data = null;

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
            holder.img = (ImageView) convertView.findViewById(R.id.imgEvNot);
            holder.titulo = (TextView) convertView.findViewById(R.id.tituloEvNot);
            convertView.setTag(holder);
        }
        else{
            holder = (DataHolder) convertView.getTag();
        }
        EventList dataItem = data.get(position);
        holder.titulo.setText(dataItem.evento.getIdEvento());
        holder.img.setImageResource(dataItem.resIdThumbnailEv);
        return convertView;
    }

    public CustomAdapterEvents(@NonNull Context context, int resource, @NonNull List<EventList> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layoutResourceId = resource;
        this.data = objects;
    }
}
