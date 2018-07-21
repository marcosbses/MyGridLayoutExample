package com.example.marcos.mygridlayoutexample;

public class MyDibujadorCeldasWAltColors extends MyDibujadorCeldas {
    private DibujadorCelda dibujadorCeldaAlt;
    public MyDibujadorCeldasWAltColors(DibujadorCelda dibujadorCelda,DibujadorCelda dibujadorCeldaAlt) {

        super(dibujadorCelda);
        this.dibujadorCeldaAlt=dibujadorCeldaAlt;
    }

    @Override
    public void dibujarCruz(int i,int j){
        //vert
        for(int n=0;n<rowCount;n++){
            if(n<2){
                dibujadorCeldaAlt.dibujarCelda(n,j);
            }else {
                dibujadorCelda.dibujarCelda(n,j);
            }

        }
        //hor
        for(int n=0;n<columnCount;n++){
            if(n!=j){
                if(i<2){
                    dibujadorCeldaAlt.dibujarCelda(i,n);
                }else{
                    dibujadorCelda.dibujarCelda(i,n);
                }
            }
        }
    }
}
