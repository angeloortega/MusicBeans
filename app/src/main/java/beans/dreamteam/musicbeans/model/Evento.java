package beans.dreamteam.musicbeans.model;

import java.util.Date;

public class Evento {
    private int idEvento;
    private int idBanda;
    private String titulo;
    private float precio;
    private Date fchPublicacion;
    private Date fchEvento;
    private byte[] foto;

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFchPublicacion() {
        return fchPublicacion;
    }

    public void setFchPublicacion(Date fchPublicacion) {
        this.fchPublicacion = fchPublicacion;
    }

    public Date getFchEvento() {
        return fchEvento;
    }

    public void setFchEvento(Date fchEvento) {
        this.fchEvento = fchEvento;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
