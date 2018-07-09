package com.example.marcos.mygridlayoutexample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * Created by marcos on 2/5/18.
 */

public abstract class DibujadorCelda implements IDibujadorCelda,IGrid{
    protected GridLayout gridLayout;
    protected Drawable drawable;
    protected IDrawableContainerParent drawableContainerParent;
    protected Context contexto;
    public DibujadorCelda(Context context,GridLayout gridLayout){
        this.contexto=context;
        this.gridLayout=gridLayout;

        initialize(context);
    }

    private synchronized void initialize(Context context){
        drawable=getDrawableToSetFromContext(context);
        drawableContainerParent=createDrawableContainerParent();
    }

    protected abstract Drawable getDrawableToSetFromContext(Context context);

    protected abstract IDrawableContainerParent createDrawableContainerParent();

    @Override
    public void dibujarCelda(int i, int j) {

        Log.i("infor","Dibujador celda:dibujarCelda: i:"+i+", j:"+j);
        IDrawableContainer drawableContainer=drawableContainerParent.getChildAt(i*gridLayout.getColumnCount()+j);
        drawableContainer.setImageDrawabe(drawable);
    }

    @Override
    public int getColumnCount() {
        return gridLayout.getColumnCount();
    }

    @Override
    public int getRowCount() {
        return gridLayout.getChildCount()/gridLayout.getColumnCount();
    }
}
