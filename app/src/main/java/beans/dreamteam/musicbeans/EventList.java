package beans.dreamteam.musicbeans;

import beans.dreamteam.musicbeans.model.Evento;

public class EventList {

    int resIdThumbnailEv;
    Evento evento;

    public EventList(int resIdThumbnail, Evento evento) {
        this.resIdThumbnailEv = resIdThumbnail;
        this.evento = evento;
    }
}
