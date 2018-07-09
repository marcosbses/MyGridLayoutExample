package com.example.marcos.mygridlayoutexample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;

/**
 * Created by marcos on 2/7/18.
 */

public class MyDibujadorCelda2 extends DibujadorCelda {

    public MyDibujadorCelda2(Context context, GridLayout gridLayout) {
        super(context, gridLayout);
    }

    @Override
    protected Drawable getDrawableToSetFromContext(Context context) {
        return context.getDrawable(R.drawable.celda2);
    }

    @Override
    protected IDrawableContainerParent createDrawableContainerParent() {
        return GridLayoutAdapter.getInstance();
    }
}
