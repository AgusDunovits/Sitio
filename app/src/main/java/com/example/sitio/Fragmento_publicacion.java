package com.example.sitio;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento_publicacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento_publicacion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragmento_publicacion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmento_publicacion.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmento_publicacion newInstance(String param1, String param2) {
        Fragmento_publicacion fragment = new Fragmento_publicacion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }
    SharedPreferences id_publicacion;
    SharedPreferences.Editor id_publicacion_edit;
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
        View view = inflater.inflate(R.layout.fragment_fragmento_publicacion, container, false);
        ImageButton boton_comida1;
        boton_comida1 = view.findViewById(R.id.boton_comida1);

        boton_comida1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transicion = fragmentManager.beginTransaction();
                transicion.replace(R.id.Ventana_principal, new Fragmento_publicacion_edit());
                transicion.commit();
                /*
                ///////////// PROVISORIOO HASTA TENER BASE DE DATOS /////////////////////
                id_publicacion = getContext().getSharedPreferences("id_publicacion", MODE_PRIVATE);
                id_publicacion_edit = id_publicacion.edit();

                id_publicacion_edit.putInt("id_publicacion", boton_comida1.getId());
                id_publicacion_edit.commit();
                */
            }
        });
        return view;
    }
}