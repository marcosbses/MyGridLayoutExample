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
        if(j<3||j>5) {
            for (int n = 0; n < rowCount; n++) {
                if (n < 3||n>5) {
                    dibujadorCeldaAlt.dibujarCelda(n, j);
                } else {
                    dibujadorCelda.dibujarCelda(n, j);
                }

            }
        }else {
            for (int n = 0; n < rowCount; n++) {
                if (n < 3||n>5) {
                    dibujadorCelda.dibujarCelda(n, j);
                } else {
                    dibujadorCeldaAlt.dibujarCelda(n, j);
                }

            }
        }
        //hor
        if(i<3||i>5) {
            for (int n = 0; n < columnCount; n++) {
                if (n != j) {
                    if (n < 3 || n > 5) {
                        dibujadorCeldaAlt.dibujarCelda(i, n);
                    } else {
                        dibujadorCelda.dibujarCelda(i, n);
                    }
                }
            }
        }else {
            for (int n = 0; n < columnCount; n++) {
                if (n != j) {
                    if (n < 3 || n > 5) {
                        dibujadorCelda.dibujarCelda(i, n);
                    } else {
                        dibujadorCeldaAlt.dibujarCelda(i, n);
                    }
                }
            }
        }
    }
}
