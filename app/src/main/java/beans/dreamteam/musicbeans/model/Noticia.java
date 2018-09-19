package beans.dreamteam.musicbeans.model;

import java.util.ArrayList;
import java.util.Date;

public class Noticia {
    private int idNoticia;
    private int idBanda;
    private String titulo;
    private String cuerpo;
    private Date fecha;
    private String foto;
    private ArrayList<Comentario> comentarios;

    public Noticia(int idNoticia, int idBanda, String titulo, String cuerpo, Date fecha, String foto, ArrayList<Comentario> comentarios) {
        this.idNoticia = idNoticia;
        this.idBanda = idBanda;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
        this.foto = foto;
        this.comentarios = comentarios;
    }

    public Noticia() {
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public int getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(int idBanda) {
        this.idBanda = idBanda;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
