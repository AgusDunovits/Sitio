package com.example.sitio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento_publicacion_edit_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento_publicacion_edit_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragmento_publicacion_edit_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmento_publicacion_edit_2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmento_publicacion_edit_2 newInstance(String param1, String param2) {
        Fragmento_publicacion_edit_2 fragment = new Fragmento_publicacion_edit_2();
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

    Button boton_emoji, siguiente_edit2, boton_aplicar;
    Button boton13, boton22, boton23, boton24;
    LinearLayout linear_emoji, linear_filtro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_publicacion_edit_2, container, false);
        boton_emoji = view.findViewById(R.id.emoji_boton_edit2);
        boton13 = view.findViewById(R.id.button13);
        boton22 = view.findViewById(R.id.button22);
        boton23 = view.findViewById(R.id.button23);
        boton24 = view.findViewById(R.id.button24);
        linear_emoji = view.findViewById(R.id.linear_emoji);
        linear_filtro = view.findViewById(R.id.linear_inicio_filtro);
        boton_aplicar = view.findViewById(R.id.boton_app);
        siguiente_edit2 = view.findViewById(R.id.siguiente_edit2);
        pantalla_emoji(false);
        boton_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla_emoji(true);
            }
        });
        siguiente_edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla_emoji(false);
            }
        });
        boton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transicion = fragmentManager.beginTransaction();
                transicion.replace(R.id.Ventana_principal, new Fragmento_publicacion_publicar());
                transicion.commit();
            }
        });
        boton_aplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transicion = fragmentManager.beginTransaction();
                transicion.replace(R.id.Ventana_principal, new Fragmento_publicacion_edit());
                transicion.commit();
            }
        });
        return view;
    }

    private void pantalla_emoji(Boolean activado) {
        if (activado) {
            linear_filtro.setVisibility(View.INVISIBLE);
            linear_emoji.setVisibility(View.VISIBLE);
        } else {
            linear_filtro.setVisibility(View.VISIBLE);
            linear_emoji.setVisibility(View.INVISIBLE);
        }
    }

}