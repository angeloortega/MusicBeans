package beans.dreamteam.musicbeans.model;

public class Muestra {
    private int idBanda;
    private String titulo;
    private String direccionMuestra;

    public Muestra(int idBanda, String titulo, String direccionMuestra) {
        this.idBanda = idBanda;
        this.titulo = titulo;
        this.direccionMuestra = direccionMuestra;
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

    public String getDireccionMuestra() {
        return direccionMuestra;
    }

    public void setDireccionMuestra(String direccionMuestra) {
        this.direccionMuestra = direccionMuestra;
    }
}
