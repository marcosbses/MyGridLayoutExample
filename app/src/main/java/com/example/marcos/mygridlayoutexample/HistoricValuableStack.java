package com.example.marcos.mygridlayoutexample;

import java.util.ArrayList;
import java.util.List;

public class HistoricValuableStack implements IPositionedValuableStack{
    private List<PositionedValuable> valuables;
    private static HistoricValuableStack historicValuableStack;
    private HistoricValuableStack(){
        this.valuables=new ArrayList<>();
    }

    public static synchronized HistoricValuableStack getInstance(){
        if(historicValuableStack==null){
            historicValuableStack=new HistoricValuableStack();
        }
        return historicValuableStack;
    }

    @Override
    public void addValuable(PositionedValuable valuable) {
        this.valuables.add(valuable);
    }

    @Override
    public PositionedValuable remove() {
        int size=valuables.size();
        if(size>0) {

            return valuables.remove(valuables.size() - 1);
        }else {
            return null;
        }
    }
}
