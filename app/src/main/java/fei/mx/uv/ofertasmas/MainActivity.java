package fei.mx.uv.ofertasmas;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import fei.mx.uv.ofertasmas.model.Estado;
import fei.mx.uv.ofertasmas.remoto.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String estado;//Valor seleccionado del arreglo
    private String ciudad;//Valor seleccionado del arreglo

    public String [] estados= {
            "Veracruz",
            "Cd Mexico",
            "Guadalajara",
            "Monterrey"
    };
    public String [] ciudades={
            //considerando que selecciona veracruz
            "Xalapa",
            "Veracruz",
            "Cordoba",
            "Coatepec",
            "Banderilla"
    };
    //PROBANDO COSAS NUEVAS
    /*
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder builder = new AlertDialog.Builder();
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_main, null))
                // Add action buttons
                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }  */

    //ESTO ESTARÁ COMENTADO POR QUE VAMOS A PROBAR ALGUNAS COSAS Xd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //llenar el spinner a traves del arreglo
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        //Crear adaptador para el spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,estados);//adaptador estados
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ciudades);//adaptador estados

        //Asigna el estilo de las listas desplegables
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//estilo de estados
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//estilo de ciudades

        //Asignar el adaptador al spinner
        spinner.setAdapter(dataAdapter);//estados
        spinner2.setAdapter(dataAdapter2);//ciudades

    }

    //metodo para boton
    public void onclick (View v) {
        //Obtener los objetos de tipo spinner
        Spinner spn_estado = (Spinner) findViewById(R.id.spinner);
        Spinner spn_ciudad = (Spinner) findViewById(R.id.spinner2);
        estado = spn_estado.getSelectedItem().toString();
        ciudad = spn_ciudad.getSelectedItem().toString();


        //Mostrar dialogo de informacion
        AlertDialog dialogo = new AlertDialog.Builder(MainActivity.this).create();
        dialogo.setTitle("Valores seleccionados");
        dialogo.setMessage(String.format("Los valores seleccionados son \n"+
                        "Estado: %s\nCiudad: %s"+
                        " \n\n¿Deseas continuar?",
                estado,
                ciudad
                )
        );

        dialogo.setButton(AlertDialog.BUTTON_POSITIVE,
                "Aceptar",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which){
                        //llamar segunda actividad, metodo definido abajo
                        llamarASegundaActividad();//Queda pendiente
                    }
                });

        dialogo.setButton(AlertDialog.BUTTON_NEGATIVE,
                "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which){
                        dialog.dismiss();
                    }
                });
        dialogo.show();
    }

    private void llamarASegundaActividad(){
        //Intent intent = new Intent(this,ListaOfertasActivity.class);
        //Intent intent = new Intent(this,Registro.class);
        Intent intent = new Intent(this,LoginActivity.class);
        // intent.putExtra("val_carrera",carrera);
        //intent.putExtra("val_materia",materia);
        startActivity(intent);
    }

}
