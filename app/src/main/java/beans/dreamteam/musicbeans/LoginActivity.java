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
import beans.dreamteam.musicbeans.utils.*;

import java.lang.annotation.Documented;
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

    @Override
    public void onResponse(String response) {
        waitingDialog.dismiss();

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

    //region Request Methods
    private void Login() {
        try {
            boolean usernameError = CustomValidator.basicValidation(this, edUserName, 5);
            boolean passwordError = CustomValidator.basicValidation(this, edUserPassword, 6);
            if (!usernameError && !passwordError) {
                waitingDialog.show();
                AsyncTask AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                    }

                    @Override
                    protected void onPostExecute() {
                        super.onPostExecute(jsonResponse);
                    }
                    protected void onPostExecute() {
                        //dismiss progress dialog if needed
                        //Callback function in MainActivity to indicate task is done
                        activity.taskDone("some string");
                }
                });

                if(conectado){

                }
                else{

                }
            }
        } catch (Exception ex) {
            waitingDialog.dismiss();
            Log.e("performLogin_Ex", ex.getMessage());
        }
    }
    //endregion
}
