package beans.dreamteam.musicbeans;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import beans.dreamteam.musicbeans.model.Carrito;
import beans.dreamteam.musicbeans.model.Evento;
import beans.dreamteam.musicbeans.model.Noticia;
import beans.dreamteam.musicbeans.model.Usuario;
import beans.dreamteam.musicbeans.utils.*;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Date;

public class LoginActivity extends AppCompatActivity{
    public EditText edUserName;
    public EditText edUserPassword;
    private waitinDialog waitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        edUserName = findViewById(R.id.login_edUserName);
        edUserPassword = findViewById(R.id.login_edUserPassword);
        Button btnLogin = findViewById(R.id.login_btnLogin);
        waitingDialog = new waitinDialog(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        edUserPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Login();
                }
                return false;
            }
        });
    }
    public void onResponse(boolean result){
        waitingDialog.dismiss();
        if(result){

            Toast.makeText(this,"Bienvenido " + Singleton.getInstance().usuarioLogeado.getNombre(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Intento de LogIn Invalido", Toast.LENGTH_LONG).show();
        }
    }
    //region Dialogs
    private class waitinDialog extends Dialog {
        Activity activity;

        public waitinDialog(Activity context) {
            super(context);
            activity = context;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.view_waiting_dialog);
            setCancelable(false);

            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.view_waiting_dialog, null);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = this.getWindow();
            lp.copyFrom(window.getAttributes());
            //This makes the dialog take up the full width
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }

    }
    //endregion

    //region async tasks
    private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private LoginActivity mCallback;
        private String user;
        private String password;
        public MyAsyncTask(LoginActivity callback, String user, String password) {
            mCallback = callback;
            this.user = user;
            this.password = password;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
             boolean result = DBConnection.login(user,password);
             if(result) {
                 ArrayList<Integer> idFavoritos;
                 Usuario usuario = DBConnection.getUsuario(user, password);
                 Singleton.getInstance().usuarioLogeado = usuario;

                 if(usuario.getTipo().equals("administrador")){
                     idFavoritos = DBConnection.getAllUsers(usuario.getIdUsuario());
                 }

                else{
                     idFavoritos = DBConnection.getFavoritos(usuario.getIdUsuario());
                     idFavoritos.add(usuario.getIdUsuario());
                 }
                 ArrayList<Usuario> favoritos = new ArrayList<>();
                 ArrayList<Noticia> noticias = new ArrayList<>();
                 ArrayList<Evento> eventos = new ArrayList<>();
                 for(Integer i: idFavoritos){
                     favoritos.add(DBConnection.getUsuario(i));
                     noticias.addAll(DBConnection.getNoticias(i));
                     eventos.addAll(DBConnection.getEventos(i));
                 }
                 Singleton.getInstance().favoritos = favoritos;
                 Singleton.getInstance().noticias = noticias;
                 Singleton.getInstance().eventos = eventos;
                 Singleton.getInstance().carrito = DBConnection.getCarrito(Singleton.getInstance().usuarioLogeado.getIdUsuario());

             }

             return result;

        }
        @Override
        protected void onPostExecute(Boolean result) {
            if (mCallback != null) {
                mCallback.onResponse(result); // will call onComplete() on MyActivity once the job is done
            }
        }

    }
    //endregion
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (waitingDialog != null) {
            waitingDialog.dismiss();
            waitingDialog = null;
        }
    }
    //region Request Methods
    private void Login() {
        try {
            boolean usernameError = CustomValidator.basicValidation(this, edUserName, 5);
            boolean passwordError = CustomValidator.basicValidation(this, edUserPassword, 6);
            if (!usernameError && !passwordError) {
                waitingDialog.show();
                new MyAsyncTask(this, edUserName.getText().toString(), edUserPassword.getText().toString()).execute();
            }
        } catch (Exception ex) {
            waitingDialog.dismiss();
            Log.e("performLogin_Ex", ex.getMessage());
        }
    }
    //endregion
}
