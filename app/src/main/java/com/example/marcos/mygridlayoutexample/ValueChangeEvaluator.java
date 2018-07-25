package com.example.marcos.mygridlayoutexample;

import android.util.Log;
import android.widget.GridLayout;

/**
 * Created by marcos on 2/14/18.
 */

public class ValueChangeEvaluator implements ISelectable{
    private static ValueChangeEvaluator valueChangeEvaluator;
    private int position;

    private IValuableParent valuableParent;
    private ValueChangeEvaluator(){
        this.valuableParent=GridLayoutAdapter.getInstance();
    }

    public static synchronized ValueChangeEvaluator getInstance(){
        if(valueChangeEvaluator==null){
            valueChangeEvaluator=new ValueChangeEvaluator();
        }
        return valueChangeEvaluator;
    }

    public boolean evaluateChange(int value){
        Log.i("infor","evaluate change");
        Log.i("infor","position: "+position);
        if(position>6){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void select(int position) {
        Log.i("infor","vche position set to: "+position);
        this.position=position;
    }

    @Override
    public int getSelectedPosition() {
        return this.position;
    }
}
