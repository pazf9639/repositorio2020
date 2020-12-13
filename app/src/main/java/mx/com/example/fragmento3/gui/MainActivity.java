package mx.com.example.fragmento3.gui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashMap;

import mx.com.example.fragmento3.R;
import mx.com.example.fragmento3.core.FragmentosApplication;
import mx.com.example.fragmento3.gui.components.NavigationHost;


public class MainActivity extends AppCompatActivity implements NavigationHost {

    public static HashMap<String, Object> GLOBALS= new HashMap<>();
    private Button botonTop;
    private Button botonRan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configContext();
        configGlobals();
        configFragmentsManager(savedInstanceState);

    }

    private void configContext() {
        FragmentosApplication.setAppContext(getApplicationContext());
    }

    private void configGlobals() {
      GLOBALS.put("app",this);
    }
///cq


    private void configFragmentsManager(Bundle savedInstanceState) {
        if (savedInstanceState==null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentPanel,new LoginFragment())
                    .commit();}



    }
    //fin
    public void Ranked(View view) {
        getSupportFragmentManager()
       .beginTransaction()
                .add(R.id.contentPanel,new Rankeado())
                .commit();
    }

    public void TopJ(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new TopJuegos())
                .commit();
    }
    public void Free(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new Free())
                .commit();
    }

    public void Vieja(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new Vieja())
                .commit();
    }

    public void Cate(View view) {
       getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel, new Cate())
                .commit();
    }
    public void admin(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new Administra())
                .commit();
    }
    public void Mio(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel,new MisJueg())
                .commit();
    }

    @Override
    public void navigateTo(androidx.fragment.app.Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right,R.animator.slide_in_right,R.animator.slide_out_left)
                        .replace(R.id.contentPanel, fragment);

        if(addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }
}