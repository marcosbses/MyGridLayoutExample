package com.example.marcos.mygridlayoutexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    PressCommand pressCommand;
    ValueChangeEvaluator valueChangeEvaluator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SingleContextContainer.setContext(getApplicationContext());
        //make mydibujadorcelda
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        SingleMainGridLayoutContainer.setGridLayout(gridLayout);
        MyDibujadorCelda myDibujadorCelda=new MyDibujadorCelda(getApplicationContext(),gridLayout);
        MyDibujadorCelda2 myDibujadorCelda2=new MyDibujadorCelda2(getApplicationContext(),gridLayout);
        int rowCount=myDibujadorCelda.getRowCount();
        Log.i("infor","count:"+rowCount);


        //make mydibujadosCeldas
        MyDibujadorCeldas myDibujadorCeldas=new MyDibujadorCeldas(myDibujadorCelda);
        MyDibujadorCeldas myDibujadorCeldas2=new MyDibujadorCeldas(myDibujadorCelda2);

        //myDibujadorCeldas.dibujarCruz(2,2);

        CeldasDibujadas celdasDibujadas=new CeldasDibujadas(myDibujadorCeldas,myDibujadorCeldas2);

        pressCommand=new PressCommand(celdasDibujadas,gridLayout);
        valueChangeEvaluator=ValueChangeEvaluator.getInstance();

    }

    public void presionado(View v){
        pressCommand.execute(v);
    }

    public void numeroPresionado(View v){
        IValuable valuable=ValuableSelector.getInstance();
        if(v.getId()==R.id.imageButtonUno){
            Log.i("infor","imageButtonUno pressed");
            //eval
            if(valueChangeEvaluator.evaluateChange(1)){
                valuable.setValues(1);
            }else{
                Toast.makeText(getApplicationContext(),"no se puede poner valor 1",Toast.LENGTH_LONG).show();
            }

        }
        if(v.getId()==R.id.imageButtonDos){
            Log.i("infor","imageButtonUno pressed");
            if(valueChangeEvaluator.evaluateChange(2)){
                valuable.setValues(2);
            }else{
                Toast.makeText(getApplicationContext(),"no se puede poner valor 2",Toast.LENGTH_LONG).show();
            }
        }
    }
}