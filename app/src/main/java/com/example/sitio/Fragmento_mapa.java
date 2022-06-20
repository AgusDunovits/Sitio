package com.example.sitio;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento_mapa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento_mapa extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Resources r;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragmento_mapa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmento_mapa.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmento_mapa newInstance(String param1, String param2) {
        Fragmento_mapa fragment = new Fragmento_mapa();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    int int_opcionPrincipal = 0;
    String String_cantidadOpciones[][] = {
            {"Comida\no\nBebida","Bar|Restaurante|Heladeria|Pizzeria"},
            {"Moda","Deportiva|Sport|Casual|Alta costura"},
            {"Indumen-\ntaria","Vestidos|Ropa interior|Calzado|Infantil"},
            {"Deportes","Futbol|Paddle|Tennis|Natacion"},
            {"Outdoor","Camping|Pesca"},
            {"Baile","Reggaeton|Ballet|Tango"}
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        r = getResources();
        View vista_mapa = inflater.inflate(R.layout.fragment_fragmento_mapa, container, false);
        ConstraintLayout CL_opciones = vista_mapa.findViewById(R.id.CL_opciones);
        LinearLayout LL_containerVertical = vista_mapa.findViewById(R.id.LL_containeVertical);
        LinearLayout LL_primerasOpciones = vista_mapa.findViewById(R.id.LL_primerasOpciones);
        Button Button_miUbicacion = vista_mapa.findViewById(R.id.Button_miUbicacion);
        Button Button_porZona = vista_mapa.findViewById(R.id.Button_porZona);
        int int_dimBoton = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 100,r.getDisplayMetrics()));

        int int_contador = 0;
        for (int x = 0; x<String_cantidadOpciones.length/3 ; x++) {
            LinearLayout LL_HprimerasOpciones = new LinearLayout(LL_primerasOpciones.getContext());
            LL_HprimerasOpciones.setOrientation(LinearLayout.HORIZONTAL);
            LL_HprimerasOpciones.setBackgroundColor(Color.TRANSPARENT);
            LinearLayout.LayoutParams LLLP_filas = new LinearLayout.LayoutParams(
                    GridLayout.LayoutParams.WRAP_CONTENT, int_dimBoton
            );
            LLLP_filas.setMargins(0,10,0,10);
            LL_HprimerasOpciones.setLayoutParams(LLLP_filas);
            LL_primerasOpciones.addView(LL_HprimerasOpciones);

            int int_n = String_cantidadOpciones.length-(x*3);
            if(int_n >= 3 ) int_n=3;

            LinearLayout.LayoutParams LLLP_botones = new LinearLayout.LayoutParams(
                    int_dimBoton, GridLayout.LayoutParams.MATCH_PARENT,1
            );
            LLLP_botones.setMargins(10,0,10,0);
            for(int n=0; n<int_n ; n++) {
                Button Button_opcion = new Button(LL_HprimerasOpciones.getContext());
                Button_opcion.setText(String_cantidadOpciones[n+(x*3)][0]);
                Button_opcion.setBackgroundColor(Color.parseColor("#FFB95A"));
                Button_opcion.setLayoutParams(LLLP_botones);
                Button_opcion.setTextColor(Color.WHITE);
                Button_opcion.setTypeface(null, Typeface.BOLD);
                LL_HprimerasOpciones.addView(Button_opcion);
                Button_opcion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LL_containerVertical.setVisibility(View.VISIBLE);
                        LL_primerasOpciones.setVisibility(View.GONE);
                    }
                });


                int_contador++;
            }
        }

        Button_miUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int_opcionPrincipal = 1;
                LL_containerVertical.setVisibility(View.GONE);
                LL_primerasOpciones.setVisibility(View.VISIBLE);
            }
        });
        Button_porZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int_opcionPrincipal = 2;
                LL_containerVertical.setVisibility(View.GONE);
                LL_primerasOpciones.setVisibility(View.VISIBLE);
            }
        });

        return vista_mapa;
    }

}