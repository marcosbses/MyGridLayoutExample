package com.example.marcos.mygridlayoutexample;

/**
 * Created by marcos on 2/5/18.
 */

public class MyDibujadorCeldas implements IGrid{
    private DibujadorCelda dibujadorCelda;
    private int columnCount;
    private int rowCount;

    public MyDibujadorCeldas(DibujadorCelda dibujadorCelda){
        this.dibujadorCelda=dibujadorCelda;
        columnCount=dibujadorCelda.getColumnCount();
        rowCount=dibujadorCelda.getRowCount();
    }

    public void dibujarHorizontal(int i){
        for(int n=0;n<columnCount;n++){
            dibujadorCelda.dibujarCelda(i,n);
        }
    }

    public void dibujarCruz(int i,int j){
        //vert
        for(int n=0;n<rowCount;n++){
            dibujadorCelda.dibujarCelda(n,j);
        }
        //hor
        for(int n=0;n<columnCount;n++){
            if(n!=j){
                dibujadorCelda.dibujarCelda(i,n);
            }
        }
    }

    @Override
    public int getColumnCount(){
        return columnCount;
    }

    @Override
    public int getRowCount(){
        return rowCount;
    }

}
