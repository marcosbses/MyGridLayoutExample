package com.example.marcos.mygridlayoutexample;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

/**
 * Created by marcos on 2/7/18.
 */

public class PressCommand {
    ViewGroup viewGroup;
    CeldasDibujadas celdasDibujadas;
    int columnCount;

    public PressCommand(CeldasDibujadas celdasDibujadas, GridLayout gridLayout){
        this.celdasDibujadas=celdasDibujadas;
        columnCount=gridLayout.getColumnCount();
    }
    public void execute(View v){
        viewGroup=(ViewGroup)v.getParent();
        int index=viewGroup.indexOfChild(v);
        int i=(int)index/columnCount;
        int j=(int)index%columnCount;
        celdasDibujadas.dibujarCruz(i,j);
    }
}

