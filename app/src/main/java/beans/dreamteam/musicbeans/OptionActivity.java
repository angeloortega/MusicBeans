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

public class OptionActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        Button btnLogin = findViewById(R.id.option_btnlogin);
        Button btnSignup = findViewById(R.id.option_btnsignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    //region Request Methods
    private void login() {
        Intent intent = new Intent(OptionActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    private void signup() {
        Intent intent = new Intent(OptionActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
    //endregion
}
