package com.example.marcos.mygridlayoutexample;

import android.widget.GridLayout;

/**
 * Created by marcos on 2/14/18.
 */

public class ValueChangeEvaluator {
    private static ValueChangeEvaluator valueChangeEvaluator;
    private ISelectable selectable;
    private IValuableParent valuableParent;
    private ValueChangeEvaluator(){
        this.valuableParent=GridLayoutAdapter.getInstance();
        this.selectable=ValuableSelector.getInstance();
    }

    public static synchronized ValueChangeEvaluator getInstance(){
        if(valueChangeEvaluator==null){
            valueChangeEvaluator=new ValueChangeEvaluator();
        }
        return valueChangeEvaluator;
    }

    public boolean evaluateChange(int value){
        for(int i=0;i<valuableParent.getNumberOfValuableChildren();i++){
            if(valuableParent.getValuableChildAt(i).getValue()==value){
                return false;
            }
        }
        return true;
    }
}
