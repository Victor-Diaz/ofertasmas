package fei.mx.uv.ofertasmas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import fei.mx.uv.ofertasmas.model.Mensaje;
import fei.mx.uv.ofertasmas.remoto.API;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {
    private String correo;
    private String telefono;
    private String usuario;
    private String contrasena;
    private String contrasenaverificar;

    private EditText edt_correo;//este es el uno
    private EditText edt_telefono;
    private EditText edt_usuario;
    private EditText edt_contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        edt_correo = (EditText) findViewById(R.id.edt_correo);
        edt_telefono = (EditText) findViewById(R.id.edt_telefono);
        edt_usuario = (EditText) findViewById(R.id.edt_usuarrio);
        edt_contrasena = (EditText) findViewById(R.id.edt_contrasena);
    }

    //Mètodo para el adaptador
    public void registrar (View v){
        int contador_campos=0;//saber el numero de campos que estàn llenos
        //Obtener los objetos de tipo EditText
        //spn_ciudad es una variable que obtiene los objetos de tipo spinner realizados anteriormente


        //esto se hace para poder validar que no exista un campo vacio
        if (!(edt_correo.getText() != null &&  !edt_correo.getText().toString().isEmpty())){
            edt_correo.setError("Debes ingresar correo electrónico.");
        }else{
            correo = edt_correo.getText().toString();//cachar el correo obtenido del campo
            contador_campos += 1;
        }
        if (!(edt_telefono.getText() != null && !edt_telefono.getText().toString().isEmpty())){
            edt_telefono.setError("Debes ingresar número telefonico.");
        }else {
            telefono = edt_telefono.getText().toString();
            contador_campos = contador_campos + 1;//otra forma de sumar
        }
        if (!(edt_usuario.getText() != null && !edt_usuario.getText().toString().isEmpty())){
            edt_usuario.setError("Debes ingresar nombre de usuario.");
        }else {
            usuario = edt_usuario.getText().toString();
            contador_campos += 1;//otra forma de sumar
        }
        if (!(edt_contrasena.getText() != null && !edt_contrasena.getText().toString().isEmpty())){
            edt_contrasena.setError("Debes ingresar una contraseña.");
        }else {
            contrasena = edt_contrasena.getText().toString();
            contador_campos += 1;//otra forma de sumar
        }
        //Mostrar dialogo de informaciòn
        if (contador_campos==4) {
            AlertDialog dialogo = new AlertDialog.Builder(Registro.this).create();
            dialogo.setTitle("Valores ingresados...");
            dialogo.setMessage(String.format("Los valores ingresados son \n" +
                            "Correo: %s\nTeléfonon: %s\nUsuario: %s\nUna contrasaña" +
                            " \n\n¿Deseas continuar?",
                    correo,
                    telefono,
                    usuario));

            dialogo.setButton(AlertDialog.BUTTON_POSITIVE,
                    "Aceptar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                           registrarUsuario();
                        }
                    });

            dialogo.setButton(AlertDialog.BUTTON_NEGATIVE,
                    "Cancelar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            dialogo.show();
        }
    }

    public void registrarUsuario(){
        JSONObject object = new JSONObject();
        try {
            object.put("correoUsuario", edt_correo.getText().toString());
            object.put("nombreUsuario", edt_usuario.getText().toString());
            object.put("contrasenaUsuario", edt_contrasena.getText().toString());
            object.put("celularUsuario", edt_telefono.getText().toString());
        }catch (JSONException ex){
            ex.printStackTrace();
        }


        RequestBody body = RequestBody
                .create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        (new JSONObject()).toString());
        Call<Mensaje> call = API.Factory.getIstance(Registro.this).registrarUsuario(body);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                if (!response.body().error) {
                    Toast.makeText(Registro.this,
                            response.body().mensaje,
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(Registro.this,
                            response.body().mensaje,
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                Toast.makeText(Registro.this,
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
