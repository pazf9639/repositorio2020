package mx.com.example.fragmento3.gui;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mx.com.example.fragmento3.R;
import mx.com.example.fragmento3.databinding.FragmentTopJuegosBinding;
import mx.com.example.fragmento3.gui.components.JuegosAdapter;
import mx.com.example.fragmento3.gui.components.NavigationIconClickListener;
import mx.com.example.fragmento3.model.juego;

public class TopJuegos extends Fragment {

    private FragmentTopJuegosBinding binding;

    private View view;
    private Context context;
    private List<juego> juegos=new ArrayList<>();

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
        MainActivity.GLOBALS.put("TopJuegosFragment",this);
    }
    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridTopGames),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }
    private void configUI() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridTopGames).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }
    private void configRecycler() {
        juegos.add(new juego(2,"call","call of duty: Modern warfare",2,"Videojuego de disparos en primera persona de estilo bélico, desarrollado por Infinity Ward "));
        juegos.add(new juego(3,"minecraft","Minecraft",4,"Minecraft es un videojuego de construcción, de tipo «mundo abierto» o sandbox "));
        juegos.add(new juego(4,"mario","Super mario galaxy 2",3," Es un videojuego de plataformas desarrollado por Nintendo EAD Tokio "));
        juegos.add(new juego(5,"valorant","Valorant",5,"Videojuego de disparos en primera persona multijugador gratuito desarrollado y publicado por Riot Games"));

        binding.rclvTopJuegos.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rclvTopJuegos.setLayoutManager(layoutManager);
        binding.rclvTopJuegos.setAdapter(new JuegosAdapter(juegos));
    }
    private void configView(LayoutInflater inflater,ViewGroup container) {
        binding=FragmentTopJuegosBinding.inflate(inflater,container,false);
        view=binding.getRoot();
        context=container.getContext();
    }


}