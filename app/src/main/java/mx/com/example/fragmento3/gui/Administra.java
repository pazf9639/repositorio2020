package mx.com.example.fragmento3.gui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.com.example.fragmento3.R;
import mx.com.example.fragmento3.databinding.FragmentAdministrarBinding;
import mx.com.example.fragmento3.gui.components.AdministraAdapter;
import mx.com.example.fragmento3.gui.components.NavigationHost;
import mx.com.example.fragmento3.gui.components.NavigationIconClickListener;
import mx.com.example.fragmento3.model.Juego;


public class Administra extends Fragment {

    private FragmentAdministrarBinding binding;
    private View view;
    private Context context;
    private List<Juego> juegos = new ArrayList<>();

    private static final String PATH_TOP = "topJuegos";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configGlobals();
        configView(inflater, container);
        configToolBar();
        configUI();
        configRecycler();

        binding.fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost)getActivity()).navigateTo(new AgregFragment(), true);
            }
        });
        return view;
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("administrarFragment",this);
    }

    private void configView(LayoutInflater inflater,ViewGroup container) {
        binding = FragmentAdministrarBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        context = container.getContext();
    }

    private void configToolBar() {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if (activity!=null){
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context, view.findViewById(R.id.gridAdministrars),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu_open),
                context.getDrawable(R.drawable.menu)
        ));
    }

    private void configUI() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridAdministrars).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }

    private void configRecycler() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_TOP);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Juego juego = snapshot.getValue(Juego.class);

                if (!juegos.contains(juego)) {
                    juegos.add(juego);
                }
                binding.rclvAdministrars.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Juego juego = snapshot.getValue(Juego.class);
                if (juego != null){
                    Log.i("juego","onChildChanged: " + juego.getIdJuego());
                }

                juegos.set(juegos.indexOf(juego),juego);
                binding.rclvAdministrars.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.rclvAdministrars.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false);
        binding.rclvAdministrars.setLayoutManager(layoutManager);
        binding.rclvAdministrars.setAdapter(new AdministraAdapter(juegos));
    }
}
// juegos.add(new juego(2,"call","call of duty: Modern warfare",2,"Videojuego de disparos en primera persona de estilo bélico, desarrollado por Infinity Ward "));
//         juegos.add(new juego(3,"minecraft","Minecraft",4,"Minecraft es un videojuego de construcción, de tipo «mundo abierto» o sandbox "));
//         juegos.add(new juego(4,"mario","Super mario galaxy 2",3," Es un videojuego de plataformas desarrollado por Nintendo EAD Tokio "));
//         juegos.add(new juego(5,"valorant","Valorant",5,"Videojuego de disparos en primera persona multijugador gratuito desarrollado y publicado por Riot Games"));
//         juegos.add(new juego(1,"kin","The king of fighters ",5,"Saga de videojuegos de lucha inicialmente para el sistema Neo Geo desarrollada por la compañía SNK."));
//         juegos.add(new juego(2,"mario6","Mario 64",2,"Videojuego de plataformas de mundo abierto para la videoconsola Nintendo 64"));
//         juegos.add(new juego(3,"ocarina","Legend of zelda: ocarina of time",4,"Videojuego de acción-aventura de 1998 desarrollado por la filial Nintendo EAD"));
//         juegos.add(new juego(4,"kart","Mario kart",3,"Videojuegos de carreras desarrollados y distribuidos por Nintendo como spin-offs"));
//         juegos.add(new juego(5,"pac","Pac-man",5," Franquicia de videojuegos japonesa publicada, desarrollada y propiedad de Bandai Namco"));
//         juegos.add(new juego(1,"zelda","Legend of zelda: Breath of the Wild",5,"Videojuego de acción-aventura de la serie The Legend of Zelda, desarrollado por Nintendo EPD"));
//         juegos.add(new juego(2,"gta","Grand theft Auto V",2," Videojuego de acción-aventura de mundo abierto desarrollado por el estudio Rockstar North"));
//         juegos.add(new juego(3,"redred","Red Dead Redemption 2",4," Videojuego de acción-aventura western, en un mundo abierto y en perspectiva de primera y tercera persona"));
//         juegos.add(new juego(4,"hunters","Metroid Prime hunters",3,"Videojuego de acción en primera persona para Nintendo DS"));
//         juegos.add(new juego(5,"hnfinite","Halo Infinite",5,"Videojuego de disparos en primera persona"));
//         juegos.add(new juego(1,"destiny2","Destiny 2",5,"Videojuego de disparos en primera persona, desarrollado y publicado por Bungie"));
//         juegos.add(new juego(2,"league","League of Legends",2," Videojuego del género multijugador de arena de batalla en línea y deporte electrónico "));
//         juegos.add(new juego(3,"poke","Pokémon GO",4,"Videojuego de realidad aumentada basado en la localización desarrollado por Niantic"));
//         juegos.add(new juego(4,"runte","Legends of Runeterra",3,"Juego de cartas coleccionables digitales y gratuito desarrollado y publicado por Riot Games."));
//         juegos.add(new juego(5,"roc","Rocket League",5,"Videojuego que combina el fútbol con los vehículos"));
//