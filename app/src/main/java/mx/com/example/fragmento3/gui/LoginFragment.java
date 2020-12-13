package mx.com.example.fragmento3.gui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import mx.com.example.fragmento3.R;
import mx.com.example.fragmento3.databinding.FragmentLoginBinding;
import mx.com.example.fragmento3.gui.MainActivity;
import mx.com.example.fragmento3.gui.TopJuegos;
import mx.com.example.fragmento3.gui.components.NavigationHost;

public class LoginFragment extends Fragment {
private FragmentLoginBinding binding;
private View view;
private Context context;
private TextInputEditText Contraseña;
private TextInputEditText usuario;

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
                Contraseña=view.findViewById(R.id.password_editText);
                usuario=view.findViewById(R.id.username_editText);
                if (usuario.getText().toString().equals("pancho")&&Contraseña.getText().toString().equals("123")
                        ||usuario.getText().toString().equals("1")&&Contraseña.getText().toString().equals("1")){
                    ((NavigationHost)MainActivity.GLOBALS.get("app")).navigateTo(new TopJuegos(),false);
                }else{
                    usuario.setText("");
                    Contraseña.setText("");
                    Toast.makeText(getActivity(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }

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