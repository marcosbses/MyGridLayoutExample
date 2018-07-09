package com.example.marcos.mygridlayoutexample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.GridLayout;

/**
 * Created by marcos on 2/5/18.
 */

public class MyDibujadorCelda extends DibujadorCelda {
    public MyDibujadorCelda(Context context, GridLayout gridLayout){
        super(context,gridLayout);
    }

    @Override
    protected Drawable getDrawableToSetFromContext(Context context) {
        return context.getResources().getDrawable(R.drawable.celda);
    }

    @Override
    protected IDrawableContainerParent createDrawableContainerParent() {
        return GridLayoutAdapter.getInstance();
    }


}
