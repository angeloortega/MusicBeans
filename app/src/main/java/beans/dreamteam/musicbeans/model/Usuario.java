package beans.dreamteam.musicbeans.model;

import java.util.ArrayList;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasenha;
    private String tipo;
    private String descripcion;
    private String genero;
    private Integer anhoFundacion;
    private String foto;
    private ArrayList<Noticia> noticias;
    private ArrayList<Evento> eventos;
    private ArrayList<Articulo> articulos;
    private ArrayList<Integer> favoritos;
    private Muestra muestra;
    private Carrito carrito;

    public Usuario(int idUsuario, String nombre, String correo, String contrasenha, String tipo, String descripcion, String genero, Integer anhoFundacion, String foto) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.genero = genero;
        this.anhoFundacion = anhoFundacion;
        this.foto = foto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnhoFundacion() {
        return anhoFundacion;
    }

    public void setAnhoFundacion(Integer anhoFundacion) {
        this.anhoFundacion = anhoFundacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public ArrayList<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }

    public ArrayList<Integer> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(ArrayList<Integer> favoritos) {
        this.favoritos = favoritos;
    }

    public Muestra getMuestra() {
        return muestra;
    }

    public void setMuestra(Muestra muestra) {
        this.muestra = muestra;
    }

    public void addToCarrito(Articulo articulo){
        this.carrito.addArticulo(articulo);
    }
    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
}
