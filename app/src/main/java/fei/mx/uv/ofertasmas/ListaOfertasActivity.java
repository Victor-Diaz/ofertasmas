package fei.mx.uv.ofertasmas;

/*import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListaOfertasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ofertas);
    }
} */
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import fei.mx.uv.ofertasmas.model.Estado;
import fei.mx.uv.ofertasmas.remoto.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaOfertasActivity extends AppCompatActivity {
    private String categoria;

    public  String [] categorias ={
            "Deportes",
            "Hogar",
            "Alimentos",
            "Tecnologia",
            "Otros xD"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ofertas);

        //llenar el spinner a traves del arreglo
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        //Crear adaptador para el spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categorias);//adaptador categorias
        //Asigna el estilo de las listas desplegables
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//estilo de categorias

        //Asignar el adaptador3 al spinner
        spinner3.setAdapter(dataAdapter3);//ciudades

        crearDialogoEstados();
    }

    //metodo para boton
    public void onclick (View v) {
        //Obtener los objetos de tipo spinner
        Spinner spn_categoria = (Spinner) findViewById(R.id.spinner3);
        categoria = spn_categoria.getSelectedItem().toString();
        //Mostrar dialogo de informacion
        AlertDialog dialogo = new AlertDialog.Builder(ListaOfertasActivity.this).create();
        dialogo.setTitle("Valores seleccionados");
        dialogo.setMessage(String.format("Los valores seleccionados son \n"+
                        "Categoria: %s\n"+
                        " \n\n¿Deseas continuar?",
                categoria
                )
        );

        dialogo.setButton(AlertDialog.BUTTON_POSITIVE,
                "Aceptar",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which){
                        //llamar segunda actividad, metodo definido abajo
                        //llamarASegundaActividad();
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

    private void crearDialogoEstados() {
        Call<List<Estado>> call = API.Factory.getIstance(ListaOfertasActivity.this).getEstados();
        call.enqueue(new Callback<List<Estado>>() {
            @Override
            public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                final String[] estados = new String[response.body().size()];
                for (int i = 0; i < response.body().size(); i++) {
                    estados[i] = response.body().get(i).getNombreEstado();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(ListaOfertasActivity.this);
                builder.setTitle("Elija su ubicacion")
                        .setItems(estados, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ListaOfertasActivity.this, estados[i], Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public void onFailure(Call<List<Estado>> call, Throwable t) {
                Toast.makeText(ListaOfertasActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}
