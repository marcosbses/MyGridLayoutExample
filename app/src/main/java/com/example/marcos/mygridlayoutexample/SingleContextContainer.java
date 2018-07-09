package com.example.marcos.mygridlayoutexample;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import java.util.List;

/**
 * Created by marcos on 2/10/18.
 */

public class SingleContextContainer {
    private static Context context;
    public static boolean setContext(Context contexto){
        if(context==null){
        context=contexto;
            return true;
        }
        return false;
    }
    public static Context getContext(){
        return context;
    }

}
