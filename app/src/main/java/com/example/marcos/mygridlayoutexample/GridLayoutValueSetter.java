package com.example.marcos.mygridlayoutexample;

import android.util.Log;

import java.util.List;

/**
 * Created by marcos on 8/19/18.
 */

public class GridLayoutValueSetter implements ISelectable,IValuable {

    private List<? extends IValuable> valuables;
    private IValuable valuable;
    private int position;
    private IValuableStack valuableStack;
    private ValueChangeEvaluator valueChangeEvaluator;
    private static GridLayoutValueSetter gridLayoutValueSetter;

    private GridLayoutValueSetter(List<? extends IValuable> valuables,ValueChangeEvaluator valueChangeEvaluator){
        this.valuables=valuables;
        this.valueChangeEvaluator=valueChangeEvaluator;
        this.valuableStack=HistoricValuableStack.getInstance();
        valuable=null;
    }

    public static synchronized GridLayoutValueSetter getInstance(){
        if(gridLayoutValueSetter==null){
            Log.i("infor","valuableSelector nulo-> instanciando");
            GridLayoutAdapter gridLayoutAdapter=GridLayoutAdapter.getInstance();
            List<? extends IValuable> imageButtons= gridLayoutAdapter.getImageButtonAdapterList();
            gridLayoutValueSetter=new GridLayoutValueSetter(imageButtons,ValueChangeEvaluator.getInstance());
        }
        return gridLayoutValueSetter;
    }

    @Override
    public void setValues(int value) {
        valuable.setValues(value);

        if(value!=-1){
            valuableStack.addValuable(valuable);
        }
    }

    @Override
    public int getValue() {
        return valuables.get(position).getValue();
    }

    @Override
    public void select(int position) {
        valueChangeEvaluator.select(position);
        valuable=valuables.get(position);
        this.position=position;
    }

    @Override
    public int getSelectedPosition() {
        return position;
    }
}
