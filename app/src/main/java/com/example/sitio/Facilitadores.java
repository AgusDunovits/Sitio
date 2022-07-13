package com.example.sitio;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import java.lang.reflect.Type;

public class Facilitadores {

    /** Retorna en pixeles según la unidad de medida dp a convertir.
     * @param dp Cantidad de dp a convertir
     * @param metricasMonitor Recursos obtenidos por la función getResources().getDisplayMetrics()
     * @return Pixeles;
     */
    public int dpApixel( int dp, DisplayMetrics metricasMonitor ){
        return Math.round( TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, dp, metricasMonitor ) );
    };

    /** Retorna los colores estandar
     * @param color 1 = Naranja claro
     *              2 = Naranja más oscuro
     *              3 = Gris inactivo
     * @return Color;
     */
    public int ColorEstandar( int color ){
        if( color == 1 ) return Color.parseColor("#FFB95A");
        if( color == 2 ) return Color.parseColor("#FF9A11");
        if( color == 3 ) return Color.parseColor("#F3F3F3");
        return Color.BLACK;
    };

}
