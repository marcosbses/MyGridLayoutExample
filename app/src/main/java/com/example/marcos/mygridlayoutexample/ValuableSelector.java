package com.example.marcos.mygridlayoutexample;

import android.util.Log;
import android.widget.ImageButton;

import java.util.List;

/**
 * Created by marcos on 2/10/18.
 */

public class ValuableSelector implements IValuable,ISelectable {
    private List<? extends IValuable> valuables;
    private IValuable valuable;
    private int position;
    private static ValuableSelector valuableSelector;
    private ValuableSelector(List<? extends IValuable> valuables){
        this.valuables=valuables;
        valuable=null;
    }

    public static synchronized ValuableSelector getInstance(){
        if(valuableSelector==null){
            Log.i("infor","valuableSelector nulo-> instanciando");
            GridLayoutAdapter gridLayoutAdapter=GridLayoutAdapter.getInstance();
            List<? extends IValuable> imageButtons= gridLayoutAdapter.getImageButtonAdapterList();
            valuableSelector=new ValuableSelector(imageButtons);
        }
        return valuableSelector;
    }

    @Override
    public void setValues(int value) {
        valuable.setValues(value);
    }

    @Override
    public int getValue() {
        return valuables.get(position).getValue();
    }

    @Override
    public void select(int position) {
        Log.i("infor","position selected:"+position);
        valuable=valuables.get(position);
        this.position=position;
    }

    @Override
    public int getSelectedPosition() {
        return position;
    }
}
