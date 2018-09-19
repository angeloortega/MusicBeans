package beans.dreamteam.musicbeans;

import java.util.Date;

import beans.dreamteam.musicbeans.model.Noticia;

public class DataItemNews {
    int resIdThumbnail;
    Noticia noticia;

    public DataItemNews(int resIdThumbnail, Noticia noticia) {
        this.resIdThumbnail = resIdThumbnail;
        this.noticia = noticia;
    }
}
