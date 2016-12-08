package fei.mx.uv.ofertasmas.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fei.mx.uv.ofertasmas.CuponActivity;
import fei.mx.uv.ofertasmas.R;
import fei.mx.uv.ofertasmas.model.Cupon;
import fei.mx.uv.ofertasmas.model.Oferta;
import fei.mx.uv.ofertasmas.remoto.RestAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuponAdapter extends RecyclerView.Adapter<CuponAdapter.ViewHolder> {

    private List<Cupon> dataSet;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout linearLayout;
        public TextView tvNombre;
        public TextView tvDescripcion;
        public TextView tvEstadoCupon;

        public ViewHolder(View v) {
            super(v);
            linearLayout = (LinearLayout) v.findViewById(R.id.linLayoutMisOfertas);
            tvNombre = (TextView) v.findViewById(R.id.tvMisOferName);
            tvDescripcion = (TextView) v.findViewById(R.id.tvMisOferDescr);
            tvEstadoCupon = (TextView) v.findViewById(R.id.tvMisOferEstado);
        }
    }

    public CuponAdapter(List<Cupon> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.mis_ofertas_rv_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Cupon c = dataSet.get(position);
        Call<Oferta> call = RestAPI.Factory.INSTANCE.getIstance(context).ofertaPorId(c.getIdOferta());
        call.enqueue(new Callback<Oferta>() {
            @Override
            public void onResponse(Call<Oferta> call, Response<Oferta> response) {
                Oferta o = response.body();
                holder.tvNombre.setText(o.getNombreOferta());
                holder.tvDescripcion.setText(o.getDescripcionOferta());
                switch (c.getIdEstadoCupon()) {
                    case 1:
                        holder.tvEstadoCupon.setText("DISPONIBLE");
                        break;
                    case 2:
                        holder.tvEstadoCupon.setText("CADUCADO");
                        break;
                    case 3:
                        holder.tvEstadoCupon.setText("CANJEADO");
                        break;
                    default:
                        break;
                }
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CuponActivity.class);
                        intent.putExtra("codigoCupon", c.getCodigoCupon());
                        view.getContext().startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<Oferta> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
