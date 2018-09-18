package beans.dreamteam.musicbeans.model;

import java.util.ArrayList;

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

    public void addArticulo(Articulo articulo){
        this.articulos.add(articulo);
    }
}
