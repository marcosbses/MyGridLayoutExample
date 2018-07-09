package com.example.marcos.mygridlayoutexample;

import android.content.Context;
import android.util.Log;
import android.widget.GridLayout;

import java.util.Arrays;

/**
 * Created by marcos on 2/6/18.
 */

public class CeldasDibujadas {
    private MyDibujadorCeldas dibujadorCeldas;
    private MyDibujadorCeldas dibujadorCeldas2;
    private int[][] matriz;
    private ISelectable selectable;
    private int ipr=0;
    private int jpr=0;

    public CeldasDibujadas(MyDibujadorCeldas dibujadorCeldas,MyDibujadorCeldas dibujadorCeldas2){
        this.dibujadorCeldas=dibujadorCeldas;
        this.dibujadorCeldas2=dibujadorCeldas2;
        matriz=MatrizContainer.getMatriz();//matriz 3x3
        GridLayoutAdapter gridLayoutAdapter=GridLayoutAdapter.getInstance();
        selectable=ValuableSelector.getInstance();
    }

    private void clear(){
        Log.i("infor","undo()");
        dibujadorCeldas.dibujarCruz(ipr,jpr);
        matriz=new int[dibujadorCeldas.getColumnCount()][dibujadorCeldas.getRowCount()];
    }

    private void undo(){
        if(matriz[ipr][jpr]==1){
            clear();
        }else{
            dibujadorCeldas2.dibujarCruz(ipr,jpr);

        }
    }


    public void dibujarCruz(int i, int j) {
        if(matriz[i][j]==1){
            undo();
            matriz[i][j]=0;
        }else{
            clear();
            dibujadorCeldas2.dibujarCruz(i,j);
            matriz[i][j]=1;

        }

        ipr=i;
        jpr=j;
        selectable.select(dibujadorCeldas.getColumnCount()*i+j);
    }
}
