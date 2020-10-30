//package mx.com.example.fragmento3.gui.components;
package mx.com.example.fragmento3.gui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mx.com.example.fragmento3.R;
import mx.com.example.fragmento3.databinding.FragmentRankeadoBinding;
import mx.com.example.fragmento3.gui.components.JuegosAdapter;
import mx.com.example.fragmento3.gui.components.NavigationIconClickListener;
import mx.com.example.fragmento3.model.juego;

public class Rankeado extends Fragment {
    private FragmentRankeadoBinding binding;
    private View view;
    private Context context;
    private List<juego> juegos=new ArrayList<>();
    private Button botonRan;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        configGlobals();
        configView(inflater,container);
        configToolbar();
        configUI();
        configRecycler();

        return view;
    }


    private void configGlobals() {
        MainActivity.GLOBALS.put("rankeadoFragment",this);
    }


    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(binding.appBar2);
        }
        binding.appBar2.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridRankeado),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }


    private void configUI() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridRankeado).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }


    private void configRecycler() {
        juegos.add(new juego(1,"zelda","Legend of zelda: Breath of the Wild",5,"Videojuego de acción-aventura de la serie The Legend of Zelda, desarrollado por Nintendo EPD"));
        juegos.add(new juego(2,"gta","Grand theft Auto V",2," Videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North"));
        juegos.add(new juego(3,"redred","Red Dead Redemption 2",4," Videojuego de acción-aventura western, en un mundo abierto y en perspectiva de primera y tercera persona"));
        juegos.add(new juego(4,"hunters","Metroid Prime hunters",3,"Videojuego de acción en primera persona para Nintendo DS"));
        juegos.add(new juego(5,"hnfinite","Halo Infinite",5,"Videojuego de disparos en primera persona"));
        binding.rclvRankeado.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        binding.rclvRankeado.setLayoutManager(layoutManager);
        binding.rclvRankeado.setAdapter(new JuegosAdapter(juegos));
    }


    private void configView(LayoutInflater inflater,ViewGroup container) {
        binding=FragmentRankeadoBinding.inflate(inflater,container,false);
        view=binding.getRoot();
        context=container.getContext();
    }
}
