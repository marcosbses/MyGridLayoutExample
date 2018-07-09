package com.example.marcos.mygridlayoutexample;

import android.os.Looper;
import android.util.Log;
import android.widget.GridLayout;

/**
 * Created by marcos on 2/10/18.
 */

public class SingleMainGridLayoutContainer {
    private static GridLayout gridLayout;
    public static void setGridLayout(GridLayout gridLayoutt){
        if(gridLayout==null){
            gridLayout=gridLayoutt;
        }else{
            Log.i("infor","container already has a gird layout");
        }

    }
    public static synchronized GridLayout getGridLayout(){
        return gridLayout;
    }
}