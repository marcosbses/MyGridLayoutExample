package com.example.marcos.mygridlayoutexample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
    private Drawable fondoDrawable;
    private Context context;
    private int fondoDrawableNumber;
    public ImageButtonAdapter(ImageButton imageButton, Context context){
        this.imageButton=imageButton;
        this.value=-1;
        this.context=context;
        this.valueDrawable=null;
        this.fondoDrawableNumber=0;
    }

    @Override
    public void setImageDrawabe(Drawable fondoDrawable) {
        this.fondoDrawable=fondoDrawable;
        if(valueDrawable!=null){
            Drawable[] drawables={fondoDrawable,valueDrawable};
            imageButton.setImageDrawable(new LayerDrawable(drawables));
        }else{
            imageButton.setImageDrawable(fondoDrawable);
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


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                if(layerDrawable==null){
                    Log.i("infor","ld is null");
                    Drawable[] drawables={fondoDrawable,valueDrawable};
                    final Drawable newDrawable=new LayerDrawable(drawables);
                    final int startFondoDrawableNumber=this.fondoDrawableNumber;

                    final Bitmap backBitmap=Bitmap.createBitmap(imageButton.getDrawable().getIntrinsicWidth(), imageButton.getDrawable().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    final Bitmap forBitmap=Bitmap.createBitmap(imageButton.getDrawable().getIntrinsicWidth(), imageButton.getDrawable().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas1=new Canvas(backBitmap);
                    Canvas canvas2=new Canvas(forBitmap);

                    imageButton.getDrawable().setBounds(0, 0, canvas1.getWidth(), canvas1.getHeight());
                    imageButton.getDrawable().draw(canvas1);
                    newDrawable.setBounds(0, 0, canvas1.getWidth(), canvas1.getHeight());
                    newDrawable.draw(canvas2);

                    final BitmapCombiner bitmapCombiner=new BitmapCombiner(backBitmap,forBitmap);

                    ValueAnimator valueAnimator=ValueAnimator.ofInt(0,100);
                    valueAnimator.setDuration(1000);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        private int lastValue=-1;
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            if(lastValue!=(int)valueAnimator.getAnimatedValue()&&((int)valueAnimator.getAnimatedValue())%5==0){
                                lastValue=(int)valueAnimator.getAnimatedValue();
                                Bitmap bitmap=bitmapCombiner.getCombinedBitmap(lastValue);

                                imageButton.setImageBitmap(bitmap);
                            }

                        }
                    });
                    valueAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            imageButton.setImageDrawable(newDrawable);
                            if(startFondoDrawableNumber!=ImageButtonAdapter.this.fondoDrawableNumber){
                                Log.i("infor","fondo changed in animation");
                                if(valueDrawable!=null){
                                    Drawable[] drawables={fondoDrawable,valueDrawable};
                                    imageButton.setImageDrawable(new LayerDrawable(drawables));
                                }else{
                                    imageButton.setImageDrawable(fondoDrawable);
                                }
                            }
                            super.onAnimationEnd(animation);

                        }
                    });
                    valueAnimator.start();
                }else{
                    Log.i("infor","ld is not null");
                    Drawable fondoDrawable=layerDrawable.getDrawable(0);
                    Drawable[] drawables={fondoDrawable,valueDrawable};
                    imageButton.setImageDrawable(new LayerDrawable(drawables));
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
}
