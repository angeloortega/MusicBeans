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

import beans.dreamteam.musicbeans.R;
import beans.dreamteam.musicbeans.utils.CustomValidator;
import beans.dreamteam.musicbeans.utils.DBConnection;

public class SignUpActivity extends AppCompatActivity {
    public EditText edUserName;
    public EditText edName;
    public EditText edUserPassword;
    private waitinDialogSign waitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        edUserName = findViewById(R.id.new_user_edUserName);
        edName = findViewById(R.id.new_user_edName);
        edUserPassword = findViewById(R.id.new_user_edUserPassword);
        Button btnNewUser = findViewById(R.id.new_user_btnnew_user);
        waitingDialog = new waitinDialogSign(this);
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        edUserPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    signUp();
                }
                return false;
            }
        });
    }
    public void onResponse(boolean result){
        waitingDialog.dismiss();
        if(result){
            Toast.makeText(this,"Creación de usuario exitosa", Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(this, "Intento de creación de usuario invalido", Toast.LENGTH_LONG).show();
        }
    }
    //region Dialogs
    private class waitinDialogSign extends Dialog {
        Activity activity;

        public waitinDialogSign(Activity context) {
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
    private class MyAsyncTaskSign extends AsyncTask<Void, Void, Boolean> {

        private SignUpActivity mCallback;
        private String user;
        private String password;
        private String name;
        public MyAsyncTaskSign(SignUpActivity callback,String name, String user, String password) {
            mCallback = callback;
            this.user = user;
            this.password = password;
            this.name = name;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            return DBConnection.signup(user,name,password);
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
    private void signUp() {
        try {
            boolean usernameError = CustomValidator.basicValidation(this, edUserName, 5);
            boolean passwordError = CustomValidator.basicValidation(this, edUserPassword, 6);
            if (!usernameError && !passwordError) {
                waitingDialog.show();
                new MyAsyncTaskSign(this, edName.getText().toString() ,edUserName.getText().toString(), edUserPassword.getText().toString()).execute();
            }
            else{
                Toast.makeText(this, "Intento de creacion de usuario invalido", Toast.LENGTH_LONG).show();

            }
        } catch (Exception ex) {
            waitingDialog.dismiss();
            Log.e("performLogin_Ex", ex.getMessage());
        }
    }
    //endregion
}