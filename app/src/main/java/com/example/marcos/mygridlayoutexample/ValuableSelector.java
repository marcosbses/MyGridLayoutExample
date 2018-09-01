package com.example.marcos.mygridlayoutexample;

import android.util.Log;
import android.widget.ImageButton;

import java.util.List;

/**
 * Created by marcos on 2/10/18.
 */
//alias: GridLayout value setter
public class ValuableSelector implements IValuable,ISelectable {

    private GridLayoutValueSetter gridLayoutValueSetter;
    private static ValuableSelector valuableSelector;
    private SudokuGame sudokuGame;

    private ValuableSelector(){
        this.gridLayoutValueSetter=GridLayoutValueSetter.getInstance();
        this.sudokuGame=SudokuGame.getInstance();
    }

    public static synchronized ValuableSelector getInstance(){
        if(valuableSelector==null){
            valuableSelector=new ValuableSelector();
        }
        return valuableSelector;
    }

    @Override
    public void setValues(int value) {
        gridLayoutValueSetter.setValues(value);
    }

    @Override
    public int getValue() {
        return gridLayoutValueSetter.getValue();
    }

    @Override
    public void select(int position) {
        Log.i("infor","position selected:"+position);
        gridLayoutValueSetter.select(position);
    }

    @Override
    public int getSelectedPosition() {
        return gridLayoutValueSetter.getSelectedPosition();
    }


}
