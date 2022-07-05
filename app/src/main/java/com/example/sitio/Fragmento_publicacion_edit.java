package com.example.sitio;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento_publicacion_edit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento_publicacion_edit extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragmento_publicacion_edit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmento_publicacion_edit.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmento_publicacion_edit newInstance(String param1, String param2) {
        Fragmento_publicacion_edit fragment = new Fragmento_publicacion_edit();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public TextView texto_prueba;
    public Button boton_publicacion;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    SharedPreferences id_publicacion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_publicacion_edit, container, false);

        /*
        ///////////// PROVISORIOO HASTA TENER BASE DE DATOS /////////////////////
        id_publicacion = getContext().getSharedPreferences("id_publicacion", MODE_PRIVATE);
        int id_imagen = id_publicacion.getInt("id_publicacion", 999);
        id_publicacion.edit().commit();
        ////////////////////////////////////////////////////////////////////////
        imagen_foto1 = view.findViewById(R.id.imagen_foto1);
        //Drawable drawable = Drawable.createFromPath(id_imagen);
        imagen_foto1.setBackgroundResource(id_imagen);
        */
        boton_publicacion = view.findViewById(R.id.a_f_publicacion);
        boton_publicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transicion = fragmentManager.beginTransaction();
                transicion.replace(R.id.Ventana_principal, new Fragmento_publicacion_publicar());
                transicion.commit();
            }
        });
        return  view;
    }
    public void dato_de_publicacion(String mensaje){
        texto_prueba.setText(mensaje);
    }

}