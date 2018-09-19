package beans.dreamteam.musicbeans.model;

import java.sql.Connection;
import java.util.ArrayList;

import beans.dreamteam.musicbeans.utils.DBConnection;
import beans.dreamteam.musicbeans.utils.Singleton;

public class Carrito {
    private int idUsuario;
    private ArrayList<Articulo> articulos;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }

    public boolean addArticulo(Articulo articulo){
        boolean result = DBConnection.addArticuloCarrito(articulo, Singleton.getInstance().usuarioLogeado.getIdUsuario());
        if(result){
            this.articulos.add(articulo);
        }
        return result;
    }

    public boolean deleteArticulo(Articulo art) {
        boolean result = DBConnection.removeArticuloCarrito(art, Singleton.getInstance().usuarioLogeado.getIdUsuario());
        if(result){
            this.articulos.remove(art);
        }
        return result;
    }

    public void checkout() {
        DBConnection.clearCarrito(Singleton.getInstance().usuarioLogeado.getIdUsuario());
        this.articulos.clear();
    }
}
