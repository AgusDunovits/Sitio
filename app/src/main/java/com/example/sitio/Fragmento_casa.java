package com.example.sitio;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

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
    public void test(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista_inicio = inflater.inflate(R.layout.fragment_fragmento_casa, container, false);

        ///HISTORIAS
        int int_historias = (int)Math.floor(Math.random()*(10)+0);
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
        int int_publicaciones = (int)Math.floor(Math.random()*(5)+1);
        String[] String_Lugares = {"Dean & Dennys","McDonald's","Burguer King","Mostaza","Deniro's"};
        String[] String_Personas = {"Darío","Camila","Facundo","Ignacio","Agus"};
        int int_screenWidth = (90*(Resources.getSystem().getDisplayMetrics().widthPixels))/100;

        LinearLayout LL_separador = vista_inicio.findViewById(R.id.LL_separador);
        LinearLayout.LayoutParams LLLP_publicacion = new LinearLayout.LayoutParams(
                int_screenWidth, LayoutParams.MATCH_PARENT);
        LLLP_publicacion.setMargins(2,2,20,0);
        LinearLayout.LayoutParams LLLP_publicacionScroll = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        LLLP_publicacionScroll.setMargins(0,0,0,0);
        LinearLayout.LayoutParams LLLP_titulo = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, 120);
        LLLP_titulo.setMargins(0,0,0,10);
        LinearLayout.LayoutParams LLLP_tvTitulo = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT, (float)8.0);
        LLLP_tvTitulo.setMargins(10,0,0,0);
        LinearLayout.LayoutParams LLLP_foto = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LLLP_foto.setMargins(0, 20, 0, 20);

        for (int x=0 ; x<int_publicaciones ; x++ ) {
            int int_fotos = (int)Math.floor(Math.random()*(5)+1);
            int int_lugar = (int)Math.floor(Math.random()*(5)+0);
            int int_persona = (int)Math.floor(Math.random()*(5)+0);
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
            IV_perfil.setLayoutParams(new LinearLayout.LayoutParams(170,LayoutParams.MATCH_PARENT, (float)1.0));
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
            TV_cantidadFotos.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER);
            TV_cantidadFotos.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT,(float)8.0));
            LL_titulo.addView(TV_cantidadFotos);
                /// MAS OPCIONES
            ImageView IV_masOpciones = new ImageView(LL_titulo.getContext());
            IV_masOpciones.setImageResource(R.drawable.masopciones);
            IV_masOpciones.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT, (float)1.0));
            LL_titulo.addView(IV_masOpciones);
            LL_publicacion.addView(LL_titulo);
            /// BARRA NOMBRE LUGAR
            TextView TV_lugar = new TextView(LL_titulo.getContext());
            TV_lugar.setBackgroundColor(Color.parseColor("#414141"));
            TV_lugar.setText(String_Lugares[int_lugar]);
            TV_lugar.setTextSize(18);
            TV_lugar.setTextColor(Color.WHITE);
            TV_lugar.setTypeface(null, Typeface.BOLD);
            TV_lugar.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER);
            TV_lugar.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            LL_publicacion.addView(TV_lugar);
            /// PANTALLA SCROLL DE FOTOS
            ScrollView SV_publicacion = new ScrollView(LL_publicacion.getContext());
            SV_publicacion.setLayoutParams(LLLP_publicacionScroll);
            SV_publicacion.setBackgroundColor(Color.TRANSPARENT);
            LinearLayout LL_divisionFotos = new LinearLayout(SV_publicacion.getContext());
            LL_divisionFotos.setOrientation(LinearLayout.VERTICAL);
            LL_divisionFotos.setBackgroundColor(Color.TRANSPARENT);
            LL_divisionFotos.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            for (int n = 0; n < int_fotos; n++) {
                int int_descripcion = (int)Math.floor(Math.random()*(4)+0);
                String String_descripcion = "Descripción";
                for (int f=0 ; f<int_descripcion ; f++){String_descripcion+="\n. . . .";}
                LinearLayout LL_foto = new LinearLayout(LL_divisionFotos.getContext());
                LL_foto.setOrientation(LinearLayout.VERTICAL);
                LL_foto.setBackgroundColor(Color.TRANSPARENT);
                LL_foto.setLayoutParams(LLLP_foto);
                /// DESCRIPCIÓN
                TextView TV_descripcion = new TextView(LL_titulo.getContext());
                TV_descripcion.setBackgroundColor(Color.TRANSPARENT);
                TV_descripcion.setText(String_descripcion);
                TV_descripcion.setTextSize(16);
                TV_descripcion.setPadding(15,0,0,10);
                TV_descripcion.setTextColor(Color.BLACK);
                TV_descripcion.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
                LL_foto.addView(TV_descripcion);
                /// FOTO
                ImageView IV_foto = new ImageView(LL_titulo.getContext());
                IV_foto.setImageResource(R.drawable.foto);
                IV_foto.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT, (float)1.0));
                LL_foto.addView(IV_foto);

                LL_divisionFotos.addView(LL_foto);
            }
            SV_publicacion.addView(LL_divisionFotos);
            LL_publicacion.addView(SV_publicacion);

            LL_separador.addView(LL_publicacion);
        }

        /*
                    /// IMAGEN PUBLICADA
                    TextView textView_imagen = new TextView(LL_publicacion.getContext());
                    textView_imagen.setText("IMAGEN");
                    textView_imagen.setGravity(Gravity.CENTER);
                    textView_imagen.setTextSize(60);
                    textView_imagen.setTextColor(Color.BLACK);
                    textView_imagen.setBackgroundColor(Color.parseColor("#F4F4F4"));
                    LinearLayout.LayoutParams lp_imagen = new LinearLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            800
                    );
                    textView_imagen.setLayoutParams(lp_imagen);
                    LL_publicacion.addView(textView_imagen);
                    /// BARRA PIE PUBLICACION
                    LinearLayout LL_piePublicacion = new LinearLayout(LL_publicacion.getContext());
                    LL_piePublicacion.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams lp_piePublicacion = new LinearLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            100
                    );
                    LL_piePublicacion.setLayoutParams(lp_piePublicacion);
                    LL_piePublicacion.setBackgroundColor(Color.TRANSPARENT);
                        /// ORDENADOR FAKE 1
                        TextView TV_fake1 = new TextView(LL_publicacion.getContext());
                        TV_fake1.setBackgroundColor(Color.TRANSPARENT);
                        TV_fake1.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT,(float) 2.0));
                        LL_piePublicacion.addView(TV_fake1);
                        /// BOTON MENSAJE PRIVADO
                        TextView TV_mensajePrivado = new TextView(LL_publicacion.getContext());
                        TV_mensajePrivado.setText("MSJ\nPRIV");
                        TV_mensajePrivado.setGravity(Gravity.CENTER);
                        TV_mensajePrivado.setTextColor(Color.BLACK);
                        TV_mensajePrivado.setBackgroundColor(Color.parseColor("#C1F4F6"));
                        TV_mensajePrivado.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT,(float) 2.0));
                        LL_piePublicacion.addView(TV_mensajePrivado);
                        /// TEXTO CANTIDAD FOTOS
                        TextView TV_cantidadFotos = new TextView(LL_publicacion.getContext());
                        String cantidadFotos = "";
                        for (int x = 0; x < cantidad_fotos ; x++){cantidadFotos+="o ";};
                        TV_cantidadFotos.setText(cantidadFotos);
                        TV_cantidadFotos.setGravity(Gravity.CENTER);
                        TV_cantidadFotos.setTextColor(Color.BLACK);
                        TV_cantidadFotos.setTextSize(20);
                        TV_cantidadFotos.setBackgroundColor(Color.TRANSPARENT);
                        TV_cantidadFotos.setLayoutParams(new LinearLayout.LayoutParams(50,LayoutParams.MATCH_PARENT,(float) 12.0));
                        LL_piePublicacion.addView(TV_cantidadFotos);
                        /// BOTON VER MSJ
                        TextView TV_verMensajes = new TextView(LL_publicacion.getContext());
                        TV_verMensajes.setText("VER\nMSJ");
                        TV_verMensajes.setGravity(Gravity.CENTER);
                        TV_verMensajes.setTextColor(Color.BLACK);
                        TV_verMensajes.setBackgroundColor(Color.parseColor("#C1F4F6"));
                        TV_verMensajes.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT,(float) 2.0));
                        LL_piePublicacion.addView(TV_verMensajes);
                        /// BOTON DAR LIKE
                        TextView TV_darLike = new TextView(LL_publicacion.getContext());
                        TV_darLike.setText("LIKE");
                        TV_darLike.setGravity(Gravity.CENTER);
                        TV_darLike.setTextColor(Color.BLACK);
                        TV_darLike.setBackgroundColor(Color.parseColor("#C1F4F6"));
                        LinearLayout.LayoutParams lp_darLike = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT,(float) 2.0);
                        lp_darLike.setMargins(15,0,0,0);
                        TV_darLike.setLayoutParams(lp_darLike);
                        LL_piePublicacion.addView(TV_darLike);
                        /// ORDENADOR FAKE 2
                        TextView TV_fake2 = new TextView(LL_publicacion.getContext());
                        TV_fake2.setBackgroundColor(Color.TRANSPARENT);
                        TV_fake2.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT,(float) 4.0));
                        LL_piePublicacion.addView(TV_fake2);
                    LL_publicacion.addView(LL_piePublicacion);
                LL_layoutPublicacion.addView(LL_publicacion);
            };*/
        return vista_inicio;
    }
}