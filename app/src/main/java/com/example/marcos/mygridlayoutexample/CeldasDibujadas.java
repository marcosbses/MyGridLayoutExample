package com.example.marcos.mygridlayoutexample;

import android.app.Activity;
import android.app.Application;
import android.content.ContentProvider;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Arrays;

/**
 * Created by marcos on 2/6/18.
 */

public class CeldasDibujadas {
    private MyDibujadorCeldas dibujadorCeldas;
    private MyDibujadorCeldas dibujadorCeldas2;
    private int[][] matriz;
    private ISelectable selectable;
    private GridLayoutAdapter gridLayoutAdapter;
    private ImageButtonAdapter lastCentralImageButtonAdapter;
    private int ipr=0;
    private int jpr=0;

    public CeldasDibujadas(MyDibujadorCeldas dibujadorCeldas,MyDibujadorCeldas dibujadorCeldas2){
        this.dibujadorCeldas=dibujadorCeldas;
        this.dibujadorCeldas2=dibujadorCeldas2;
        matriz=MatrizContainer.getMatriz();//matriz 3x3
        GridLayoutAdapter gridLayoutAdapter=GridLayoutAdapter.getInstance();
        selectable=ValuableSelector.getInstance();
        this.gridLayoutAdapter=GridLayoutAdapter.getInstance();
    }


    private void undo(){
        dibujadorCeldas.dibujarCruz(ipr,jpr);

    }


    public void dibujarCruz(int i, int j) {
        if(this.lastCentralImageButtonAdapter!=null){
            lastCentralImageButtonAdapter.pop();
        }
        undo();
        dibujadorCeldas2.dibujarCruz(i,j);


        ipr=i;
        jpr=j;
        selectable.select(dibujadorCeldas.getColumnCount()*i+j);
        //
        ImageButtonAdapter imageButtonAdapter=gridLayoutAdapter.getImageButtonAdapter(i*9+j);
        if(imageButtonAdapter!=lastCentralImageButtonAdapter){
            imageButtonAdapter.popUp();
        }
        lastCentralImageButtonAdapter=imageButtonAdapter;


    }
}
