package beans.dreamteam.musicbeans.model;

import java.util.ArrayList;
import java.util.Date;

public class Comentario {
    private int idComentario;
    private int idNoticia;
    private int idUsuario;
    private String comentario;
    private Date fecha;

    public Comentario(int idComentario,int idNoticia, int idUsuario, String comentario, Date fecha) {
        this.idComentario = idComentario;
        this.idNoticia = idNoticia;
        this.idUsuario = idUsuario;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
