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
import java.util.Calendar;
import java.util.List;
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
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Usuario where correo = ? and contrasenha = ?");
            pst.setString(1,userName);
            pst.setString(2,password);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                user = new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"),
                        rs.getString("correo"), rs.getString("contrasenha"),
                        rs.getString("tipo"),rs.getString("descripcion"),
                        rs.getString("genero"), rs.getInt("anhoFundacion"),
                        rs.getString("foto"));
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return null;
        }
        return user;
    }
    public static Usuario getUsuario(Integer id){
        Usuario user = new Usuario(-1,null,null,null,null,
                null,null,null,null);
        try{
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Usuario where idUsuario = ?");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                user = new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"),
                        rs.getString("correo"), rs.getString("contrasenha"),
                        rs.getString("tipo"),rs.getString("descripcion"),
                        rs.getString("genero"), rs.getInt("anhoFundacion"),
                        rs.getString("foto"));
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return null;
        }
        return user;
    }
    public static Comentario getComentario ( int idComentario){

        Comentario comentario = new Comentario(-1,-1,-1,"",null);
        try {
            Connection cn = connectionDB();
            PreparedStatement pst2 = cn.prepareStatement
                    ("select * from Comentario where idComentario = ?");
            pst2.setInt(1, idComentario);
            ResultSet rs2 = pst2.executeQuery();

            if(rs2.next()){
                comentario = new Comentario(rs2.getInt("idComentario"), rs2.getInt("idNoticia"),
                        rs2.getInt("idUsuario"), rs2.getString("comentario"),
                        rs2.getDate("fecha"));
            }
            cn.close();
            cn = null;
        }
        catch(Exception e){
            return null;
        }
        return comentario;
    }

    public static Evento getEvento( int idEvento){
        Evento evento = new Evento();
        try{
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Noticia where idEvento = ?");
            pst.setInt(1,idEvento);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                evento.setIdBanda(rs.getInt("idUsuario"));
                evento.setIdBanda(rs.getInt("idUsuario"));
                evento.setTitulo(rs.getString("titulo"));
                evento.setPrecio(rs.getFloat("precio"));
                evento.setFchPublicacion(rs.getDate("fechaPublicacion"));
                evento.setFoto(rs.getString("foto"));
                evento.setFchEvento(rs.getDate("fechaEvento"));
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return null;
        }
        return evento;
    }

    public static ArrayList<Noticia> getNoticias(int idBanda){
        ArrayList<Noticia> noticias = new ArrayList<>();
        Noticia noticia;
        try{
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Noticia where idUsuario = ?");
            pst.setInt(1,idBanda);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                noticia = new Noticia();
                noticia.setIdNoticia(rs.getInt("idNoticia"));
                noticia.setIdBanda(rs.getInt("idUsuario"));
                noticia.setCuerpo(rs.getString("cuerpo"));
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setIdNoticia(rs.getInt("idNoticia"));
                noticia.setFecha(rs.getDate("fecha"));
                noticia.setFoto(rs.getString("foto"));
                noticia.setComentarios(getComentarios( noticia.getIdNoticia()));
                noticias.add(noticia);
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return null;
        }
        return noticias;
    }

    public static ArrayList<Evento> getEventos(int idBanda){
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento noticia;
        try{
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Evento where idUsuario = ?");
            pst.setInt(1,idBanda);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                noticia = new Evento();
                noticia.setIdEvento(rs.getInt("idEvento"));
                noticia.setIdBanda(rs.getInt("idUsuario"));
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setPrecio(rs.getFloat("precio"));
                noticia.setFchPublicacion(rs.getDate("fechaPublicacion"));
                noticia.setFchEvento(rs.getDate("fechaEvento"));
                noticia.setFoto(rs.getString("foto"));
                eventos.add(noticia);
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return null;
        }
        return eventos;
    }

    public static Noticia getNoticia( int idNoticia){
        Noticia noticia = new Noticia();
        try{
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Noticia where idNoticia = ?");
            pst.setInt(1,idNoticia);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                noticia.setIdNoticia(idNoticia);
                noticia.setIdBanda(rs.getInt("idUsuario"));
                noticia.setCuerpo(rs.getString("cuerpo"));
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setIdNoticia(rs.getInt("idNoticia"));
                noticia.setFecha(rs.getDate("fecha"));
                noticia.setFoto(rs.getString("foto"));
                noticia.setComentarios(getComentarios( noticia.getIdNoticia()));
            }
            cn.close();
            cn = null;
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
            Connection cn = connectionDB();
            PreparedStatement pst2 = cn.prepareStatement
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
                        rs2.getString("foto")
                );
            }
            cn.close();
            cn = null;
        }
        catch(Exception e){
            return null;
        }
        return articulo;
    }
    public static ArrayList<Articulo> getArticulos( int idBanda){
        ArrayList<Articulo> articulos = new ArrayList<>();
        Articulo articulo = new Articulo(-1,-1,"","","",-1,0,
                null,0,null);
        try {
            Connection cn = connectionDB();
            PreparedStatement pst2 = cn.prepareStatement
                    ("select * from Articulo where idUsuario = ?");

            pst2.setInt(1, idBanda);
            ResultSet rs2 = pst2.executeQuery();

            if(rs2.next()){
                articulo = new Articulo(rs2.getInt("idUsuario"), rs2.getInt("idArticulo"),
                        rs2.getString("nombre"), rs2.getString("categoria"),
                        rs2.getString("descripcion"),
                        rs2.getFloat("precio"),
                        rs2.getInt("cantidadDisponible"),
                        rs2.getDate("fechaPublicacion"),
                        0,
                        rs2.getString("foto")
                );
                articulos.add(articulo);
            }
            cn.close();
            cn = null;
        }
        catch(Exception e){
            return null;
        }
        return articulos;
    }
    public static Carrito getCarrito( int idUsuario){
        Carrito carrito = new Carrito();
        ArrayList<Articulo> articulos = new ArrayList<>();
        try{
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Carrito where idUsuario = ?");
            pst.setInt(1,idUsuario);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                carrito.setIdUsuario(rs.getInt("idUsuario"));
                articulos.add(getArticulo( rs.getInt("idArticulo")));
            }
            carrito.setArticulos(articulos);
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return null;
        }
        return carrito;
    }

    public static ArrayList<Comentario> getComentarios ( int idNoticia){
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try{
            Connection cn = connectionDB();

            PreparedStatement pst = cn.prepareStatement
                    ("select * from Comentario where idNoticia = ?");
            pst.setInt(1,idNoticia);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                comentarios.add(new Comentario(rs.getInt("idComentario"), rs.getInt("idNoticia"),rs.getInt("idUsuario"),
                                rs.getString("comentario"), rs.getDate("fecha")));
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return null;
        }
        return comentarios;
    }

    public static ArrayList<Integer> getAllUsers(int idUsuario){
        ArrayList<Integer> favoritos = new ArrayList<>();
        try{
            Connection cn = connectionDB();

            PreparedStatement pst = cn.prepareStatement
                    ("select * from Usuario where idUsuario <> ?");
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
    public static ArrayList<Integer> getFavoritos( int idUsuario){
        ArrayList<Integer> favoritos = new ArrayList<>();
        try{
            Connection cn = connectionDB();

            PreparedStatement pst = cn.prepareStatement
                    ("select * from Favorito where idUsuario = ?");
            pst.setInt(1,idUsuario);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                favoritos.add(rs.getInt("idBanda"));
            }

            pst = cn.prepareStatement
                    ("select * from Usuario where tipo = 'administrador'");
             rs = pst.executeQuery();

            while(rs.next()){
                favoritos.add(rs.getInt("idUsuario"));
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return null;
        }
        return favoritos;
    }
    public static ArrayList<Integer> getCalificaciones( int idBanda){
        ArrayList<Integer> calificaciones = new ArrayList<>();
        try{
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Calificacion where idBanda = ?");
            pst.setInt(1,idBanda);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                calificaciones.add(rs.getInt("calificacion"));
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return null;
        }
        return calificaciones;
    }

    public static int getCalificacion(int idUsuario, int idBanda){
        int calificacion = 0;
        try{
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Calificacion where idBanda = ? AND idUsuario = ?");
            pst.setInt(2,idBanda);
            pst.setInt(1,idUsuario);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                calificacion = rs.getInt("calificacion");
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return 0;
        }
        return calificacion;
    }

    public static Muestra getMuestra( int idUsuario){
        Muestra muestra = new Muestra(-1,"","");
        try{
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Muestra where idUsuario = ?");
            pst.setInt(1,idUsuario);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                muestra = new Muestra(rs.getInt("idUsuario"),
                        rs.getString("titulo"), rs.getString("direccionMuestra"));
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
            return null;
        }
        return muestra;
    }
    public static boolean login( String user, String password){
        try{
            CallableStatement cstmt = null;
            Connection cn = connectionDB();
            cstmt = cn.prepareCall
                    ("exec userLogin ?,?");
            cstmt.setString(1,user);
            cstmt.setString(2,password);
            boolean result = cstmt.execute();

            if(result) {
                ResultSet rs = cstmt.getResultSet();
                if(rs.next()) {
                    if (rs.getInt("result") == 1) {
                        cn.close();
                        cn = null;
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

    public static boolean rating(int idUsuario, int idBanda, int calificacion){
        try{
            CallableStatement cstmt = null;
            Connection cn = connectionDB();
            cstmt = cn.prepareCall
                    ("exec calificar ?,?,?");
            cstmt.setInt(1,idUsuario);
            cstmt.setInt(2,idBanda);
            cstmt.setInt(3,calificacion);
            cstmt.execute();
        }
        catch (Exception e){

            return false;
        }
        return true;
    }

    public static boolean signup( String user, String fullName, String password){
        try{
            Statement cstmt = null;
            Connection cn = connectionDB();
            cstmt = connectionDB().createStatement();
            int result = cstmt.executeUpdate("insert into Usuario values ('"+fullName+"', '"+user+"', '"+password+"', 'Cliente', null,null,null,null)");

            cn.close();
            cn = null;
            if(result > 0) {
                return true;
            }
        }
        catch (Exception e){
        }
        return false;
    }

    public static boolean addNoticia(Noticia ntc){
        try{
            Statement cstmt = null;
            Connection cn = connectionDB();
            cstmt = connectionDB().createStatement();
            int result = cstmt.executeUpdate("insert into Noticia values ("+ntc.getIdBanda()+", '"+ntc.getTitulo()+"', '"+ntc.getCuerpo()+"', '"
                    + android.text.format.DateFormat.format("MM-dd-yyyy", Calendar.getInstance().getTime())
                    +"', '"+ ntc.getFoto()+"')");

            cn.close();
            cn = null;
            if(result > 0) {
                return true;
            }
        }
        catch (Exception e){
        }
        return false;
    }


    public static boolean deleteNoticia(Noticia ntc){
        try{
            Statement cstmt = null;
            Connection cn = connectionDB();
            cstmt = connectionDB().createStatement();
            int result = cstmt.executeUpdate("delete from Noticia where idUsuario = "+ntc.getIdBanda()+" AND titulo = '"+ntc.getTitulo()+"'");

            cn.close();
            cn = null;
            if(result > 0) {
                return true;
            }
        }
        catch (Exception e){
        }
        return false;
    }

    public static boolean deleteFavorito(int userId, int bandaId){
        try{
            Statement cstmt = null;
            Connection cn = connectionDB();
            cstmt = connectionDB().createStatement();
            int result = cstmt.executeUpdate("delete from Favorito where idUsuario = "+userId+" AND idBanda = '"+bandaId+"'");
            cn.close();
            cn = null;
            if(result > 0) {
                return true;
            }
        }
        catch (Exception e){
        }
        return false;
    }
    public static boolean addFavorito(int userId, int bandaId) {
        try{
            Statement cstmt = null;
            Connection cn = connectionDB();
            cstmt = connectionDB().createStatement();
            int result = cstmt.executeUpdate("insert into Favorito values ("+bandaId+", "+userId+")");

            cn.close();
            cn = null;
            if(result > 0) {
                return true;
            }
        }
        catch (Exception e){
        }
        return false;
    }


    public static boolean addArticuloCarrito(Articulo articulo, int idUsuario) {
        try{
            Statement cstmt = null;
            Connection cn = connectionDB();
            cstmt = connectionDB().createStatement();
            int result = cstmt.executeUpdate("insert into Carrito values ("+idUsuario+", "+articulo.getIdArticulo()+", 1)");

            cn.close();
            cn = null;
            if(result > 0) {
                return true;
            }
        }
        catch (Exception e){
        }
        return false;
    }

    public static boolean removeArticuloCarrito(Articulo art, int idUsuario) {
        try{
            Statement cstmt = null;
            Connection cn = connectionDB();
            cstmt = connectionDB().createStatement();
            int result = cstmt.executeUpdate("delete from Carrito where idUsuario = "+idUsuario+" AND idArticulo = "+art.getIdArticulo());

            cn.close();
            cn = null;
            if(result > 0) {
                return true;
            }
        }
        catch (Exception e){
        }
        return false;
    }

    public static void clearCarrito(int idUsuario) {
        try{
            Statement cstmt = null;
            Connection cn = connectionDB();
            cstmt = connectionDB().createStatement();
            int result = cstmt.executeUpdate("delete from Carrito where idUsuario = "+idUsuario);

            cn.close();
            cn = null;
        }
        catch (Exception e){
        }
    }
    public static List<Usuario> searchQuery(String bandName){
        List<Usuario> bandasBuscadas = new ArrayList<>();
        System.out.println(bandName);
        Usuario user;
        try{
            Connection cn = connectionDB();
            PreparedStatement pst = cn.prepareStatement
                    ("select * from Usuario where nombre like ?");
            pst.setString(1,"%"+bandName+"%");
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                user = new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"),rs.getString("correo") ,"", "banda",
                        rs.getString("descripcion"),rs.getString("genero"),rs.getInt("anhoFundacion"),rs.getString("foto"));
                bandasBuscadas.add(user);
            }
            cn.close();
            cn = null;
        }
        catch (Exception e){
        }
        return bandasBuscadas;
    }
}
