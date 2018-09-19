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
import android.widget.Toast;

import java.util.List;

import beans.dreamteam.musicbeans.utils.Singleton;

public class CustomAdapterCarrito extends ArrayAdapter{

    Context context;
    int layoutResourceId;
    List<DataItemCarrito> data = null;

    static class DataHolder{
        ImageView ivArticulo;
        TextView tvArticulo;
        TextView tvPrecio;
        ImageView ivEliminar;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        DataHolder holder = null;
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new DataHolder();
            holder.tvPrecio = (TextView) convertView.findViewById(R.id.tvPrecio);
            holder.ivArticulo = (ImageView) convertView.findViewById(R.id.ivArticulo);
            holder.tvArticulo = (TextView) convertView.findViewById(R.id.tvArticulo);
            holder.ivEliminar = (ImageView) convertView.findViewById(R.id.ivEliminar);
            convertView.setTag(holder);
        }
        else{
            holder = (DataHolder)convertView.getTag();
        }
        final DataItemCarrito dataItem = data.get(position);
        holder.tvArticulo.setText(dataItem.art.getNombre());
        holder.ivArticulo.setImageResource(dataItem.resIdThumbnail);
        holder.tvPrecio.setText(dataItem.art.getPrecio() + "$");
        holder.ivEliminar.setImageResource(dataItem.deleteimg);
        holder.ivEliminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean result = Singleton.getInstance().carrito.deleteArticulo(dataItem.art);
                if(result) {
                    data.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(parent.getContext(),"Articulo eliminado del carrito", Toast.LENGTH_LONG).show();
                }
                else{

                    Toast.makeText(parent.getContext(),"No se pudo eliminar el articulo del carrito", Toast.LENGTH_LONG).show();
                }
                //Redireccionar a la tienda de nuevo
            }
        });
        return convertView;
    }

    public CustomAdapterCarrito(@NonNull Context context, int resource, @NonNull List<DataItemCarrito> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }

}
