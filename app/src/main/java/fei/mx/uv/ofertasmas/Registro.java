package fei.mx.uv.ofertasmas;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registro extends AppCompatActivity {
    private String correo;
    private String telefono;
    private String usuario;
    private String contrasena;
    private String contrasenaverificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    //Mètodo para el adaptador
    public void onClick (View v){
        int contador_campos=0;//saber el numero de campos que estàn llenos
        //Obtener los objetos de tipo EditText
        //spn_ciudad es una variable que obtiene los objetos de tipo spinner realizados anteriormente
        EditText editText = (EditText) findViewById(R.id.editText);//este es el uno
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        EditText editText4 = (EditText) findViewById(R.id.editText4);
        EditText editText5 = (EditText) findViewById(R.id.editText5);

        //esto se hace para poder validar que no exista un campo vacio
        if (!(editText.getText() != null &&  !editText.getText().toString().isEmpty())){
            editText.setError("Debes ingresar correo electrónico.");
        }else{
            correo = editText.getText().toString();//cachar el correo obtenido del campo
            contador_campos += 1;
        }
        if (!(editText2.getText() != null && !editText2.getText().toString().isEmpty())){
            editText2.setError("Debes ingresar número telefonico.");
        }else {
            telefono = editText2.getText().toString();
            contador_campos = contador_campos + 1;//otra forma de sumar
        }
        if (!(editText3.getText() != null && !editText3.getText().toString().isEmpty())){
            editText3.setError("Debes ingresar nombre de usuario.");
        }else {
            usuario = editText3.getText().toString();
            contador_campos += 1;//otra forma de sumar
        }
        if (!(editText4.getText() != null && !editText4.getText().toString().isEmpty())){
            editText4.setError("Debes ingresar una contraseña.");
        }else {
            contrasena = editText4.getText().toString();
            contador_campos += 1;//otra forma de sumar
        }
        if (!(editText5.getText() != null && !editText5.getText().toString().isEmpty())){
            editText5.setError("Debes verificar la contraseña.");
        }else {
            //if(editText4.getText()==editText5.getText()){//pregunta si la contraseña es la misma
                contrasenaverificar = editText5.getText().toString();
                contador_campos += 1;//otra forma de sumar
        }
        //QUERIA VALIDAR LA MISMA CONTRASEÑA PERO CREO NO LO HACE
        if (!(editText4.getText() != editText5.getText())){
            editText5.setError("Debes ingresar la misma contraseña");
        }else{
            contrasena=contrasenaverificar;
        }
        //Mostrar dialogo de informaciòn
        if (contador_campos==5) {
            AlertDialog dialogo = new AlertDialog.Builder(Registro.this).create();
            dialogo.setTitle("Valores ingresados...");
            dialogo.setMessage(String.format("Los valores ingresados son \n" +
                            "Correo: %s\nTeléfonon: %s\nUsuario: %s\nUna contrsaña" +
                            " \n\n¿Deseas continuar?",
                    correo,
                    telefono,
                    usuario));

            dialogo.setButton(AlertDialog.BUTTON_POSITIVE,
                    "Aceptar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                           // llamarASegundaActividad();
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
}
