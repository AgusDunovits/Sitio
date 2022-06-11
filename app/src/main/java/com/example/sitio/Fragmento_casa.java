package com.example.sitio;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
            Long Long_temp = System.currentTimeMillis()/50;
            int int_anchoHistoria = Math.round(TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 80,r.getDisplayMetrics()));
            if(Long_temp-tsLong>1 ) {
                if(!runActivo) {
                    tsLong = Long_temp;
                    int temp_scrollX = HSV_pantalla.getScrollX();
                    int int_moverA = 0;

                    if(temp_scrollX >= int_anchoHistoria/2) {
                        int int_anchoPromedio = temp_scrollX + (int)(int_screenWidth / 2) - int_anchoHistoria;
                        int int_posicionScroll = int_anchoPromedio/int_screenWidth;
                        int_moverA = int_anchoHistoria + (int_posicionScroll * int_screenWidth);
                    }
                    HSV_pantalla.smoothScrollTo(int_moverA, 0);
                    runActivo = true;
                } else {
                    runActivo = false;
                }
            } else {
                handler.postDelayed(this, 10);
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        r = getResources();
        int_screenWidth = r.getDisplayMetrics().widthPixels;
        int int_margenHistoria = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8,r.getDisplayMetrics()));

        View vista_inicio = inflater.inflate(R.layout.fragment_fragmento_casa, container, false);

        HSV_pantalla = vista_inicio.findViewById(R.id.HSV_pantalla);
        HSV_pantalla.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                Long Long_temp = System.currentTimeMillis()/50;
                runActivo = false;
                if(!handler.hasCallbacks(runnable)) {
                    handler.post(runnable);
                }
                tsLong = Long_temp;
            }
        });

        ///Historias
        int int_historias = (int)Math.floor(Math.random()*(10)+1);
        String String_colorHistoria[] = {"#FFC6C6","#FFE6C6","#FFFEC6","#E4FFC6","#C6FFFE","#C6CEFF","#EFC6FF","#FFC6EE"};
        LinearLayout LL_historias = vista_inicio.findViewById(R.id.LL_historias);
        int int_anchoHistoria = LL_historias.getWidth();
        LinearLayout.LayoutParams LLLP_botonHistorias = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                160
        );
        LLLP_botonHistorias.setMargins(0,0,int_margenHistoria,15);

        if( int_historias==0 ){
            ScrollView SV_historias = vista_inicio.findViewById(R.id.SV_historias);
            SV_historias.setVisibility(vista_inicio.GONE);
        } else {
            for (int n = 0; n < int_historias; n++) {
                int int_colorHistoria = (int)Math.floor(Math.random()*(8)+0);
                String String_tituloBoton = "His..\n" + String.valueOf(n);
                Button Button_historiaN = new Button(LL_historias.getContext());
                Button_historiaN.setText(String_tituloBoton);
                Button_historiaN.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                Button_historiaN.setBackgroundColor(Color.parseColor(String_colorHistoria[int_colorHistoria]));
                Button_historiaN.setTextColor(Color.parseColor("#000000"));
                Button_historiaN.setLayoutParams(LLLP_botonHistorias);
                //Button_historiaN.setBackgroundResource(R.drawable.historia);
                LL_historias.addView(Button_historiaN);
            }
        }
        /// PUBLICACIONES
        String[] String_Lugares = {"Dean & Dennys","McDonald's","Burguer King","Mostaza","Deniro's"};
        String[] String_Personas = {"Darío","Camila","Facundo","Ignacio","Agus"};
        int int_publicaciones = (int)Math.floor(Math.random()*(5)+2);
        int int_alturaTitulo = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50,r.getDisplayMetrics()));
        int int_alturaFoto = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 380,r.getDisplayMetrics()));

        LinearLayout LL_separador = vista_inicio.findViewById(R.id.LL_separador);
        for (int x=0 ; x<int_publicaciones ; x++ ) {
            int int_fotos = (int) Math.floor(Math.random() * (5) + 1);
            int int_lugar = (int) Math.floor(Math.random() * (5) + 0);
            int int_persona = (int) Math.floor(Math.random() * (5) + 0);

            //Tabla para publicacion con scroll y barra inferior sin scroll
            LinearLayout LL_contenedorPub = new LinearLayout(LL_separador.getContext());
            LL_contenedorPub.setOrientation(LinearLayout.VERTICAL);
            LL_contenedorPub.setBackgroundColor(Color.WHITE);
            LL_contenedorPub.setLayoutParams(new LinearLayout.LayoutParams(int_screenWidth, LayoutParams.MATCH_PARENT));
            LL_separador.addView(LL_contenedorPub);

            //Constraint para posicionar publicacion y barra inferior
            ConstraintLayout CL_pubYbarra = new ConstraintLayout(LL_contenedorPub.getContext());
            CL_pubYbarra.setId(View.generateViewId());
            CL_pubYbarra.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            LL_contenedorPub.addView(CL_pubYbarra);

            // Descripcion publicacion
            String String_descripcion = "Descripción\n. . . .\n. . . .";
            TextView TV_descripcion = new TextView(CL_pubYbarra.getContext());
            TV_descripcion.setId(View.generateViewId());
            TV_descripcion.setBackgroundColor(Color.TRANSPARENT);
            TV_descripcion.setText(String_descripcion);
            TV_descripcion.setTextSize(14);
            TV_descripcion.setTypeface(null, Typeface.ITALIC);
            TV_descripcion.setTextColor(Color.BLACK);
            TV_descripcion.setGravity(Gravity.CENTER_HORIZONTAL);
            TV_descripcion.setPadding(5,5,0,0);
            LinearLayout.LayoutParams LLLP_tvDescripcion = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1
            );
            TV_descripcion.setLayoutParams(LLLP_tvDescripcion);
            CL_pubYbarra.addView(TV_descripcion);

            // Publicacion scrolleable
            ScrollView SV_publicacion = new ScrollView(LL_contenedorPub.getContext());
            SV_publicacion.setId(View.generateViewId());
            SV_publicacion.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0,1));
            SV_publicacion.setBackgroundColor(Color.TRANSPARENT);
            CL_pubYbarra.addView(SV_publicacion);

            // Tabla vertical para contenido scrolleable
            LinearLayout LL_division = new LinearLayout(SV_publicacion.getContext());
            LL_division.setOrientation(LinearLayout.VERTICAL);
            LL_division.setBackgroundColor(Color.TRANSPARENT);
            LL_division.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            SV_publicacion.addView(LL_division);

            // Mas opciones
            /*Button Button_masOpciones = new Button(CL_barraTitulo.getContext());
            Button_masOpciones.setId(View.generateViewId());
            Button_masOpciones.setBackgroundColor(Color.TRANSPARENT);
            Button_masOpciones.setText(". . .");
            Button_masOpciones.setTextSize(18);
            Button_masOpciones.setTextColor(Color.BLACK);
            Button_masOpciones.setTypeface(null, Typeface.BOLD);
            Button_masOpciones.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
            CL_barraTitulo.addView(Button_masOpciones);*/

            for (int n = 0; n < int_fotos; n++) {
                int int_foto = (int)Math.floor(Math.random()*(4)+0);

                /// Foto
                ImageView IV_foto = new ImageView(LL_division.getContext());
                if(int_foto==0)IV_foto.setImageResource(R.drawable.foto);
                if(int_foto==1)IV_foto.setImageResource(R.drawable.foto1);
                if(int_foto==2)IV_foto.setImageResource(R.drawable.foto2);
                if(int_foto==3)IV_foto.setImageResource(R.drawable.foto3);
                IV_foto.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, int_alturaFoto));
                LL_division.addView(IV_foto);
            }

            // Tabla horizontal para barra de titulo
            LinearLayout LL_titulo = new LinearLayout(CL_pubYbarra.getContext());
            LL_titulo.setId(View.generateViewId());
            LL_titulo.setOrientation(LinearLayout.HORIZONTAL);
            LL_titulo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, int_alturaTitulo));
            LL_titulo.setPadding(15, 10, 0, 0);
            LL_titulo.setBackgroundColor(Color.WHITE);
            CL_pubYbarra.addView(LL_titulo);

            // Imagen de perfil
            ImageView IV_perfil = new ImageView(LL_titulo.getContext());
            IV_perfil.setId(View.generateViewId());
            IV_perfil.setImageResource(R.drawable.imagenperfil);
            IV_perfil.setLayoutParams(new LinearLayout.LayoutParams(120, LayoutParams.MATCH_PARENT));
            LL_titulo.addView(IV_perfil);

            // Tabla vertical para nombre del perfil y lugar visitado
            LinearLayout LL_informacion = new LinearLayout(LL_titulo.getContext());
            LL_informacion.setId(View.generateViewId());
            LL_informacion.setOrientation(LinearLayout.VERTICAL);
            LL_informacion.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
            LL_informacion.setBackgroundColor(Color.TRANSPARENT);
            LL_titulo.addView(LL_informacion);

            // Nombre del perfil
            TextView TV_titulo = new TextView(LL_informacion.getContext());
            TV_titulo.setBackgroundColor(Color.TRANSPARENT);
            TV_titulo.setText(String_Personas[int_persona]);
            TV_titulo.setTextSize(16);
            TV_titulo.setTextColor(Color.BLACK);
            TV_titulo.setTypeface(null, Typeface.BOLD);
            TV_titulo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            LL_informacion.addView(TV_titulo);

            // Lugar visitado
            TextView TV_lugar = new TextView(LL_informacion.getContext());
            TV_lugar.setBackgroundColor(Color.TRANSPARENT);
            TV_lugar.setText(String_Lugares[int_lugar]);
            TV_lugar.setTextSize(15);
            TV_lugar.setTextColor(Color.BLACK);
            TV_lugar.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            LL_informacion.addView(TV_lugar);

            // Cantidad de fotos
            String String_cantFotos = "";
            for (int z=0 ; z < int_fotos ; z++ ) String_cantFotos+="* ";
            TextView TV_cantFotos = new TextView(LL_titulo.getContext());
            TV_cantFotos.setId(View.generateViewId());
            TV_cantFotos.setBackgroundColor(Color.TRANSPARENT);
            TV_cantFotos.setText(String_cantFotos);
            TV_cantFotos.setTextSize(18);
            TV_cantFotos.setGravity(Gravity.CENTER_HORIZONTAL);
            TV_cantFotos.setPadding(10,0,10,0);
            TV_cantFotos.setTextColor(Color.BLACK);
            TV_cantFotos.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, (float) 10.0));
            LL_titulo.addView(TV_cantFotos);

            // Tabla para botonera inferior
            LinearLayout LL_botonera = new LinearLayout(LL_titulo.getContext());
            LL_botonera.setId(View.generateViewId());
            LL_botonera.setOrientation(LinearLayout.HORIZONTAL);
            LL_botonera.setPadding(0,35,10,0);
            LL_botonera.setLayoutParams(new LinearLayout.LayoutParams(200,LayoutParams.MATCH_PARENT, (float) 5.0));
            LL_botonera.setBackgroundColor(Color.TRANSPARENT);
            LL_titulo.addView(LL_botonera);

            /// Boton mensaje privado
            Button Button_msjPrivado = new Button(LL_botonera.getContext());
            Button_msjPrivado.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, (float) 1.0));
            Button_msjPrivado.setBackgroundResource(R.drawable.button_msjprivado);
            LL_botonera.addView(Button_msjPrivado);

            /// Boton proximo a visitar
            Button Button_quieroIr = new Button(LL_botonera.getContext());
            Button_quieroIr.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, (float) 1.0));
            Button_quieroIr.setBackgroundResource(R.drawable.button_quieroir);
            LL_botonera.addView(Button_quieroIr);

            /// Boton ver mensajes/escribir
            Button Button_verMensajes = new Button(LL_botonera.getContext());
            Button_verMensajes.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, (float) 1.0));
            Button_verMensajes.setBackgroundResource(R.drawable.button_chat);
            LL_botonera.addView(Button_verMensajes);

            /// Boton dar me gusta
            Button Button_like = new Button(LL_botonera.getContext());
            Button_like.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, (float) 1.0));
            Button_like.setBackgroundResource(R.drawable.button_unlike);
            LL_botonera.addView(Button_like);

            ConstraintSet CS_pubYbarra = new ConstraintSet();
            CS_pubYbarra.clone(CL_pubYbarra);
            CS_pubYbarra.connect(TV_descripcion.getId(),ConstraintSet.TOP,CS_pubYbarra.PARENT_ID,ConstraintSet.TOP,10);
            CS_pubYbarra.connect(TV_descripcion.getId(),ConstraintSet.BOTTOM,SV_publicacion.getId(),ConstraintSet.TOP,10);
            CS_pubYbarra.connect(SV_publicacion.getId(),ConstraintSet.TOP,TV_descripcion.getId(),ConstraintSet.BOTTOM,0);
            CS_pubYbarra.connect(SV_publicacion.getId(),ConstraintSet.BOTTOM,LL_titulo.getId(),ConstraintSet.TOP,0);
            CS_pubYbarra.connect(LL_titulo.getId(),ConstraintSet.TOP,SV_publicacion.getId(),ConstraintSet.BOTTOM,0);
            CS_pubYbarra.connect(LL_titulo.getId(),ConstraintSet.BOTTOM,CS_pubYbarra.PARENT_ID,ConstraintSet.BOTTOM,0);
            CS_pubYbarra.applyTo(CL_pubYbarra);

        }
        return vista_inicio;
    }
}