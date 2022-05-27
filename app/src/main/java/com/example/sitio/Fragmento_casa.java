package com.example.sitio;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.GridLayout.LayoutParams;
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

        ///HISTORIA
            int cantidad_historias = 5;
            LayoutParams params1;
            LinearLayout LL_layoutHistorias = vista_inicio.findViewById(R.id.LayoutHistorias);
            HorizontalScrollView HSV_Historias = vista_inicio.findViewById(R.id.HSV_historias);
            if (cantidad_historias == 0) {
                HSV_Historias.setVisibility(vista_inicio.GONE);
            } else {
                for (int n = 0; n < cantidad_historias; n++) {
                    String descripcion = "Historia\n" + String.valueOf(n);
                    Button boton = new Button(vista_inicio.getContext());
                    boton.setText(descripcion);
                    boton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    boton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    boton.setTextColor(Color.parseColor("#000000"));
                    LinearLayout.LayoutParams lp_boton = new LinearLayout.LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT
                    );
                    boton.setBackgroundResource(R.drawable.btn_round);
                    lp_boton.setMargins(0, 18, 20, 0);
                    //boton.setLayoutParams(margin);
                    boton.setLayoutParams(lp_boton);
                    LL_layoutHistorias.addView(boton);
                }
            };
        ///
        /// PUBLICACIONES
            int cantidad_publicaciones = (int)Math.floor(Math.random()*(10-1+1)+1);
            LinearLayout LL_layoutPublicacion = vista_inicio.findViewById(R.id.layout_publicacion);
            for (int n = 0; n < cantidad_publicaciones; n++) {
                int cantidad_fotos = (int)Math.floor(Math.random()*(6-1+1)+1);
                ///LAYOUT x PUBLICACION
                LinearLayout LL_publicacion = new LinearLayout(LL_layoutPublicacion.getContext());
                LL_publicacion.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams lp_publicacion = new LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT
                );
                lp_publicacion.setMargins(0,20,0,0);
                LL_publicacion.setLayoutParams(lp_publicacion);
                    /// BARRA TITULO
                    LinearLayout LL_barraTitulo = new LinearLayout(LL_publicacion.getContext());
                    LL_barraTitulo.setOrientation(LinearLayout.HORIZONTAL);
                    LL_barraTitulo.setPadding(15,0,0,0);
                    LinearLayout.LayoutParams lp_barraTitulo = new LinearLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            150
                    );
                    LL_barraTitulo.setLayoutParams(lp_barraTitulo);
                    LL_barraTitulo.setBackgroundColor(Color.TRANSPARENT);
                        /// IMAGEN PERFIL
                        TextView imagen_perfil = new TextView(LL_publicacion.getContext());
                        imagen_perfil.setText("I\nMA\nGEN");
                        imagen_perfil.setGravity(Gravity.CENTER);
                        imagen_perfil.setBackgroundColor(Color.parseColor("#FFD692"));
                        imagen_perfil.setLayoutParams(new LinearLayout.LayoutParams(170,LayoutParams.MATCH_PARENT));
                        imagen_perfil.setTextSize(11);
                        imagen_perfil.setTextColor(Color.BLACK);
                        LL_barraTitulo.addView(imagen_perfil);
                        /// NOMBRE PERFIL
                        String string_titulo = "PERFIL " + String.valueOf(n);
                        TextView texto_titulo = new TextView(LL_publicacion.getContext());
                        texto_titulo.setBackgroundColor(Color.TRANSPARENT);
                        texto_titulo.setText(string_titulo);
                        texto_titulo.setTextSize(16);
                        texto_titulo.setTextColor(Color.BLACK);
                        texto_titulo.setTypeface(null, Typeface.BOLD);
                        texto_titulo.setGravity(Gravity.BOTTOM);
                        LinearLayout.LayoutParams lp_titulo = new LinearLayout.LayoutParams(
                                LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT, (float) 10.0
                        );
                        lp_titulo.setMargins(10,0,0,0);
                        texto_titulo.setLayoutParams(lp_titulo);
                        LL_barraTitulo.addView(texto_titulo);
                        /// MAS OPCIONES PERFIL
                        TextView masOpciones = new TextView(LL_publicacion.getContext());
                        masOpciones.setBackgroundColor(Color.TRANSPARENT);
                        masOpciones.setText(". . .");
                        masOpciones.setTextSize(30);
                        masOpciones.setTextColor(Color.BLACK);
                        masOpciones.setTypeface(null, Typeface.BOLD);
                        masOpciones.setGravity(Gravity.CENTER|Gravity.BOTTOM);
                        LinearLayout.LayoutParams lp_masopciones = new LinearLayout.LayoutParams(
                                LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT, (float) 3.0
                        );
                        masOpciones.setLayoutParams(lp_masopciones);
                        LL_barraTitulo.addView(masOpciones);
                    LL_publicacion.addView(LL_barraTitulo);
                    /// FIN BARRA TITULO
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
            };
        return vista_inicio;
    }
}