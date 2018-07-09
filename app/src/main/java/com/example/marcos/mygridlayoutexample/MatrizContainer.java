package com.example.marcos.mygridlayoutexample;

/**
 * Created by marcos on 2/14/18.
 */

public class MatrizContainer {
    private static int[][] matriz;

    public static synchronized int[][] getMatriz(){
        if(matriz==null){
            matriz=new int[3][3];
        }
        return matriz;
    }
}
