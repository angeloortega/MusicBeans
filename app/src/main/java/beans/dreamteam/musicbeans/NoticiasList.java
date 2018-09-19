package beans.dreamteam.musicbeans;

import beans.dreamteam.musicbeans.model.Noticia;

public class NoticiasList {

    int resIdThumbnailNot;
    Noticia noticia;

    public NoticiasList(int resIdThumbnail, Noticia noticia) {
        this.resIdThumbnailNot = resIdThumbnail;
        this.noticia = noticia;
    }
}
