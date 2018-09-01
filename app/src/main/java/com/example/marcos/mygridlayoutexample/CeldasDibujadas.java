package com.example.marcos.mygridlayoutexample;



/**
 * Created by marcos on 2/6/18.
 */

public class CeldasDibujadas {
    private MyDibujadorCeldas dibujadorCeldas;
    private MyDibujadorCeldas dibujadorCeldas2;
    private ISelectable selectable;
    private GridLayoutAdapter gridLayoutAdapter;
    private ImageButtonAdapter lastCentralImageButtonAdapter;
    private int ipr=0;
    private int jpr=0;

    public CeldasDibujadas(MyDibujadorCeldas dibujadorCeldas,MyDibujadorCeldas dibujadorCeldas2){
        this.dibujadorCeldas=dibujadorCeldas;
        this.dibujadorCeldas2=dibujadorCeldas2;
        selectable=ValuableSelector.getInstance();
        this.gridLayoutAdapter=GridLayoutAdapter.getInstance();
    }


    private void undo(){
        dibujadorCeldas.dibujarCruz(ipr,jpr);

    }


    public void dibujarCruz(int i, int j) {
        if(this.lastCentralImageButtonAdapter!=null){
            lastCentralImageButtonAdapter.pop();
        }
        undo();
        dibujadorCeldas2.dibujarCruz(i,j);


        ipr=i;
        jpr=j;
        selectable.select(dibujadorCeldas.getColumnCount()*i+j);
        //
        ImageButtonAdapter imageButtonAdapter=gridLayoutAdapter.getImageButtonAdapter(i*9+j);
        if(imageButtonAdapter!=lastCentralImageButtonAdapter){
            imageButtonAdapter.popUp();
        }
        lastCentralImageButtonAdapter=imageButtonAdapter;


    }
}
