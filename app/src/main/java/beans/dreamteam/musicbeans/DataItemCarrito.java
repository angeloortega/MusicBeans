package beans.dreamteam.musicbeans;

import beans.dreamteam.musicbeans.model.Articulo;

public class DataItemCarrito {

    int resIdThumbnail;
    Articulo art;
    int deleteimg;

    public DataItemCarrito(int resIdThumbnail,int deleteimg, Articulo art) {
        this.resIdThumbnail = resIdThumbnail;
        this.art = art;
        this.deleteimg = deleteimg;
    }
}
