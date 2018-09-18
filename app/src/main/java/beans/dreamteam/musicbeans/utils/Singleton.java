package beans.dreamteam.musicbeans.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import beans.dreamteam.musicbeans.model.Usuario;

public class Singleton {
    private String _appVer;
    public Usuario usuarioLogeado;
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


}

