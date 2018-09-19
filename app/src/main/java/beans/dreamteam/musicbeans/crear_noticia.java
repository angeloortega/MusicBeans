package beans.dreamteam.musicbeans;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Calendar;
import java.util.UUID;

import beans.dreamteam.musicbeans.model.Noticia;
import beans.dreamteam.musicbeans.model.Usuario;
import beans.dreamteam.musicbeans.utils.DBConnection;
import beans.dreamteam.musicbeans.utils.Singleton;

public class crear_noticia extends AppCompatActivity {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    TextInputLayout titulo;
    TextInputLayout descripcion;
    ImageView img;
    private static final int SELECT_FILE = 1;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_noticia);
        titulo = (TextInputLayout) findViewById(R.id.inputTitulo);
        descripcion = (TextInputLayout) findViewById(R.id.textoNoticia);
        Button agregarNoticia = findViewById(R.id.agregarNoticia);
        Button agregarImg = findViewById(R.id.agregarImg);
        img = findViewById(R.id.previewImgNoticia);
        agregarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(intent, "Seleccione una imagen"),
                        SELECT_FILE);
            }
        });
        agregarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tituloNoticia = titulo.getEditText().getText().toString();
                String descripcionNoticia = descripcion.getEditText().getText().toString();
                final Noticia noticia = new Noticia();
                noticia.setIdBanda(Singleton.getInstance().usuarioLogeado.getIdUsuario());
                noticia.setTitulo(tituloNoticia);
                noticia.setCuerpo(descripcionNoticia);
                noticia.setFecha(Calendar.getInstance().getTime());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG,25,baos);
                byte[] data = baos.toByteArray();
                final String path = "noticias/" + UUID.randomUUID() + ".png";
                final StorageReference fire = storage.getReference(path);
                UploadTask uploadTask = fire.putBytes(data);
                uploadTask.addOnSuccessListener(crear_noticia.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                    fire.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            noticia.setFoto(uri.toString());
                            boolean result = DBConnection.addNoticia(noticia);
                            if(result){
                                Singleton.getInstance().noticias.add(noticia);
                                Intent intent = new Intent(crear_noticia.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(crear_noticia.this, "Hubo un error al insertar la noticia, verifique los datos", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
                });



            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImageUri = null;
        Uri selectedImage;
        String filePath = null;
        switch (requestCode) {
            case SELECT_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    String selectedPath=selectedImage.getPath();
                    if (requestCode == SELECT_FILE) {
                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getContentResolver().openInputStream(
                                        selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            bmp = BitmapFactory.decodeStream(imageStream);
                            img.setImageBitmap(bmp);
                        }
                    }
                }
                break;
        }
    }
}
