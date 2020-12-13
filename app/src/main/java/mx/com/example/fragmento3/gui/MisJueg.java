package mx.com.example.fragmento3.gui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mx.com.example.fragmento3.R;
import mx.com.example.fragmento3.databinding.FragmentCateBinding;
import mx.com.example.fragmento3.databinding.FragmentMisjuegBinding;
import mx.com.example.fragmento3.gui.components.ColoresAdapter;
import mx.com.example.fragmento3.gui.components.NavigationIconClickListener;
import mx.com.example.fragmento3.model.Color;

public class MisJueg extends Fragment {

    private @NonNull FragmentMisjuegBinding binding;
    private View view;
    private Context context;
    private List<Color> colores = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configGlobals();
        configView(inflater,container);
        configToolbar();
        configUI();
        configRecycler();
        return view;
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("MisJuegosFragment",this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMisjuegBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        context = container.getContext();
    }

    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if(activity!=null){
            activity.setSupportActionBar(binding.appBar002);
        }
        binding.appBar002.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridMio), new AccelerateDecelerateInterpolator(), context.getDrawable(R.drawable.menu), context.getDrawable(R.drawable.menu_open)));
    }
    private void configUI() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridMio).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }
    private void configRecycler() {

        colores.add(new Color(1,"mario6","Super mario 64",5,"Juego de matar una tortuga"));
        colores.add(new Color(2,"poke","Pokemon go",4,"Juego de realidad aumentada"));
        colores.add(new Color(3,"minecraft","Minecraft",3,"Juego de caja de arena"));
        colores.add(new Color(4,"ocarina","Legend of zelda ocarina of time",2,"Juego de una ocarina que hace viajar al pasado"));
        colores.add(new Color(5,"roc","roc league",5,"Juego de futbol con carritos"));

        binding.rclvMIo.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false);
        binding.rclvMIo.setLayoutManager(layoutManager);
        binding.rclvMIo.setAdapter(new ColoresAdapter(colores));
    }
}
