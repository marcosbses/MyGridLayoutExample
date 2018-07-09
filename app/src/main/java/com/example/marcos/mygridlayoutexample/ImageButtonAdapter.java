package com.example.marcos.mygridlayoutexample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageButton;

/**
 * Created by marcos on 2/9/18.
 */

public class ImageButtonAdapter implements IDrawableContainer,IValuable {
    private ImageButton imageButton;
    private int value;
    private Drawable valueDrawable;
    private Context context;
    public ImageButtonAdapter(ImageButton imageButton, Context context){
        this.imageButton=imageButton;
        this.value=-1;
        this.context=context;
        this.valueDrawable=null;
    }

    @Override
    public void setImageDrawabe(Drawable fondoDrawable) {
        if(valueDrawable!=null){
            Drawable[] drawables={fondoDrawable,valueDrawable};
            imageButton.setImageDrawable(new LayerDrawable(drawables));
        }else{
            imageButton.setImageDrawable(fondoDrawable);
        }
    }

    public void setValues(int value){
        Log.i("infor","in ImageButtonAdapter set values(value)");
        LayerDrawable layerDrawable=getLayerDrawable();
        setValue(value);
        if(value==1){
            value=1;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                valueDrawable=context.getDrawable(R.drawable.vector_uno);
                if(layerDrawable==null){
                    Log.i("infor","ld is null");
                    Drawable fondoDrawable=imageButton.getDrawable();
                    Drawable[] drawables={fondoDrawable,valueDrawable};
                    TransitionDrawable transitionDrawable=new TransitionDrawable(new Drawable[]{context.getDrawable(R.drawable.celda),context.getDrawable(R.drawable.celda2)});

                    imageButton.setImageDrawable(transitionDrawable);
                    transitionDrawable.setCrossFadeEnabled(true);
                    transitionDrawable.startTransition(20000);
                }else{
                    Log.i("infor","ld is not null");
                    Drawable fondoDrawable=layerDrawable.getDrawable(0);
                    Drawable[] drawables={fondoDrawable,valueDrawable};
                    imageButton.setImageDrawable(new LayerDrawable(drawables));
                }
            }
        }
        if(value==2){
                valueDrawable=context.getDrawable(R.drawable.vector_dos);
                value=2;
                if(layerDrawable==null){
                    Log.i("infor","ld is null");
                    Drawable fondoDrawable=imageButton.getDrawable();
                    Drawable[] drawables={fondoDrawable,valueDrawable};
                    imageButton.setImageDrawable(new LayerDrawable(drawables));
                }else{
                    Log.i("infor","ld is not null");
                    Drawable fondoDrawable=layerDrawable.getDrawable(0);
                    Drawable[] drawables={fondoDrawable,valueDrawable};
                    imageButton.setImageDrawable(new LayerDrawable(drawables));
                }
        }
        if(value==0){
            valueDrawable=null;
        }
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    private LayerDrawable getLayerDrawable(){
        LayerDrawable layerDrawable=null;
        try {
            layerDrawable = (LayerDrawable) imageButton.getDrawable();
        }catch (ClassCastException e){

        }
        return layerDrawable;
    }
}
