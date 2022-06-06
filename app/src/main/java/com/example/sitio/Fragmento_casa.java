package com.example.sitio;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.util.TypedValue;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento_casa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento_casa extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int int_scrollX = 0;
    private HorizontalScrollView HSV_pantalla;
    Resources r;
    int int_screenWidth;
    Boolean runActivo = false;

    Long tsLong = 0L;

    public Fragmento_casa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmento_casa.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmento_casa newInstance(String param1, String param2) {
        Fragmento_casa fragment = new Fragmento_casa();
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

    final Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Long Long_temp = System.currentTimeMillis()/300;
            if(Long_temp-tsLong>1 ) {
                if(!runActivo) {
                    tsLong = Long_temp;
                    int temp_scrollX = HSV_pantalla.getScrollX();
                    int int_anchoPromedio = temp_scrollX + (int)(int_screenWidth / 2);
                    int int_posicionScroll = int_anchoPromedio/int_screenWidth;
                    int int_moverA = int_posicionScroll * int_screenWidth;
                    Log.d("DEBUG",String.valueOf(int_moverA));
                    HSV_pantalla.smoothScrollTo(int_moverA, 0);
                    runActivo = true;
                } else {
                    runActivo = false;
                }
            } else {
                handler.postDelayed(this, 50);
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        r = getResources();
        int_screenWidth = r.getDisplayMetrics().widthPixels;

        View vista_inicio = inflater.inflate(R.layout.fragment_fragmento_casa, container, false);
        HSV_pantalla = vista_inicio.findViewById(R.id.HSV_pantalla);
        ///HISTORIAS
        int int_historias = (int)Math.floor(Math.random()*(0)+0);
        LinearLayout LL_historias = vista_inicio.findViewById(R.id.LL_historias);
        LinearLayout.LayoutParams LLLP_botonHistorias = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                160
        );
        LLLP_botonHistorias.setMargins(0,0,0,15);

        if( int_historias==0 ){
            ScrollView SV_historias = vista_inicio.findViewById(R.id.SV_historias);
            SV_historias.setVisibility(vista_inicio.GONE);
        } else {
            for (int n = 0; n < int_historias; n++) {
                String String_tituloBoton = "His..\n" + String.valueOf(n);
                Button Button_historiaN = new Button(LL_historias.getContext());
                Button_historiaN.setText(String_tituloBoton);
                Button_historiaN.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                Button_historiaN.setBackgroundColor(Color.TRANSPARENT);
                Button_historiaN.setTextColor(Color.parseColor("#000000"));
                Button_historiaN.setLayoutParams(LLLP_botonHistorias);
                Button_historiaN.setBackgroundResource(R.drawable.historia);
                LL_historias.addView(Button_historiaN);
            }
        }
        /// PUBLICACIONES
        int int_publicaciones = (int)Math.floor(Math.random()*(5)+2);
        String[] String_Lugares = {"Dean & Dennys","McDonald's","Burguer King","Mostaza","Deniro's"};
        String[] String_Personas = {"Darío","Camila","Facundo","Ignacio","Agus"};
        int int_alturaTitulo = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 45,r.getDisplayMetrics()));
        int int_alturaFoto = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 400,r.getDisplayMetrics()));

        LinearLayout LL_separador = vista_inicio.findViewById(R.id.LL_separador);
        LinearLayout.LayoutParams LLLP_publicacion = new LinearLayout.LayoutParams(
                int_screenWidth, LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams LLLP_publicacionScroll = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams LLLP_titulo = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, int_alturaTitulo);
        LLLP_titulo.setMargins(0,0,0,10);
        LinearLayout.LayoutParams LLLP_tvTitulo = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT, (float)8.0);
        LLLP_tvTitulo.setMargins(10,0,0,0);
        LinearLayout.LayoutParams LLLP_foto = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, int_alturaFoto);
        LLLP_foto.setMargins(0, 0, 0, 5);

        for (int x=0 ; x<int_publicaciones ; x++ ) {
            int int_fotos = (int) Math.floor(Math.random() * (5) + 1);
            int int_lugar = (int) Math.floor(Math.random() * (5) + 0);
            int int_persona = (int) Math.floor(Math.random() * (5) + 0);
            LinearLayout LL_publicacion = new LinearLayout(LL_separador.getContext());
            LL_publicacion.setOrientation(LinearLayout.VERTICAL);
            LL_publicacion.setLayoutParams(LLLP_publicacion);
            LL_publicacion.setBackgroundColor(Color.TRANSPARENT);

            /// BARRA TITUTLO
            LinearLayout LL_titulo = new LinearLayout(LL_publicacion.getContext());
            LL_titulo.setOrientation(LinearLayout.HORIZONTAL);
            LL_titulo.setLayoutParams(LLLP_titulo);
            LL_titulo.setPadding(15, 10, 0, 0);
            LL_titulo.setBackgroundColor(Color.TRANSPARENT);
            /// IMAGEN PERFIL
            ImageView IV_perfil = new ImageView(LL_titulo.getContext());
            IV_perfil.setImageResource(R.drawable.imagenperfil);
            IV_perfil.setLayoutParams(new LinearLayout.LayoutParams(170, LayoutParams.MATCH_PARENT, (float) 1.0));
            LL_titulo.addView(IV_perfil);
            /// NOMBRE PERFIL
            TextView TV_titulo = new TextView(LL_titulo.getContext());
            TV_titulo.setBackgroundColor(Color.TRANSPARENT);
            TV_titulo.setText(String_Personas[int_persona]);
            TV_titulo.setTextSize(16);
            TV_titulo.setTextColor(Color.BLACK);
            TV_titulo.setTypeface(null, Typeface.BOLD);
            TV_titulo.setGravity(Gravity.BOTTOM);
            TV_titulo.setLayoutParams(LLLP_tvTitulo);
            LL_titulo.addView(TV_titulo);
            /// CANTIDAD FOTOS
            String String_CantidadFotos = "FOTOS\n(" + String.valueOf(int_fotos) + ")";
            TextView TV_cantidadFotos = new TextView(LL_titulo.getContext());
            TV_cantidadFotos.setBackgroundColor(Color.TRANSPARENT);
            TV_cantidadFotos.setText(String_CantidadFotos);
            TV_cantidadFotos.setTextSize(14);
            TV_cantidadFotos.setTextColor(Color.BLACK);
            TV_cantidadFotos.setTypeface(null, Typeface.BOLD);
            TV_cantidadFotos.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);
            TV_cantidadFotos.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, (float) 8.0));
            LL_titulo.addView(TV_cantidadFotos);
            /// MAS OPCIONES
            ImageView IV_masOpciones = new ImageView(LL_titulo.getContext());
            IV_masOpciones.setImageResource(R.drawable.masopciones);
            IV_masOpciones.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, (float) 1.0));
            LL_titulo.addView(IV_masOpciones);
            LL_publicacion.addView(LL_titulo);
            /// BARRA NOMBRE LUGAR
            TextView TV_lugar = new TextView(LL_titulo.getContext());
            TV_lugar.setBackgroundColor(Color.parseColor("#414141"));
            TV_lugar.setText(String_Lugares[int_lugar]);
            TV_lugar.setTextSize(18);
            TV_lugar.setTextColor(Color.WHITE);
            TV_lugar.setTypeface(null, Typeface.BOLD);
            TV_lugar.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);
            TV_lugar.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            LL_publicacion.addView(TV_lugar);
            /// PANTALLA SCROLL DE FOTOS
            ScrollView SV_publicacion = new ScrollView(LL_publicacion.getContext());
            SV_publicacion.setLayoutParams(LLLP_publicacionScroll);
            SV_publicacion.setBackgroundColor(Color.TRANSPARENT);
            LinearLayout LL_divisionFotos = new LinearLayout(SV_publicacion.getContext());
            LL_divisionFotos.setOrientation(LinearLayout.VERTICAL);
            LL_divisionFotos.setBackgroundColor(Color.TRANSPARENT);
            LL_divisionFotos.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            /// DESCRIPCIÓN
            int int_descripcion = (int) Math.floor(Math.random() * (4) + 0);
            String String_descripcion = "Descripción";
            for (int f = 0; f < int_descripcion; f++) {
                String_descripcion += "\n. . . .";
            }
            TextView TV_descripcion = new TextView(LL_titulo.getContext());
            TV_descripcion.setBackgroundColor(Color.TRANSPARENT);
            TV_descripcion.setText(String_descripcion);
            TV_descripcion.setTextSize(16);
            TV_descripcion.setPadding(15, 0, 0, 10);
            TV_descripcion.setTextColor(Color.BLACK);
            TV_descripcion.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            LL_divisionFotos.addView(TV_descripcion);
            for (int n = 0; n < int_fotos; n++) {
                LinearLayout LL_foto = new LinearLayout(LL_divisionFotos.getContext());
                LL_foto.setOrientation(LinearLayout.VERTICAL);
                LL_foto.setBackgroundColor(Color.TRANSPARENT);
                LL_foto.setLayoutParams(LLLP_foto);
                /// FOTO
                ImageView IV_foto = new ImageView(LL_titulo.getContext());
                IV_foto.setImageResource(R.drawable.foto);
                IV_foto.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, (float) 1.0));
                LL_foto.addView(IV_foto);

                LL_divisionFotos.addView(LL_foto);
            }
            SV_publicacion.addView(LL_divisionFotos);
            LL_publicacion.addView(SV_publicacion);
            LL_separador.addView(LL_publicacion);

            HSV_pantalla.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    Long Long_temp = System.currentTimeMillis()/300;
                    runActivo = false;
                    if(!handler.hasCallbacks(runnable)) {
                        handler.post(runnable);
                    }
                    tsLong = Long_temp;
                }
            });

        }
        return vista_inicio;
    }
}