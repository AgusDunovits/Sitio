package com.example.sitio;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.util.TypedValue;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento_casa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento_casa extends Fragment {

    long tiempo_primerClick = 0L;
    int posicionX_primerClick = 0;

    public Fragmento_casa() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Resources r = getResources();
        int int_screenWidth = r.getDisplayMetrics().widthPixels;
        int int_anchoHistoria = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 80, r.getDisplayMetrics()));
        int int_margenHistoria = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics()));
        int int_margenIzquierdo = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics()));

        View vista_inicio = inflater.inflate(R.layout.fragment_fragmento_casa, container, false);
        HorizontalScrollView HSV_pantalla = vista_inicio.findViewById(R.id.HSV_pantalla);
        HSV_pantalla.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch( View v, MotionEvent event ) {
                if ( event.getAction() == MotionEvent.ACTION_MOVE ){
                    if( posicionX_primerClick == 0 ) {
                        tiempo_primerClick = System.currentTimeMillis()/100;
                        posicionX_primerClick = HSV_pantalla.getScrollX();
                        /**
                         * Log.d("onTouch","Tiempo primer click: "+String.valueOf(tiempo_primerClick));
                         * Log.d("onTouch","Posicion primer click: "+posicionX_primerClick);
                         */
                    }
                }
                if ( event.getAction() == MotionEvent.ACTION_UP ) {
                    long tiempo_soltarClick = System.currentTimeMillis()/100;
                    int posicionX_soltarClick = HSV_pantalla.getScrollX();
                    int minimoScroll = 0;
                    /**
                     * Log.d("onTouch","Tiempo soltar click: "+String.valueOf(tiempo_primerClick));
                     * Log.d("onTouch","Posicion soltar click: "+posicionX_soltarClick);
                     * Log.d("onTouch","Diferencia de tiempos: "+posicionX_soltarClick);
                     */
                    int posicionScroll = 0;
                    if( tiempo_soltarClick-tiempo_primerClick <= 1 ) {
                        /** Log.d("onTouch","El tipo de scroll fue deslice"); */
                        minimoScroll = 10;
                    } else {
                        /** Log.d("onTouch","El tipo de scroll fue arrastre"); */
                        minimoScroll = int_screenWidth/2;
                    }

                    int diferenciaScroll = posicionX_soltarClick - posicionX_primerClick;
                    /**
                     * Log.d("onTouch","Diferencia de scroll: "+diferenciaScroll);
                     * Log.d("onTouch","Minimo scrolleable: "+minimoScroll);
                     */
                    if( posicionX_soltarClick > int_anchoHistoria/2 ) {
                        posicionScroll = (posicionX_soltarClick - int_anchoHistoria) / int_screenWidth;
                        if (diferenciaScroll > minimoScroll) {
                            /** Log.d("onTouch","Scroll hacia la derecha"); */
                            posicionScroll += 1;
                        } else if (diferenciaScroll < 0) {
                            posicionScroll = (posicionX_primerClick - int_anchoHistoria) / int_screenWidth;
                            if( diferenciaScroll*-1 > minimoScroll ) {
                                /** Log.d("onTouch","Scroll hacia la izquierda"); */
                                posicionScroll -= 1;
                            }
                        }
                        posicionScroll = int_anchoHistoria + (posicionScroll * int_screenWidth);
                    } else if (diferenciaScroll > minimoScroll) {
                        /** Log.d("onTouch","Scroll hacia la derecha"); */
                        posicionScroll = int_anchoHistoria + (posicionScroll * int_screenWidth);
                    }

                    HSV_pantalla.smoothScrollTo(posicionScroll, 0);
                    posicionX_primerClick = 0;
                    return true;
                }
                return false;
            }
        });

        ///Historias
        int int_historias = (int)Math.floor(Math.random()*(10)+1);
        String[] String_colorHistoria = {"#FFC6C6","#FFE6C6","#FFFEC6","#E4FFC6","#C6FFFE","#C6CEFF","#EFC6FF","#FFC6EE"};
        LinearLayout LL_historias = vista_inicio.findViewById(R.id.LL_historias);
        LinearLayout.LayoutParams LLLP_botonHistorias = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                160
        );
        LLLP_botonHistorias.setMargins(int_margenIzquierdo,0,int_margenHistoria,15);

        if( int_historias==0 ){
            ScrollView SV_historias = vista_inicio.findViewById(R.id.SV_historias);
            SV_historias.setVisibility(vista_inicio.GONE);
        } else {
            for (int n = 0; n < int_historias; n++) {
                int int_colorHistoria = (int)Math.floor(Math.random()*(8)+0);
                String String_tituloBoton = "His..\n" + n;
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
                TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics()));
        int int_alturaFoto = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 380, r.getDisplayMetrics()));

        LinearLayout LL_separador = vista_inicio.findViewById(R.id.LL_separador);

        //Constraint para publicaciones
        ConstraintLayout CL_pubXpub = new ConstraintLayout(LL_separador.getContext());
        CL_pubXpub.setId(View.generateViewId());
        CL_pubXpub.setBackgroundColor(Color.TRANSPARENT);
        CL_pubXpub.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        LL_separador.addView(CL_pubXpub);

        int int_ultimoID = -1;

        for (int x=0 ; x<int_publicaciones ; x++ ) {
            int int_fotos = (int) Math.floor(Math.random() * (5) + 1);
            int int_lugar = (int) Math.floor(Math.random() * (5) + 0);
            int int_persona = (int) Math.floor(Math.random() * (5) + 0);

            //Tabla para barra superior, publicacion con scroll y barra inferior
            LinearLayout LL_contenedorPub = new LinearLayout(LL_separador.getContext());
            LL_contenedorPub.setId(View.generateViewId());
            LL_contenedorPub.setOrientation(LinearLayout.VERTICAL);
            LL_contenedorPub.setBackgroundColor(Color.TRANSPARENT);
            LL_contenedorPub.setLayoutParams(new LinearLayout.LayoutParams(int_screenWidth, LayoutParams.MATCH_PARENT));
            CL_pubXpub.addView(LL_contenedorPub);

            ConstraintSet CS_pubXpub = new ConstraintSet();
            CS_pubXpub.clone(CL_pubXpub);

            if(x==0) {
                CS_pubXpub.connect(LL_contenedorPub.getId(),ConstraintSet.LEFT,CS_pubXpub.PARENT_ID,ConstraintSet.LEFT,0);
            } else {
                CS_pubXpub.connect(LL_contenedorPub.getId(),ConstraintSet.LEFT,int_ultimoID,ConstraintSet.RIGHT,0);
            }
            CS_pubXpub.applyTo(CL_pubXpub);
            int_ultimoID = LL_contenedorPub.getId();

            //Constraint para posicionar publicacion y barra inferior
            ConstraintLayout CL_pubYbarra = new ConstraintLayout(LL_contenedorPub.getContext());
            CL_pubYbarra.setId(View.generateViewId());
            CL_pubYbarra.setBackgroundColor(Color.TRANSPARENT);
            CL_pubYbarra.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            LL_contenedorPub.addView(CL_pubYbarra);

            // Tabla horizontal para barra superior
            LinearLayout LL_barraSuperior = new LinearLayout(CL_pubYbarra.getContext());
            LL_barraSuperior.setId(View.generateViewId());
            LL_barraSuperior.setOrientation(LinearLayout.HORIZONTAL);
            LL_barraSuperior.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, int_alturaTitulo));
            LL_barraSuperior.setPadding(5, 5, 0, 0);
            LL_barraSuperior.setBackgroundColor(Color.WHITE);
            CL_pubYbarra.addView(LL_barraSuperior);

            // Imagen de perfil
            ImageView IV_perfil = new ImageView(LL_barraSuperior.getContext());
            IV_perfil.setId(View.generateViewId());
            IV_perfil.setImageResource(R.drawable.imagenperfil);
            IV_perfil.setLayoutParams(new LinearLayout.LayoutParams(120, LayoutParams.MATCH_PARENT));
            LL_barraSuperior.addView(IV_perfil);

            // Tabla vertical para nombre del perfil y lugar visitado
            LinearLayout LL_informacion = new LinearLayout(LL_barraSuperior.getContext());
            LL_informacion.setId(View.generateViewId());
            LL_informacion.setOrientation(LinearLayout.VERTICAL);
            LL_informacion.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
            LL_informacion.setBackgroundColor(Color.TRANSPARENT);
            LL_barraSuperior.addView(LL_informacion);

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
            for (int z=0 ; z < int_fotos ; z++ ) String_cantFotos+="○";
            TextView TV_cantFotos = new TextView(LL_barraSuperior.getContext());
            TV_cantFotos.setId(View.generateViewId());
            TV_cantFotos.setBackgroundColor(Color.TRANSPARENT);
            TV_cantFotos.setText(String_cantFotos);
            TV_cantFotos.setTextSize(18);
            TV_cantFotos.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL);
            TV_cantFotos.setPadding(10,0,10,0);
            TV_cantFotos.setTextColor(Color.BLACK);
            TV_cantFotos.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            LL_barraSuperior.addView(TV_cantFotos);

            // Publicacion scrolleable
            ScrollView SV_publicacion = new ScrollView(LL_contenedorPub.getContext());
            SV_publicacion.setId(View.generateViewId());
            SV_publicacion.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0,1));
            if(int_fotos==1) SV_publicacion.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            SV_publicacion.setBackgroundColor(Color.TRANSPARENT);
            CL_pubYbarra.addView(SV_publicacion);

            // Tabla vertical para contenido scrolleable
            LinearLayout LL_division = new LinearLayout(SV_publicacion.getContext());
            LL_division.setId(View.generateViewId());
            LL_division.setPadding(25,0,25,0);
            LL_division.setOrientation(LinearLayout.VERTICAL);
            LL_division.setBackgroundColor(Color.TRANSPARENT);
            SV_publicacion.addView(LL_division);

            //Constraint para fotos
            ConstraintLayout CL_fotos = new ConstraintLayout(LL_separador.getContext());
            CL_fotos.setId(View.generateViewId());
            CL_fotos.setBackgroundColor(Color.TRANSPARENT);
            CL_fotos.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            LL_division.addView(CL_fotos);
            int int_idFoto = -1;
            for (int n = 0; n < int_fotos; n++) {
                int int_foto = (int)Math.floor(Math.random()*(4)+0);
                /// Foto
                ImageView IV_foto = new ImageView(LL_division.getContext());
                IV_foto.setId(View.generateViewId());
                if(int_foto==0)IV_foto.setImageResource(R.drawable.foto);
                if(int_foto==1)IV_foto.setImageResource(R.drawable.foto1);
                if(int_foto==2)IV_foto.setImageResource(R.drawable.foto2);
                if(int_foto==3)IV_foto.setImageResource(R.drawable.foto3);
                IV_foto.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, int_alturaFoto));
                CL_fotos.addView(IV_foto);

                ConstraintSet CS_fotos = new ConstraintSet();
                CS_fotos.clone(CL_fotos);

                if(n==0) {
                    CS_fotos.connect(IV_foto.getId(),ConstraintSet.TOP,CS_fotos.PARENT_ID,ConstraintSet.TOP,0);
                } else {
                    CS_fotos.connect(IV_foto.getId(),ConstraintSet.TOP,int_idFoto,ConstraintSet.BOTTOM,20);
                }
                CS_fotos.applyTo(CL_fotos);
                int_idFoto = IV_foto.getId();
            }

            // Tabla horizontal para barra de titulo
            LinearLayout LL_titulo = new LinearLayout(CL_pubYbarra.getContext());
            LL_titulo.setId(View.generateViewId());
            LL_titulo.setOrientation(LinearLayout.HORIZONTAL);
            LL_titulo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, int_alturaTitulo));
            LL_titulo.setPadding(15, 5, 0, 0);
            LL_titulo.setBackgroundColor(Color.WHITE);
            CL_pubYbarra.addView(LL_titulo);


            // Descripcion publicacion
            String String_descripcion = "Descripción\n. . . .";
            TextView TV_descripcion = new TextView(CL_pubYbarra.getContext());
            TV_descripcion.setId(View.generateViewId());
            TV_descripcion.setBackgroundColor(Color.TRANSPARENT);
            TV_descripcion.setText(String_descripcion);
            TV_descripcion.setTextSize(14);
            TV_descripcion.setTypeface(null, Typeface.ITALIC|Typeface.BOLD);
            TV_descripcion.setTextColor(Color.BLACK);
            TV_descripcion.setPadding(0,5,10,0);
            LinearLayout.LayoutParams LLLP_tvDescripcion = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT,13
            );
            TV_descripcion.setLayoutParams(LLLP_tvDescripcion);
            LL_titulo.addView(TV_descripcion);

            // Tabla para botonera inferior
            LinearLayout LL_botonera = new LinearLayout(LL_titulo.getContext());
            LL_botonera.setId(View.generateViewId());
            LL_botonera.setOrientation(LinearLayout.HORIZONTAL);
            LL_botonera.setPadding(0,20,10,30);
            LL_botonera.setLayoutParams(new LinearLayout.LayoutParams(180,LayoutParams.MATCH_PARENT, (float) 5.0));
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
            CS_pubYbarra.connect(LL_barraSuperior.getId(),ConstraintSet.TOP,CS_pubYbarra.PARENT_ID,ConstraintSet.TOP,0);
            CS_pubYbarra.connect(SV_publicacion.getId(),ConstraintSet.TOP,LL_barraSuperior.getId(),ConstraintSet.BOTTOM,0);
            CS_pubYbarra.connect(SV_publicacion.getId(),ConstraintSet.BOTTOM,LL_titulo.getId(),ConstraintSet.TOP,0);
            CS_pubYbarra.connect(LL_titulo.getId(),ConstraintSet.BOTTOM,CS_pubYbarra.PARENT_ID,ConstraintSet.BOTTOM,0);
            CS_pubYbarra.applyTo(CL_pubYbarra);
        }
        return vista_inicio;
    }
}