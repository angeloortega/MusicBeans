package beans.dreamteam.musicbeans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import beans.dreamteam.musicbeans.model.Muestra;
import beans.dreamteam.musicbeans.model.Usuario;

public class agregar_cancion extends AppCompatActivity {

    TextInputLayout titulo;
    TextInputLayout direccionMuestra;
    Usuario banda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cancion);
        Button agregarCancion = findViewById(R.id.agregarCancion);
        titulo =  (TextInputLayout) findViewById(R.id.entryNombreCancion);
        direccionMuestra = (TextInputLayout) findViewById(R.id.inputLinkCancion);
        agregarCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo_String = titulo.getEditText().getText().toString();
                String dirMuestra = direccionMuestra.getEditText().getText().toString();
                Muestra muestra = new Muestra(banda.getIdUsuario(),titulo_String,dirMuestra);
            }
        });
    }
}
