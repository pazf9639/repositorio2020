//package mx.com.example.fragmento3.gui.components;
package mx.com.example.fragmento3.gui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

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
import mx.com.example.fragmento3.databinding.FragmentRankeadoBinding;
import mx.com.example.fragmento3.databinding.FragmentViejaBinding;
import mx.com.example.fragmento3.gui.components.JuegosAdapter;
import mx.com.example.fragmento3.gui.components.NavigationIconClickListener;
import mx.com.example.fragmento3.model.Juego;

public class Rankeado extends Fragment {

    private @NonNull
    FragmentRankeadoBinding binding;
    private View view;
    private Context context;
    private List<Juego> juegos = new ArrayList<>();

    private static final String PATH_TOP = "Ranked";

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

//        binding.fabCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((NavigationHost)getActivity()).navigateTo(new AddFragment(), true);
//            }
//        });
        return view;
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("RankeadoFragment",this);
    }

    private void configView(LayoutInflater inflater,ViewGroup container) {
        binding = FragmentRankeadoBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        context = container.getContext();
    }

    private void configToolBar() {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if (activity!=null){
            activity.setSupportActionBar(binding.appBar3);
        }
        binding.appBar3.setNavigationOnClickListener(new NavigationIconClickListener(
                context, view.findViewById(R.id.gridRankeado),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu_open),
                context.getDrawable(R.drawable.menu)
        ));
    }

    private void configUI() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridRankeado).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
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
                binding.rclvRanked.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Juego juego = snapshot.getValue(Juego.class);
                if (juego != null){
                    Log.i("juego","onChildChanged: " + juego.getIdJuego());
                }

                juegos.set(juegos.indexOf(juego),juego);
                binding.rclvRanked.getAdapter().notifyDataSetChanged();
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

        binding.rclvRanked.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        binding.rclvRanked.setLayoutManager(layoutManager);
        binding.rclvRanked.setAdapter(new JuegosAdapter(juegos));
    }
}
