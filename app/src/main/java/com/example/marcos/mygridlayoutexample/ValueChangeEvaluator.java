package com.example.marcos.mygridlayoutexample;

import android.util.Log;
import android.widget.GridLayout;

/**
 * Created by marcos on 2/14/18.
 */

public class ValueChangeEvaluator implements ISelectable {
    private static ValueChangeEvaluator valueChangeEvaluator;
    private int position;

    private IValuableParent valuableParent;

    private ValueChangeEvaluator() {
        this.valuableParent = GridLayoutAdapter.getInstance();
    }

    public static synchronized ValueChangeEvaluator getInstance() {
        if (valueChangeEvaluator == null) {
            valueChangeEvaluator = new ValueChangeEvaluator();
        }
        return valueChangeEvaluator;
    }

    public boolean evaluateChange(int value) {
        Log.i("infor", "evaluate change");
        Log.i("infor", "position: " + position);

        int dj = position % 3;
        int i = (int) position / (3 * 3);
        int di = i % 3;
        int pi = position - (di * (3 * 3));
        pi = pi - dj;

        /*Log.i("infor", "dj: " + dj);
        Log.i("infor", "i: " + i);
        Log.i("infor", "di: " + di);*/
        for (i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int p = pi + (i * 3 * 3) + j;
                //Log.i("infor", "p: " + p);
                if (valuableParent.getValuableChildAt(p).getValue() == value) {
                    return false;
                }

            }
        }

        return true;

    }

    @Override
    public void select(int position) {
        Log.i("infor", "vche position set to: " + position);
        this.position = position;
    }

    @Override
    public int getSelectedPosition() {
        return this.position;
    }
}
