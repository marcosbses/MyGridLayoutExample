package com.example.marcos.mygridlayoutexample;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 2/9/18.
 */

public class GridLayoutAdapter implements IDrawableContainerParent,IValuableParent,IGrid {
    private GridLayout gridLayout;
    private List<ImageButtonAdapter> imageButtonAdapterList;
    private static GridLayoutAdapter gridLayoutAdapter;


    private GridLayoutAdapter(GridLayout gridLayout,Context context){
        this.gridLayout=gridLayout;
        imageButtonAdapterList=new ArrayList<>();
        for(int i=0;i<gridLayout.getChildCount();i++){
            imageButtonAdapterList.add(new ImageButtonAdapter((ImageButton) gridLayout.getChildAt(i),context));
        }
    }
    @Override
    public IDrawableContainer getChildAt(int index) {
        return imageButtonAdapterList.get(index);
    }

    public List<ImageButtonAdapter> getImageButtonAdapterList(){return imageButtonAdapterList;}

    public static synchronized GridLayoutAdapter getInstance(){
        if(gridLayoutAdapter==null){
            GridLayout gridLayout1=SingleMainGridLayoutContainer.getGridLayout();
            if(gridLayout1==null){
                Log.i("infor","grid layout nulo in GridlayoutAdapter");
            }
            if(Looper.myLooper() == Looper.getMainLooper()){Log.i("infor","main thread get inst of grid lay adapt");}
            gridLayoutAdapter=new GridLayoutAdapter(SingleMainGridLayoutContainer.getGridLayout(),SingleContextContainer.getContext());
        }
        return gridLayoutAdapter;
    }

    @Override
    public IValuable getValuableChildAt(int index) {
        return getImageButtonAdapterList().get(index);
    }

    @Override
    public int getNumberOfValuableChildren() {
        return imageButtonAdapterList.size();
    }

    @Override
    public int getColumnCount() {
        return this.gridLayout.getColumnCount();
    }

    @Override
    public int getRowCount() {
        return this.gridLayout.getRowCount();
    }

    public ImageButtonAdapter getImageButtonAdapter(int i){
        return imageButtonAdapterList.get(i);
    }
}
