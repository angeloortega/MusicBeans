package beans.dreamteam.musicbeans.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import beans.dreamteam.musicbeans.model.*;


public class Singleton {
    private String _appVer;
    public Usuario usuarioLogeado;
    public ArrayList<Usuario> favoritos;
    public ArrayList<Noticia> noticias;
    public ArrayList<Evento> eventos;
    public Usuario selectedUser;
    public ArrayList<Noticia> noticiasUsuario;
    public ArrayList<Evento> eventosUsuario;
    public Noticia selecetedNews;
    public Articulo selecetedArticulo;
    public Carrito carrito;
    private static final Singleton ourInstance = new Singleton();


    public static Singleton getInstance() {
        return ourInstance;
    }

    public String get_appVer() {
        return _appVer;
    }

    public void set_appVer(String _appVer) {
        this._appVer = _appVer;
    }

    public void agregarFavorito(){
        DBConnection.addFavorito(usuarioLogeado.getIdUsuario(),selectedUser.getIdUsuario());
        favoritos.add(selectedUser);
        noticias.addAll(noticiasUsuario);
        eventos.addAll(eventosUsuario);
    }
    public void quitarFavorito(){
        DBConnection.deleteFavorito(usuarioLogeado.getIdUsuario(),selectedUser.getIdUsuario());
         ArrayList<Usuario> favoritosNew = new ArrayList<>();
         ArrayList<Noticia> noticiasNew = new ArrayList<>();
         ArrayList<Evento> eventosNew = new ArrayList<>();
        for(Usuario usr : favoritos){
            if(usr.getIdUsuario() == selectedUser.getIdUsuario()){
                favoritosNew.add(usr);
            }
        }
        for(Noticia ntc : noticias){
            if(ntc.getIdBanda() == selectedUser.getIdUsuario()){
                noticiasNew.add(ntc);            }
        }
        for(Evento evt : eventos){
            if(evt.getIdBanda() == selectedUser.getIdUsuario()){
                eventosNew.add(evt);
            }
        }
        favoritos.removeAll(favoritosNew);
        noticias.removeAll(noticiasNew);
        eventos.removeAll(eventosNew);
    }

}

