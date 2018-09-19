package beans.dreamteam.musicbeans;

import beans.dreamteam.musicbeans.model.Articulo;

public class DataItemTienda {

    int resIdThumbnail;
    Articulo articulo;

    public DataItemTienda(int resIdThumbnail, Articulo articulo) {
        this.resIdThumbnail = resIdThumbnail;
        this.articulo = articulo;
    }
}
