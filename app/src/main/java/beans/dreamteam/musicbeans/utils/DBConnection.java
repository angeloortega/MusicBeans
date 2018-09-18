package beans.dreamteam.musicbeans.utils;
import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import beans.dreamteam.musicbeans.model.*;

public class DBConnection {
    public static Connection connectionDB(Context currentContext){
        Connection conexion = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://registrocivil.database.windows.net:1433/MusicBeans;user=leyendas;password=Angelo12345678;");
        }
        catch(Exception e){
            Toast.makeText(currentContext,e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return conexion;
    }

    public static Usuario getUsuario(Context currentContext, String userName, String password){
        Usuario user = new Usuario(-1,null,null,null,null,
                null,null,null,null);
        try{
            int pos = 0;
            PreparedStatement pst = connectionDB(currentContext).prepareStatement
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
            Toast.makeText(currentContext,"Ocurrio un error en la BD", Toast.LENGTH_SHORT).show();
        }
        return user;
    }
    public static Comentario getComentario (Context currentContext, int idComentario){

        Comentario comentario = new Comentario(-1,-1,-1,"",null);
        try {
            PreparedStatement pst2 = connectionDB(currentContext).prepareStatement
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
            Toast.makeText(currentContext,"Ocurrio un error en la BD", Toast.LENGTH_SHORT).show();
        }
        return comentario;
    }

    public static Evento getEvento(Context currentContext, int idEvento){
        Evento evento = new Evento();
        try{
            PreparedStatement pst = connectionDB(currentContext).prepareStatement
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
            Toast.makeText(currentContext,"Ocurrio un error en la BD", Toast.LENGTH_SHORT).show();
        }
        return evento;
    }

    public static Noticia getNoticia(Context currentContext, int idNoticia){
        Noticia noticia = new Noticia();
        try{
            PreparedStatement pst = connectionDB(currentContext).prepareStatement
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
                noticia.setComentarios(getComentarios(currentContext, noticia.getIdNoticia()));
            }
        }
        catch (Exception e){
            Toast.makeText(currentContext,"Ocurrio un error en la BD", Toast.LENGTH_SHORT).show();
        }
        return noticia;
    }

    public static Articulo getArticulo(Context currentContext, int idArticulo){
        Articulo articulo = new Articulo(-1,-1,"","","",-1,0,
                null,0,null);
        try {
            PreparedStatement pst2 = connectionDB(currentContext).prepareStatement
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
            Toast.makeText(currentContext,"Ocurrio un error en la BD", Toast.LENGTH_SHORT).show();
        }
        return articulo;
    }
    public static Carrito getCarrito(Context currentContext, int idUsuario){
        Carrito carrito = new Carrito();
        ArrayList<Articulo> articulos = new ArrayList<>();
        try{
            PreparedStatement pst = connectionDB(currentContext).prepareStatement
                    ("select * from Carrito where idUsuario = ?");
            pst.setInt(1,idUsuario);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                carrito.setIdUsuario(rs.getInt("idUsuario"));
                articulos.add(getArticulo(currentContext, rs.getInt("idArticulo")));
            }
            carrito.setArticulos(articulos);
        }
        catch (Exception e){
            Toast.makeText(currentContext,"Ocurrio un error en la BD", Toast.LENGTH_SHORT).show();
        }
        return carrito;
    }

    public static ArrayList<Comentario> getComentarios (Context currentContext, int idNoticia){
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try{
            PreparedStatement pst = connectionDB(currentContext).prepareStatement
                    ("select * from Comentario where idNoticia = ?");
            pst.setInt(1,idNoticia);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                comentarios.add(new Comentario(rs.getInt("idComentario"), rs.getInt("idNoticia"),rs.getInt("idUsuario"),
                                rs.getString("comentario"), rs.getDate("fecha")));
            }
        }
        catch (Exception e){
            Toast.makeText(currentContext,"Ocurrio un error en la BD", Toast.LENGTH_SHORT).show();
        }
        return comentarios;
    }

    public static ArrayList<Integer> getFavoritos(Context currentContext, int idUsuario){
        ArrayList<Integer> favoritos = new ArrayList<>();
        try{
        PreparedStatement pst = connectionDB(currentContext).prepareStatement
                    ("select * from Favorito where idUsuario = ?");
            pst.setInt(1,idUsuario);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                favoritos.add(rs.getInt("idBanda"));
            }
        }
        catch (Exception e){
            Toast.makeText(currentContext,"Ocurrio un error en la BD", Toast.LENGTH_SHORT).show();
        }
        return favoritos;
    }
    public static ArrayList<Integer> getCalificaciones(Context currentContext, int idBanda){
        ArrayList<Integer> calificaciones = new ArrayList<>();
        try{
            PreparedStatement pst = connectionDB(currentContext).prepareStatement
                    ("select * from Calificacion where idBanda = ?");
            pst.setInt(1,idBanda);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                calificaciones.add(rs.getInt("calificacion"));
            }
        }
        catch (Exception e){
            Toast.makeText(currentContext,"Ocurrio un error en la BD", Toast.LENGTH_SHORT).show();
        }
        return calificaciones;
    }

    public static Muestra getMuestra(Context currentContext, int idUsuario){
        Muestra muestra = new Muestra(-1,"","");
        try{
            PreparedStatement pst = connectionDB(currentContext).prepareStatement
                    ("select * from Muestra where idUsuario = ?");
            pst.setInt(1,idUsuario);
            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                muestra = new Muestra(rs.getInt("idUsuario"),
                        rs.getString("titulo"), rs.getString("direccionMuestra"));
            }
        }
        catch (Exception e){
            Toast.makeText(currentContext,"Ocurrio un error en la BD", Toast.LENGTH_SHORT).show();
        }
        return muestra;
    }
    public static boolean login(Context currentContext, String user, String password){
        try{
            CallableStatement cstmt = null;

            cstmt = connectionDB(currentContext).prepareCall
                    ("exec userLogin ?,?");
            cstmt.setString(1,user);
            cstmt.setString(2,password);
            boolean result = cstmt.execute();
            if(result) {
                ResultSet rs = cstmt.getResultSet();

                if(rs.next()) {
                    if(rs.getInt("result") == 1){
                            Toast.makeText(currentContext,"LogIn Exitoso", Toast.LENGTH_LONG).show();
                        return true;
                    }
                    else{
                        Toast.makeText(currentContext,"Intento de LogIn Invalido", Toast.LENGTH_LONG).show();
                        return false;
                    }
                }
                else{
                    Toast.makeText(currentContext,"Intento de LogIn Invalido", Toast.LENGTH_LONG).show();

                }
            }
            else{
                Toast.makeText(currentContext,"Intento de LogIn Invalido", Toast.LENGTH_LONG).show();

            }
        }
        catch (Exception e){
            Toast.makeText(currentContext,"Intento de LogIn Invalido", Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
