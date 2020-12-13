package mx.com.example.fragmento3.gui.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import mx.com.example.fragmento3.model.Juego;
import mx.com.example.fragmento3.R;
public class AdministraAdapter extends RecyclerView.Adapter<AdministraAdapter.ViewHolder> {
    private List<Juego> administraciones;
    private Context context;

    public AdministraAdapter(List<Juego> administraciones) {
        this.administraciones = administraciones;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_administrar, parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Juego administracion = administraciones.get(position);
        String imgUri = administracion.getImagen();
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

                .centerCrop();

        Glide.with(context)
                .load(imgUri)
                .placeholder(R.drawable.ic_imagelis)
                .error(R.drawable.ic_broken)
                .apply(options)
                .into(holder.icAdministrar);

        holder.txtTitulo.setText(administracion.getTitulo());
        holder.rbClasificacion.setRating(administracion.getClasificacion());
        holder.txtDescripcion.setText(administracion.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return administraciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private AppCompatImageView icAdministrar;
        private TextView txtTitulo;
        private AppCompatRatingBar rbClasificacion;
        private TextView txtDescripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icAdministrar = itemView.findViewById(R.id.gamev);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            rbClasificacion = itemView.findViewById(R.id.rbClasificacion);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            this.view = itemView;
        }
    }
}
