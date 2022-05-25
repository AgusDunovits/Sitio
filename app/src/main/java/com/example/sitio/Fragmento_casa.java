package com.example.sitio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.GridLayout.LayoutParams;

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
        int cantidad_historias = 4;
        LayoutParams params1;
        View vista_inicio = inflater.inflate(R.layout.fragment_fragmento_casa, container, false);
        LinearLayout LL_layoutHistorias = vista_inicio.findViewById(R.id.LayoutHistorias);
        if(cantidad_historias == 0){
            LL_layoutHistorias.setVisibility(vista_inicio.GONE);
        } else {
            for (int n = 0 ; n < cantidad_historias ; n++) {
                String descripcion = "Historia\n" + String.valueOf(n);
                Button boton = new Button(vista_inicio.getContext());
                boton.setText(descripcion);
                boton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LL_layoutHistorias.addView(boton);
            }
        };
        return vista_inicio;
    }
}