package com.example.marcos.mygridlayoutexample;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ValueDrawableGetter {
    private static ValueDrawableGetter valueDrawableGetter;
    private Context context;
    private ValueDrawableGetter(){
        this.context=SingleContextContainer.getContext();
    }
    public static synchronized ValueDrawableGetter getInstance(){
        if(valueDrawableGetter==null){
            valueDrawableGetter=new ValueDrawableGetter();
        }
        return valueDrawableGetter;
    }

    public Drawable getValueDrawable(int value){
        if(value==1){
            return context.getResources().getDrawable(R.drawable.vector_uno);
        }
        if(value==2){
            return context.getResources().getDrawable(R.drawable.vector_dos);
        }
        if(value==3){
            return context.getResources().getDrawable(R.drawable.vector_tres);
        }
        if(value==4){
            return context.getResources().getDrawable(R.drawable.vector_cuatro);
        }
        return context.getResources().getDrawable(R.drawable.vector_vacio);
    }
}
