package mx.com.example.fragmento3.gui.components;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import mx.com.example.fragmento3.databinding.FragmentLoginBinding;
import mx.com.example.fragmento3.gui.MainActivity;
import mx.com.example.fragmento3.gui.TopJuegos;

public class LoginFragment extends Fragment {
private FragmentLoginBinding binding;
private View view;
private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configGlobals();
        configView(inflater,container);
        configListeners();
       return view;
    }

    private void configListeners() {
        binding.nextButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost)MainActivity.GLOBALS.get("app")).navigateTo(new TopJuegos(),false);
            }
        });
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding=FragmentLoginBinding.inflate(inflater,container,false);
        view=binding.getRoot();
        context=container.getContext();
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("Loading fragment",this);
    }
}