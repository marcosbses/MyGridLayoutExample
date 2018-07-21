package com.example.marcos.mygridlayoutexample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;

public class MyDibujadorCelda3 extends DibujadorCelda {
    public MyDibujadorCelda3(Context context, GridLayout gridLayout) {
        super(context, gridLayout);
    }

    @Override
    protected Drawable getDrawableToSetFromContext(Context context) {
        return context.getDrawable(R.drawable.celda3);
    }

    @Override
    protected IDrawableContainerParent createDrawableContainerParent() {
        return GridLayoutAdapter.getInstance();
    }

}
