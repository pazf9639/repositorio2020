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
import mx.com.example.fragmento3.gui.components.JuegosAdapter;
import mx.com.example.fragmento3.gui.components.NavigationIconClickListener;
import mx.com.example.fragmento3.model.juego;

public class Vieja extends Fragment {

    private mx.com.example.fragmento3.databinding.FragmentViejaBinding binding;
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
        MainActivity.GLOBALS.put("ViejaFragment",this);
    }


    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(binding.appBar4);
        }
        binding.appBar4.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridVieja),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }


    private void configUI() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridVieja).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }


    private void configRecycler() {
        juegos.add(new juego(1,"kin","The king of fighters ",5,"Saga de videojuegos de lucha inicialmente para el sistema Neo Geo desarrollada por la compañía SNK."));
        juegos.add(new juego(2,"mario6","Mario 64",2,"Videojuego de plataformas de mundo abierto para la videoconsola Nintendo 64"));
        juegos.add(new juego(3,"ocarina","Legend of zelda: ocarina of time",4,"Videojuego de acción-aventura de 1998 desarrollado por la filial Nintendo EAD"));
        juegos.add(new juego(4,"kart","Mario kart",3,"Videojuegos de carreras desarrollados y distribuidos por Nintendo como spin-offs"));
        juegos.add(new juego(5,"pac","Pac-man",5," Franquicia de videojuegos japonesa publicada, desarrollada y propiedad de Bandai Namco"));
        binding.rclvVieja.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        binding.rclvVieja.setLayoutManager(layoutManager);
        binding.rclvVieja.setAdapter(new JuegosAdapter(juegos));
    }


    private void configView(LayoutInflater inflater,ViewGroup container) {
        binding= mx.com.example.fragmento3.databinding.FragmentViejaBinding.inflate(inflater,container,false);
        view=binding.getRoot();
        context=container.getContext();
    }
}




