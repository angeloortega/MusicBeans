package beans.dreamteam.musicbeans.model;

import java.util.Date;

public class Articulo {
    private int idBanda;
    private int idArticulo;
    private String nombre;
    private String categoria;
    private String Descripcion;
    private float precio;
    private int ctdDisponible;
    private Date fechaPublicacion;
    private Integer cantidadComprada;
    private String foto;

    public Articulo(int idBanda,int idArticulo, String nombre, String categoria, String descripcion, float precio, int ctdDisponible, Date fechaPublicacion, Integer cantidadComprada, String foto) {
        this.idBanda = idBanda;
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.categoria = categoria;
        Descripcion = descripcion;
        this.precio = precio;
        this.ctdDisponible = ctdDisponible;
        this.fechaPublicacion = fechaPublicacion;
        this.cantidadComprada = cantidadComprada;
        this.foto = foto;
    }

    public Integer getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(Integer cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }

    public int getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(int idBanda) {
        this.idBanda = idBanda;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCtdDisponible() {
        return ctdDisponible;
    }

    public void setCtdDisponible(int ctdDisponible) {
        this.ctdDisponible = ctdDisponible;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
