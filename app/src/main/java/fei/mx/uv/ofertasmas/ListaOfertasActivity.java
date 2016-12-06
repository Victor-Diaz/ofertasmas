package fei.mx.uv.ofertasmas;

import android.content.DialogInterface;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ofertas);

        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }


    private void crearDialogoEstados(){
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
