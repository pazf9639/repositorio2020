package mx.com.example.fragmento3.gui.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.com.example.fragmento3.R;
import mx.com.example.fragmento3.model.Juego;
import mx.com.example.fragmento3.model.Juego;

public class JuegosAdapter extends RecyclerView.Adapter<JuegosAdapter.ViewHolder> {


    private List<Juego> juegos;
    private Context context;

    public JuegosAdapter(List<Juego> juegos) {
        this.juegos = juegos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_juego, parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*Juego juego = juegos.get(position);
        String imgUri = "@drawable/" + juego.getImagen();
        int imgResourse = context.getResources().getIdentifier(imgUri,null, context.getPackageName());
        holder.icJuego.setImageResource(imgResourse);
        holder.txtTitulo.setText(juego.getTitulo());
        holder.rbClasificacion.setRating(juego.getClasificacion());
        holder.txtDescripcion.setText(juego.getDescripcion());
*/
        Juego administracion = juegos.get(position);
        String imgUri = administracion.getImagen();
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                //.fitCenter();
                .centerCrop();

        Glide.with(context)
                .load(imgUri)
                .placeholder(R.drawable.ic_imagelis)
                .error(R.drawable.ic_broken)
                .apply(options)
                .into(holder.icJuego);
        //holder.icAdministrar.setImageResource(imgResourse);
        holder.txtTitulo.setText(administracion.getTitulo());
        holder.rbClasificacion.setRating(administracion.getClasificacion());
        holder.txtDescripcion.setText(administracion.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return juegos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private AppCompatImageView icJuego;
        private TextView txtTitulo;
        private AppCompatRatingBar rbClasificacion;
        private TextView txtDescripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icJuego = itemView.findViewById(R.id.icJuego);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            rbClasificacion = itemView.findViewById(R.id.rbClasificacion);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            this.view = itemView;
        }
    }
}
