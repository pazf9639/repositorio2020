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
import mx.com.example.fragmento3.databinding.FragmentCateBinding;
import mx.com.example.fragmento3.gui.components.ColoresAdapter;
import mx.com.example.fragmento3.gui.components.NavigationIconClickListener;
import mx.com.example.fragmento3.model.Color;

public class Cate extends Fragment {
    private FragmentCateBinding binding;
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
        MainActivity.GLOBALS.put("CategoriasFragment",this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentCateBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        context = container.getContext();
    }

    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if(activity!=null){
            activity.setSupportActionBar(binding.appBar7);
        }
        binding.appBar7.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridCate), new AccelerateDecelerateInterpolator(), context.getDrawable(R.drawable.menu), context.getDrawable(R.drawable.menu_open)));
    }
    private void configUI() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridCate).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }
    private void configRecycler() {

        colores.add(new Color(1,"acton","Accion",5,"Juegos de accion"));
        colores.add(new Color(2,"arca","Arcade",4,"Juegos Arcade"));
        colores.add(new Color(3,"dep","Deportivo",3,"Juegos de Deporte"));
        colores.add(new Color(4,"stra","Estrategia",2,"Juegos de estrategia"));
        colores.add(new Color(5,"simul","Simulacion",5,"Juegos de simulacion"));

        binding.rclvCate.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false);
        binding.rclvCate.setLayoutManager(layoutManager);
        binding.rclvCate.setAdapter(new ColoresAdapter(colores));
    }


}
