package com.example.marcos.mygridlayoutexample;

/**
 * Created by marcos on 8/20/18.
 */

public class PositionedValuable implements IValuable {
    private IValuable valuable;
    private int position;
    public PositionedValuable(IValuable valuable,int position){
        this.valuable=valuable;
        this.position=position;
    }

    @Override
    public void setValues(int value) {
        valuable.setValues(value);
    }

    @Override
    public int getValue() {
        return valuable.getValue();
    }

    public int getPosition(){
        return this.position;
    }

}
