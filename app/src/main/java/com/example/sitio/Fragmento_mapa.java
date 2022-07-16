package com.example.sitio;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

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
    private int int_primeraOpcion = -1;
    private int int_segundaOpcion = -1;
    private int int_contador = 0;

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
    String[] Localidades = {
            "Villa Adelina",
            "Villa Ballester",
            "Olivos",
            "Martinez",
            "Munro",
            "Victoria",
            "San Fernando",
            "Boulogne",
            "Tigre"
    };
    String[] String_cantidadOpciones = {
            "Comida\no\nBebida",
            "Moda",
            "Indumen-\ntaria",
            "Deportes",
            "Outdoor",
            "Baile"
    };
    String[] segundasOpciones = {
            "Bar",
            "Restau- rante",
            "Heladería",
            "Pizzería",
            "Pastas",
            "Vegetariano ",
            "Vegano",
            "Deportiva",
            "Sport",
            "Casual",
            "Alta costura",
            "Vestidos",
            "Ropa interior",
            "Calzado",
            "Infantil",
            "Futbol",
            "Paddle",
            "Tennis",
            "Natación",
            "Camping",
            "Pesca",
            "Reggaeton",
            "Ballet",
            "Tango"
    };
    int[] int_botonActivo = new int[segundasOpciones.length];
    int[] cantidadSegundas = {
            7,4,4,4,2,3
    };
    int[] posicionSegundas = {
            0,7,11,15,19,21
    };
    int[] idTablas = new int[String_cantidadOpciones.length*3];
    int[] idBotones = new int[segundasOpciones.length];
    int id_checkBox, id_botonFiltrar;
    int cantidadActivos = 0;
    Boolean Bool_mapa = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        r = getResources();
        View vista_mapa = inflater.inflate(R.layout.fragment_fragmento_mapa, container, false);
        ConstraintLayout CL_opciones = vista_mapa.findViewById(R.id.CL_opciones);
        LinearLayout LL_containerVertical = vista_mapa.findViewById(R.id.LL_containeVertical);
        LinearLayout LL_primerasOpciones = vista_mapa.findViewById(R.id.LL_primerasOpciones);
        LinearLayout LL_segundasOpciones = vista_mapa.findViewById(R.id.LL_segundasOpciones);
        Button Button_miUbicacion = vista_mapa.findViewById(R.id.Button_miUbicacion);
        Button Button_porZona = vista_mapa.findViewById(R.id.Button_porZona);
        ImageButton IB_retroceder = vista_mapa.findViewById(R.id.botonretroceder);
        ImageView IV_Mapa = vista_mapa.findViewById(R.id.mapa);
        int int_dimBoton = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 100,r.getDisplayMetrics()));
        int int_dimBoton2da = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 70,r.getDisplayMetrics()));
        int int_dimSeekBar = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 25,r.getDisplayMetrics()));

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
                    GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.MATCH_PARENT,1
            );
            LLLP_botones.setMargins(10,0,10,0);
            for(int n=0; n<int_n ; n++) {
                Button Button_opcion = new Button(LL_HprimerasOpciones.getContext());
                Button_opcion.setText(String_cantidadOpciones[n+(x*3)]);
                Button_opcion.setBackgroundColor(Color.parseColor("#FFB95A"));
                Button_opcion.setLayoutParams(LLLP_botones);
                Button_opcion.setTextColor(Color.BLACK);
                Button_opcion.setId(10000+(x*3)+n);
                LL_HprimerasOpciones.addView(Button_opcion);
                Button_opcion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LL_segundasOpciones.setVisibility(View.VISIBLE);
                        LL_primerasOpciones.setVisibility(View.GONE);
                        int_primeraOpcion = Button_opcion.getId()-10000;
                        for(int nn=0; nn<3; nn++){
                            int int_temp = (Button_opcion.getId()-10000)*3+nn;
                            if(idTablas[int_temp]!=0 ){
                                LinearLayout temp_Tabla = vista_mapa.findViewById(idTablas[int_temp]);
                                temp_Tabla.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
                for (int z=0 ; z<3 ; z++ ) {
                    int newint = x*3+n;
                    LinearLayout LL_HsegundasOpciones = new LinearLayout(LL_segundasOpciones.getContext());
                    int zTemp = cantidadSegundas[newint]-(z*4);
                    if ( zTemp>4 ) {
                        zTemp = 4;
                    } else if (zTemp<=0)break;
                    int filaPosicion = (newint*3)+z;
                    LL_HsegundasOpciones.setVisibility(View.GONE);
                    LL_HsegundasOpciones.setOrientation(LinearLayout.HORIZONTAL);
                    LL_HsegundasOpciones.setBackgroundColor(Color.TRANSPARENT);
                    LinearLayout.LayoutParams LLLP_filas2 = new LinearLayout.LayoutParams(
                            GridLayout.LayoutParams.WRAP_CONTENT, int_dimBoton2da
                    );
                    LLLP_filas2.setMargins(0,10,0,10);
                    LL_HsegundasOpciones.setLayoutParams(LLLP_filas2);
                    LL_HsegundasOpciones.setId(View.generateViewId());
                    idTablas[filaPosicion] = LL_HsegundasOpciones.getId();
                    LL_segundasOpciones.addView(LL_HsegundasOpciones);

                    for(int nn=0 ; nn<zTemp ; nn++){
                        int int_posicion = posicionSegundas[newint]+( z*4 )+nn;
                        Button Button_opcion2 = new Button(LL_HsegundasOpciones.getContext());
                        Button_opcion2.setText(segundasOpciones[int_posicion]);
                        Button_opcion2.setBackgroundColor(Color.parseColor("#FFB95A"));
                        Button_opcion2.setLayoutParams(LLLP_botones);
                        Button_opcion2.setTextColor(Color.BLACK);
                        Button_opcion2.setId(View.generateViewId());
                        idBotones[int_posicion] = Button_opcion2.getId();
                        LL_HsegundasOpciones.addView(Button_opcion2);
                        Button_opcion2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CheckBox temp_CheckBox = vista_mapa.findViewById(id_checkBox);
                                int_segundaOpcion = 1;
                                if(int_botonActivo[int_posicion]==0){
                                    /** Esta inactivo */
                                    cantidadActivos += 1;
                                    int_botonActivo[int_posicion] = 1;
                                    Button_opcion2.setBackgroundColor(Color.parseColor("#F3F3F3"));
                                    Button_opcion2.setTextColor(Color.parseColor("#FF9A11"));
                                } else {
                                    /** Esta activo */
                                    cantidadActivos -= 1;
                                    int_botonActivo[int_posicion] = 0;
                                    Button_opcion2.setBackgroundColor(Color.parseColor("#FFB95A"));
                                    Button_opcion2.setTextColor(Color.BLACK);
                                }
                                if(cantidadActivos==cantidadSegundas[int_primeraOpcion]){
                                    temp_CheckBox.setChecked(true);
                                } else {
                                    temp_CheckBox.setChecked(false);
                                }
                                Button Button_temp = vista_mapa.findViewById(id_botonFiltrar);
                                if(cantidadActivos>0) {
                                    Button_temp.setBackgroundColor(Color.parseColor("#F3F3F3"));
                                    Button_temp.setTextColor(Color.parseColor("#FF9A11"));
                                } else {
                                    Button_temp.setBackgroundColor(Color.parseColor("#EBEBEB"));
                                    Button_temp.setTextColor(Color.parseColor("#999999"));
                                }
                            }
                        });
                    }
                }
                int_contador++;
            }
        }

        CheckBox CB_todosFiltros = new CheckBox(LL_segundasOpciones.getContext());
        CB_todosFiltros.setText("Todas las opciones");
        CB_todosFiltros.setTypeface(null, Typeface.BOLD);
        CB_todosFiltros.setId(View.generateViewId());
        LinearLayout.LayoutParams LLLP_checkBox = new LinearLayout.LayoutParams(
                GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT
        );
        LLLP_checkBox.setMargins(0,40,00,0);
        CB_todosFiltros.setLayoutParams(LLLP_checkBox);
        CB_todosFiltros.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#444444")));
        id_checkBox = CB_todosFiltros.getId();
        LL_segundasOpciones.addView(CB_todosFiltros);

        CB_todosFiltros.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int posicionBotones = posicionSegundas[int_primeraOpcion];
                for(int n=0 ; n<cantidadSegundas[int_primeraOpcion] ; n++ ){
                    Button Button_temp = vista_mapa.findViewById(idBotones[posicionBotones+n]);
                    if(isChecked){
                        cantidadActivos = cantidadSegundas[int_primeraOpcion];
                        int_botonActivo[posicionBotones+n] = 1;
                        Button_temp.setBackgroundColor(Color.parseColor("#F3F3F3"));
                        Button_temp.setTextColor(Color.parseColor("#FF9A11"));
                    } else if(cantidadActivos==cantidadSegundas[int_primeraOpcion]) {
                        int_botonActivo[posicionBotones+n] = 0;
                        Button_temp.setBackgroundColor(Color.parseColor("#FFB95A"));
                        Button_temp.setTextColor(Color.BLACK);
                        if(n==cantidadSegundas[int_primeraOpcion]-1)cantidadActivos = 0;
                    }
                }
                Button Button_temp = vista_mapa.findViewById(id_botonFiltrar);
                if(cantidadActivos>0) {
                    Button_temp.setBackgroundColor(Color.parseColor("#F3F3F3"));
                    Button_temp.setTextColor(Color.parseColor("#FF9A11"));
                    int_segundaOpcion = 0;
                } else {
                    Button_temp.setBackgroundColor(Color.parseColor("#EBEBEB"));
                    Button_temp.setTextColor(Color.parseColor("#999999"));
                }
            }
        });

        LinearLayout.LayoutParams LLLP_SeekBar = new LinearLayout.LayoutParams(
                GridLayout.LayoutParams.MATCH_PARENT, int_dimSeekBar
        );
        LLLP_SeekBar.setMargins(20,40,20,0);
        LinearLayout LL_barraKm = new LinearLayout(LL_segundasOpciones.getContext());
        LL_barraKm.setOrientation(LinearLayout.HORIZONTAL);
        LL_barraKm.setLayoutParams(LLLP_SeekBar);
        LL_segundasOpciones.addView(LL_barraKm);

        SeekBar SB_segundasOpciones = new SeekBar(LL_barraKm.getContext());
        SB_segundasOpciones.setLayoutParams(new LinearLayout.LayoutParams(
                GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.MATCH_PARENT,5));
        SB_segundasOpciones.setMax(7);
        SB_segundasOpciones.setProgress(0);
        SB_segundasOpciones.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
        SB_segundasOpciones.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#FFB95A")));
        LL_barraKm.addView(SB_segundasOpciones);

        TextView TV_seekBar = new TextView(LL_barraKm.getContext());
        TV_seekBar.setBackgroundColor(Color.TRANSPARENT);
        TV_seekBar.setTextColor(Color.BLACK);
        TV_seekBar.setTextSize(14);
        TV_seekBar.setText("Distancia: 1km");
        TV_seekBar.setLayoutParams(new LinearLayout.LayoutParams(
                GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.MATCH_PARENT,1));
        LL_barraKm.addView(TV_seekBar);

        LinearLayout.LayoutParams LLLP_buscarZona = new LinearLayout.LayoutParams(
                GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT
        );
        LLLP_buscarZona.setMargins(20,40,20,0);
        AutoCompleteTextView ACTV_buscarZona = new AutoCompleteTextView(LL_segundasOpciones.getContext());
        ACTV_buscarZona.setHint("Elegir localidad");
        ACTV_buscarZona.setCompletionHint(" Elegir localidad");
        ACTV_buscarZona.setLayoutParams(LLLP_buscarZona);
        ACTV_buscarZona.setThreshold(1);
        ACTV_buscarZona.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(LL_segundasOpciones.getContext(), android.R.layout.simple_list_item_1,Localidades);
        ACTV_buscarZona.setAdapter(adaptador);
        LL_segundasOpciones.addView(ACTV_buscarZona);

        LinearLayout.LayoutParams LLLP_filtrar = new LinearLayout.LayoutParams(
                GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT
        );
        LLLP_filtrar.setMargins(0,50,0,0);
        Button Button_filtrar = new Button(LL_segundasOpciones.getContext());
        Button_filtrar.setBackgroundColor(Color.parseColor("#EBEBEB"));
        Button_filtrar.setTextColor(Color.parseColor("#999999"));
        Button_filtrar.setTypeface(null, Typeface.BOLD);
        Button_filtrar.setText("Buscar");
        Button_filtrar.setLayoutParams(LLLP_filtrar);
        Button_filtrar.setGravity(Gravity.CENTER);
        Button_filtrar.setId(View.generateViewId());
        id_botonFiltrar = Button_filtrar.getId();
        LL_segundasOpciones.addView(Button_filtrar);


        ACTV_buscarZona.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        ACTV_buscarZona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                View v = getActivity().getCurrentFocus();
                if (v != null) {
                    inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        SB_segundasOpciones.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int int_progreso = SB_segundasOpciones.getProgress()+1;
                TV_seekBar.setText("Distancia: "+int_progreso+"km");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        Button_filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cantidadActivos>0){
                    Boolean continuar = true;
                    String miTexto = ACTV_buscarZona.getText().toString();
                    if(!Arrays.stream(Localidades).anyMatch(miTexto::contains) && int_opcionPrincipal==2){
                        continuar = false;
                        ACTV_buscarZona.setText("");
                        ACTV_buscarZona.setHintTextColor(Color.RED);
                        ACTV_buscarZona.setHint("No existe esa localidad");
                    } else {
                        Bool_mapa = true;
                        IV_Mapa.setVisibility(View.VISIBLE);
                        LL_segundasOpciones.setVisibility(View.GONE);
                    }
                }
            }
        });
        Button_miUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int_opcionPrincipal = 1;
                LL_containerVertical.setVisibility(View.GONE);
                LL_primerasOpciones.setVisibility(View.VISIBLE);
                LL_barraKm.setVisibility(View.VISIBLE);
                ACTV_buscarZona.setVisibility(View.GONE);
            }
        });
        Button_porZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int_opcionPrincipal = 2;
                LL_containerVertical.setVisibility(View.GONE);
                LL_primerasOpciones.setVisibility(View.VISIBLE);
                LL_barraKm.setVisibility(View.GONE);
                ACTV_buscarZona.setVisibility(View.VISIBLE);
            }
        });
        IB_retroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(int_segundaOpcion!=-1 && Bool_mapa == true){
                    Bool_mapa = false;
                    IV_Mapa.setVisibility(View.GONE);
                    LL_segundasOpciones.setVisibility(View.VISIBLE);
                } else {
                    if(int_primeraOpcion!=-1){
                        cantidadActivos = cantidadSegundas[int_primeraOpcion];
                        CheckBox temp_CheckBox = vista_mapa.findViewById(id_checkBox);
                        temp_CheckBox.setChecked(true);
                        temp_CheckBox.setChecked(false);
                        for(int n=0 ; n<3 ; n++){
                            if(idTablas[(int_primeraOpcion*3)+n]!=0){
                                LinearLayout LL_temp = vista_mapa.findViewById(idTablas[(int_primeraOpcion*3)+n]);
                                LL_temp.setVisibility(View.GONE);
                            }
                        }
                        int_primeraOpcion = -1;
                        LL_primerasOpciones.setVisibility(View.VISIBLE);
                        LL_segundasOpciones.setVisibility(View.GONE);
                    } else {
                        LL_containerVertical.setVisibility(View.VISIBLE);
                        LL_primerasOpciones.setVisibility(View.GONE);
                    }
                }
            }
        });

        return vista_mapa;
    }

}