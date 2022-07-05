package com.example.sitio;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Principal extends AppCompatActivity {

    private Fragment[] Fragmentos = {
            new Fragmento_casa(),
            new Fragmento_sugerencias(),
            new Fragmento_mapa(),
            new Fragmento_publicacion(),
            new Fragmento_usuario()
    };

    private Boolean[] boton_active = {true,false,false,false,false};
    private ImageButton[] barra_boton = new ImageButton[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        /// INICIALIZAR BARRA//
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();   // ESCONDER
        //
        barra_boton[0] = findViewById(R.id.boton_casa);
        barra_boton[1] = findViewById(R.id.boton_buscar);
        barra_boton[2] = findViewById(R.id.boton_mapa);
        barra_boton[3] = findViewById(R.id.boton_sugerencias);
        barra_boton[4] = findViewById(R.id.boton_usuario);
        cambiar_fragmentos(Fragmentos[0]);

        barra_boton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStatus(0);
            }
        });
        barra_boton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStatus(1);
            }
        });
        barra_boton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStatus(2);
            }
        });
        barra_boton[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStatus(3);
            }
        });
        barra_boton[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStatus(4);
            }
        });
    }

    private void onClickStatus(int posicion){
        for(int n=0; n < boton_active.length ; n++) {
            if(n==posicion) {
                cambiar_fragmentos(Fragmentos[n]);
                boton_active[n] = true;
                barra_boton[n].setBackgroundColor(Color.parseColor("#FBA934"));
            } else {
                if(boton_active[n]) {
                    barra_boton[n].setBackgroundColor(Color.parseColor("#FFB95A"));
                }
                boton_active[n] = false;
            }
        }
    };

    public void cambiar_fragmentos(Fragment fragmento) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transicion = fragmentManager.beginTransaction();
        transicion.replace(R.id.Ventana_principal, fragmento);
        transicion.commit();
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}