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
    private Fragmento_usuario Fragmento_usuario = new Fragmento_usuario();
    private Fragmento_sugerencias Fragmento_sugerencias = new Fragmento_sugerencias();
    private Fragmento_mapa Fragmento_mapa = new Fragmento_mapa();
    private Button boton_casa, boton_buscar ,boton_sugerencias, boton_usuario, boton_mapa;
    private Boolean entro_casa = false;
    private Boolean entro_buscar = true;
    private Boolean entro_usuario = true;
    private Boolean entro_sugerencias = true;
    private Boolean entro_mapa = true;
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
        boton_usuario = findViewById(R.id.boton_usuario);
        boton_sugerencias = findViewById(R.id.boton_sugerencias);
        boton_mapa = findViewById(R.id.boton_mapa);
        cambiar_fragmentos(Fragmento_casa);
        boton_casa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(entro_casa) {
                    cambiar_fragmentos(Fragmento_casa);
                    entro_casa = false;
                    entro_buscar = true;
                    entro_usuario = true;
                    entro_sugerencias = true;
                    entro_mapa = true;
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
                    entro_usuario = true;
                    entro_sugerencias = true;
                    entro_mapa = true;
                }
            }
        });
        boton_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(entro_usuario) {
                    cambiar_fragmentos(Fragmento_usuario);
                    entro_casa = true;
                    entro_buscar = true;
                    entro_usuario = false;
                    entro_sugerencias = true;
                    entro_mapa = true;
                }
            }
        });
        boton_sugerencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(entro_sugerencias) {
                    cambiar_fragmentos(Fragmento_sugerencias);
                    entro_casa = true;
                    entro_buscar = true;
                    entro_usuario = true;
                    entro_sugerencias = false;
                    entro_mapa = true;
                }
            }
        });
        boton_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(entro_mapa) {
                    cambiar_fragmentos(Fragmento_mapa);
                    entro_casa = true;
                    entro_buscar = true;
                    entro_usuario = true;
                    entro_sugerencias = true;
                    entro_mapa = false;
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