package beans.dreamteam.musicbeans.utils;
import android.content.Context;
import android.os.StrictMode;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import beans.dreamteam.musicbeans.model.*;

public class DBConnection {
    public static Connection connectionDB(){
        Connection conexion = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://registrocivil.database.windows.net:1433/MusicBeans;user=leyendas;password=Angelo12345678;");
        }
        catch(Exception e){
            return null;
        }
        return conexion;
    }

    public static Usuario getUsuario( String userName, String password){
        Usuario user = new Usuario(-1,null,null,null,null,
                null,null,null,null);
        try{
            int pos = 0;
            PreparedStatement pst = connectionDB().prepareStatement
                    ("select * from Usuario where correo = ? and contrasenha = ?");
            pst.setString(1,userName);
            pst.setString(2,password);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                user = new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"),
                        rs.getString("correo"), rs.getString("contrasenha"),
                        rs.getString("tipo"),rs.getString("descripcion"),
                        rs.getString("genero"), rs.getInt("anhoFundacion"),
                        rs.getBytes("foto"));
            }
        }
        catch (Exception e){
            return null;
        }
        return user;
    }
    public static Comentario getComentario ( int idComentario){

        Comentario comentario = new Comentario(-1,-1,-1,"",null);
        try {
            PreparedStatement pst2 = connectionDB().prepareStatement
                    ("select * from Comentario where idComentario = ?");
            pst2.setInt(1, idComentario);
            ResultSet rs2 = pst2.executeQuery();
            if(rs2.next()){
                comentario = new Comentario(rs2.getInt("idComentario"), rs2.getInt("idNoticia"),
                        rs2.getInt("idUsuario"), rs2.getString("comentario"),
                        rs2.getDate("fecha"));
            }
        }
        catch(Exception e){
            return null;
        }
        return comentario;
    }

    public static Evento getEvento( int idEvento){
        Evento evento = new Evento();
        try{
            PreparedStatement pst = connectionDB().prepareStatement
                    ("select * from Noticia where idEvento = ?");
            pst.setInt(1,idEvento);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                evento.setIdBanda(rs.getInt("idUsuario"));
                evento.setIdBanda(rs.getInt("idUsuario"));
                evento.setTitulo(rs.getString("titulo"));
                evento.setPrecio(rs.getFloat("precio"));
                evento.setFchPublicacion(rs.getDate("fechaPublicacion"));
                evento.setFoto(rs.getBytes("foto"));
                evento.setFchEvento(rs.getDate("fechaEvento"));
            }
        }
        catch (Exception e){
            return null;
        }
        return evento;
    }

    public static Noticia getNoticia( int idNoticia){
        Noticia noticia = new Noticia();
        try{
            PreparedStatement pst = connectionDB().prepareStatement
                    ("select * from Noticia where idNoticia = ?");
            pst.setInt(1,idNoticia);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                noticia.setIdBanda(rs.getInt("idUsuario"));
                noticia.setCuerpo(rs.getString("cuerpo"));
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setIdNoticia(rs.getInt("idNoticia"));
                noticia.setFecha(rs.getDate("fecha"));
                noticia.setFoto(rs.getBytes("foto"));
                noticia.setComentarios(getComentarios( noticia.getIdNoticia()));
            }
        }
        catch (Exception e){
            return null;
        }
        return noticia;
    }

    public static Articulo getArticulo( int idArticulo){
        Articulo articulo = new Articulo(-1,-1,"","","",-1,0,
                null,0,null);
        try {
            PreparedStatement pst2 = connectionDB().prepareStatement
                    ("select * from Articulo where idArticulo = ?");
            pst2.setInt(1, idArticulo);
            ResultSet rs2 = pst2.executeQuery();
            if(rs2.next()){
                articulo = new Articulo(rs2.getInt("idUsuario"), rs2.getInt("idArticulo"),
                        rs2.getString("nombre"), rs2.getString("categoria"),
                        rs2.getString("descripcion"),
                        rs2.getFloat("precio"),
                        rs2.getInt("cantidadDisponible"),
                        rs2.getDate("fechaPublicacion"),
                        0,
                        rs2.getBytes("foto")
                );
            }
        }
        catch(Exception e){
            return null;
        }
        return articulo;
    }
    public static Carrito getCarrito( int idUsuario){
        Carrito carrito = new Carrito();
        ArrayList<Articulo> articulos = new ArrayList<>();
        try{
            PreparedStatement pst = connectionDB().prepareStatement
                    ("select * from Carrito where idUsuario = ?");
            pst.setInt(1,idUsuario);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                carrito.setIdUsuario(rs.getInt("idUsuario"));
                articulos.add(getArticulo( rs.getInt("idArticulo")));
            }
            carrito.setArticulos(articulos);
        }
        catch (Exception e){
            return null;
        }
        return carrito;
    }

    public static ArrayList<Comentario> getComentarios ( int idNoticia){
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try{
            PreparedStatement pst = connectionDB().prepareStatement
                    ("select * from Comentario where idNoticia = ?");
            pst.setInt(1,idNoticia);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                comentarios.add(new Comentario(rs.getInt("idComentario"), rs.getInt("idNoticia"),rs.getInt("idUsuario"),
                                rs.getString("comentario"), rs.getDate("fecha")));
            }
        }
        catch (Exception e){
            return null;
        }
        return comentarios;
    }

    public static ArrayList<Integer> getFavoritos( int idUsuario){
        ArrayList<Integer> favoritos = new ArrayList<>();
        try{
        PreparedStatement pst = connectionDB().prepareStatement
                    ("select * from Favorito where idUsuario = ?");
            pst.setInt(1,idUsuario);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                favoritos.add(rs.getInt("idBanda"));
            }
        }
        catch (Exception e){
            return null;
        }
        return favoritos;
    }
    public static ArrayList<Integer> getCalificaciones( int idBanda){
        ArrayList<Integer> calificaciones = new ArrayList<>();
        try{
            PreparedStatement pst = connectionDB().prepareStatement
                    ("select * from Calificacion where idBanda = ?");
            pst.setInt(1,idBanda);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                calificaciones.add(rs.getInt("calificacion"));
            }
        }
        catch (Exception e){
            return null;
        }
        return calificaciones;
    }

    public static Muestra getMuestra( int idUsuario){
        Muestra muestra = new Muestra(-1,"","");
        try{
            PreparedStatement pst = connectionDB().prepareStatement
                    ("select * from Muestra where idUsuario = ?");
            pst.setInt(1,idUsuario);
            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                muestra = new Muestra(rs.getInt("idUsuario"),
                        rs.getString("titulo"), rs.getString("direccionMuestra"));
            }
        }
        catch (Exception e){
            return null;
        }
        return muestra;
    }
    public static boolean login( String user, String password){
        try{
            CallableStatement cstmt = null;

            cstmt = connectionDB().prepareCall
                    ("exec userLogin ?,?");
            cstmt.setString(1,user);
            cstmt.setString(2,password);
            boolean result = cstmt.execute();
            if(result) {
                ResultSet rs = cstmt.getResultSet();
                if(rs.next()) {
                    if (rs.getInt("result") == 1) {
                        return true;
                    }
                }
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

    public static boolean signup( String user, String fullName, String password){
        try{
            Statement cstmt = null;
            cstmt = connectionDB().createStatement();
            int result = cstmt.executeUpdate("insert into Usuario values ('"+fullName+"', '"+user+"', '"+password+"', 'Cliente', null,null,null,null)");
            if(result > 0) {
                return true;
            }
        }
        catch (Exception e){
        }
        return false;
    }
}
