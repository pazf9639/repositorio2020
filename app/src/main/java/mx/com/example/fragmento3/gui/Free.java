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
import mx.com.example.fragmento3.databinding.FragmentFreeBinding;
import mx.com.example.fragmento3.gui.components.JuegosAdapter;
import mx.com.example.fragmento3.gui.components.NavigationIconClickListener;
import mx.com.example.fragmento3.model.juego;

public class Free extends Fragment{

        private FragmentFreeBinding binding;
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
            MainActivity.GLOBALS.put("FreeFragment",this);
        }


        private void configToolbar() {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity != null) {
                activity.setSupportActionBar(binding.appBar3);
            }
            binding.appBar3.setNavigationOnClickListener(new NavigationIconClickListener(
                    context,
                    view.findViewById(R.id.gridFree),
                    new AccelerateDecelerateInterpolator(),
                    context.getDrawable(R.drawable.menu),
                    context.getDrawable(R.drawable.menu_open)
            ));
        }


        private void configUI() {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                view.findViewById(R.id.gridFree).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
            }
        }


        private void configRecycler() {
            juegos.add(new juego(1,"destiny2","Destiny 2",5,"Videojuego de disparos en primera persona, desarrollado y publicado por Bungie"));
            juegos.add(new juego(2,"league","League of Legends",2," Videojuego del género multijugador de arena de batalla en línea y deporte electrónico "));
            juegos.add(new juego(3,"poke","Pokémon GO",4,"Videojuego de realidad aumentada basado en la localización desarrollado por Niantic"));
            juegos.add(new juego(4,"runte","Legends of Runeterra",3,"Juego de cartas coleccionables digitales y gratuito desarrollado y publicado por Riot Games."));
            juegos.add(new juego(5,"roc","Rocket League",5,"Videojuego que combina el fútbol con los vehículos"));
            binding.rclvFree.setHasFixedSize(true);
            LinearLayoutManager layoutManager=new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
            binding.rclvFree.setLayoutManager(layoutManager);
            binding.rclvFree.setAdapter(new JuegosAdapter(juegos));
        }


        private void configView(LayoutInflater inflater,ViewGroup container) {
            binding= FragmentFreeBinding.inflate(inflater,container,false);
            view=binding.getRoot();
            context=container.getContext();
        }
    }



