package com.example.sitio;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    private Fragmento_casa Fragmento_casa = new Fragmento_casa();
    private Fragmento_buscar Fragmento_buscar = new Fragmento_buscar();
    private Button boton_casa;
    private Button boton_buscar;
    private Boolean entro_casa = false;
    private Boolean entro_buscar = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        /// INICIALIZAR BARRA//
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();   // ESCONDER
        //
        boton_casa = findViewById(R.id.boton_casa);
        boton_buscar = findViewById(R.id.boton_buscar);
        cambiar_fragmentos(Fragmento_casa);
        boton_casa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(entro_casa) {
                    cambiar_fragmentos(Fragmento_casa);
                    entro_casa = false;
                    entro_buscar = true;

                }
            }
        });
        boton_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(entro_buscar) {
                    cambiar_fragmentos(Fragmento_buscar);
                    entro_casa = true;
                    entro_buscar = false;
                }
            }
        });
    }


    public void cambiar_fragmentos(Fragment fragmento) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transicion = fragmentManager.beginTransaction();
        transicion.replace(R.id.Ventana_principal, fragmento);
        transicion.commit();
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}