package com.example.marcos.mygridlayoutexample;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by marcos on 2/9/18.
 */

public class ImageButtonAdapter implements IDrawableContainer,IValuable,ISquare {
    private ImageButton imageButton;
    private int value;
    private Drawable valueDrawable;
    private Drawable fondoDrawable;
    private Context context;
    private int fondoDrawableNumber;
    private ImageView popUpView;
    public ImageButtonAdapter(ImageButton imageButton, Context context){
        this.imageButton=imageButton;
        this.value=-1;
        this.context=context;
        this.valueDrawable=null;
        this.fondoDrawableNumber=0;
        popUpView=new ImageView(SingleContextContainer.getContext());
        popUpView.setBackground(context.getDrawable(R.drawable.celda10));
        Activity activity=(Activity)SingleContextContainer.getContext();
        //activity.addContentView(popUpView,new ViewGroup.LayoutParams(300,300));

        ViewGroup viewGroup=(ViewGroup)(activity.findViewById(R.id.frameLayout));
        viewGroup.addView(popUpView,new ViewGroup.LayoutParams(300,300));


        popUpView.setVisibility(View.INVISIBLE);
        //set default fondo
        setImageDrawabe(context.getDrawable(R.drawable.celda));

    }

    @Override
    public void setImageDrawabe(Drawable fondoDrawable) {
        this.fondoDrawable=fondoDrawable;
        if(valueDrawable!=null){
            Drawable[] drawables={fondoDrawable,valueDrawable};
            //imageButton.setImageDrawable(new LayerDrawable(drawables));
            imageButton.setBackground(new LayerDrawable(drawables));
        }else{
            //imageButton.setImageDrawable(fondoDrawable);
            imageButton.setBackground(fondoDrawable);
        }
        if(fondoDrawableNumber==0){
            fondoDrawableNumber=1;
        }else{
            fondoDrawableNumber=0;
        }
    }

    public void setValues(int value){
        Log.i("infor","in ImageButtonAdapter set values(value)");
        LayerDrawable layerDrawable=getLayerDrawable();
        setValue(value);

        if(value==1){
            valueDrawable=context.getDrawable(R.drawable.vector_uno);
        }
        if(value==2){
            valueDrawable=context.getDrawable(R.drawable.vector_dos);
        }
        if(value==3){
            valueDrawable=context.getDrawable(R.drawable.vector_tres);
        }
        if(value==4){
            valueDrawable=context.getDrawable(R.drawable.vector_cuatro);
        }
        if(value==-1){
            valueDrawable=context.getDrawable(R.drawable.vector_vacio);
        }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable popupDrawable=popUpView.getBackground();
            try{
                LayerDrawable popupLayerDrawable=(LayerDrawable)popupDrawable;
                Drawable[] drawables={popupLayerDrawable.getDrawable(0),valueDrawable};
                popUpView.setBackground(new LayerDrawable(drawables));
            }catch (ClassCastException e){
                Drawable[] drawables={popupDrawable,valueDrawable};
                popUpView.setBackground(new LayerDrawable(drawables));
            }



                if(layerDrawable==null){
                    Log.i("infor","ld is null");
                    Drawable[] drawables={fondoDrawable,valueDrawable};

                    imageButton.setBackground(new LayerDrawable(drawables));






                }else{
                    Log.i("infor","ld is not null");
                    Drawable fondoDrawable=layerDrawable.getDrawable(0);
                    Drawable[] drawables={fondoDrawable,valueDrawable};
                    //imageButton.setImageDrawable(new LayerDrawable(drawables));
                    imageButton.setBackground(new LayerDrawable(drawables));
                }
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

    @Override
    public int getDimension() {
        return imageButton.getWidth();
    }

    @Override
    public void setDimension(int dimension) {
        ViewGroup.LayoutParams params=imageButton.getLayoutParams();
        params.width=dimension;
        params.height=dimension;
        imageButton.setLayoutParams(params);
    }

    private int leftPopUpMargin(){
        int lefM=imageButton.getLeft();
        lefM=lefM+(int)(imageButton.getWidth()/2);
        int popupWidth=popUpView.getWidth();
        lefM=lefM-(int)(popupWidth/2);
        return lefM;
    }

    public void popUp(){
        Log.i("infor","top: "+imageButton.getTop());
        popUpView.setY(imageButton.getTop());
        ViewGroup.MarginLayoutParams marginLayoutParams=(ViewGroup.MarginLayoutParams) popUpView.getLayoutParams();
        marginLayoutParams.leftMargin=leftPopUpMargin();
        popUpView.setLayoutParams(marginLayoutParams);

        this.setValues(value);
        popUpView.setVisibility(View.VISIBLE);
    }
    public void pop(){
        popUpView.setVisibility(View.INVISIBLE);
    }
}
