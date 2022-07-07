package com.example.sitio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento_ver_publicaciones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento_ver_publicaciones extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragmento_ver_publicaciones() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmento_ver_publicaciones.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmento_ver_publicaciones newInstance(String param1, String param2) {
        Fragmento_ver_publicaciones fragment = new Fragmento_ver_publicaciones();
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
        focoactivo1 = true;
        focoactivo2 = false;
    }
    ScrollView scrollView1,scrollView2;
    HorizontalScrollView horizontalScrollView1;
    TextView like, mensaje;
    int scroll1X, scroll1Y;
    int scroll2X, scroll2Y;
    int scrollHX, scrollHY;
    boolean focoactivo1,focoactivo2;
    EditText texto_descripcion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragmento_ver_publicaciones, container, false);
        horizontalScrollView1 = view.findViewById(R.id.horizontalScrollView);
        scrollView1 = view.findViewById(R.id.scrollView2);
        scrollView2 = view.findViewById(R.id.scrollView22);
        like = view.findViewById(R.id.like);
        mensaje = view.findViewById(R.id.mensajes);
        like.setText("54");
        mensaje.setText("5");
        texto_descripcion = view.findViewById(R.id.texto_descripcion_ver_publicacion);
        texto_descripcion.setText("Comi el otro dia en lo de Romario, la comida es muy buena, totalmente recomendado ");
        saber_posicion_barras(scrollView1, 1);
        saber_posicion_barras(scrollView2, 2);
        saber_posicion_barras_horizontal(horizontalScrollView1);
        corregir_barra_horizontal_y_vertical(horizontalScrollView1, scrollView1, scrollView2);
        bloquaar_barras(scrollView1, 1);
        bloquaar_barras(scrollView2, 2);
        //////////////////////////
        return  view;
    }

    private void bloquaar_barras(ScrollView scrollView, int que_barra) {
        if(que_barra == 1) {
            scrollView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (scroll2Y <= 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            });
        } else {
            scrollView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (scroll1Y <= 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            });
        }
    }
    private void saber_posicion_barras(ScrollView scrollView, int que_barra) {
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if(que_barra == 1){
                    scroll1X = scrollView.getScrollX();
                    scroll1Y = scrollView.getScrollY();
                } else {
                    scroll2X = scrollView.getScrollX();
                    scroll2Y = scrollView.getScrollY();
                }
            }

        });
    }
    private void saber_posicion_barras_horizontal(HorizontalScrollView horizontalScrollView) {
        horizontalScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                scrollHX = horizontalScrollView.getScrollX();
                scrollHY = horizontalScrollView.getScrollY();
            }
        });
    }
    private void corregir_barra_horizontal_y_vertical(HorizontalScrollView horizontalScrollView, ScrollView scroll1,ScrollView scroll2) {
        horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("valor horizontal barra", String.valueOf(scrollHX));
               if (focoactivo1 & scrollHX >= 600) {
                    horizontalScrollView.setScrollX(1046);
                    focoactivo1 = false;
                    focoactivo2 = true;
                    scroll1.setScrollY(0);
                    texto_descripcion.setText("Muy rica comida a buen precio\nAmee ");
                   like.setText("23");
                   mensaje.setText("3");
                } else if(focoactivo2 & scrollHX <=450){
                   horizontalScrollView.setScrollX(0);
                   focoactivo1 = true;
                   focoactivo2 = false;
                   scroll2.setScrollY(0);
                   texto_descripcion.setText("Comi el otro dia en lo de Romario, la comida es muy buena, totalmente recomendado ");
                   like.setText("54");
                   mensaje.setText("5");
               }
                return false;
            }
        });
    }
}