package com.example.marcos.mygridlayoutexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    PressCommand pressCommand;
    ValueChangeEvaluator valueChangeEvaluator;
    private SudokuGame sudokuGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SingleContextContainer.setContext(this);
        //make mydibujadorcelda
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        SingleMainGridLayoutContainer.setGridLayout(gridLayout);
        MyDibujadorCelda myDibujadorCelda=new MyDibujadorCelda(getApplicationContext(),gridLayout);
        MyDibujadorCelda2 myDibujadorCelda2=new MyDibujadorCelda2(getApplicationContext(),gridLayout);
        MyDibujadorCelda3 myDibujadorCelda3=new MyDibujadorCelda3(getApplicationContext(),gridLayout);
        int rowCount=myDibujadorCelda.getRowCount();
        Log.i("infor","count:"+rowCount);


        DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
        int width=displayMetrics.widthPixels;
        //cambio tamano celdas
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageButton gridChild=(ImageButton) gridLayout.getChildAt(i);
            ViewGroup.LayoutParams layoutParams=gridChild.getLayoutParams();

            layoutParams.width=(int)(width/9);
            gridChild.setLayoutParams(layoutParams);
        }

        //make mydibujadosCeldas
        MyDibujadorCeldas myDibujadorCeldas=new MyDibujadorCeldasWAltColors(myDibujadorCelda3,myDibujadorCelda);
        MyDibujadorCeldas myDibujadorCeldas2=new MyDibujadorCeldas(myDibujadorCelda2);

        //myDibujadorCeldas.dibujarCruz(2,2);

        this.sudokuGame=SudokuGame.getInstance();
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
        if(v.getId()==R.id.imageButtonTres){
            Log.i("infor","imageButtonUno pressed");
            if(valueChangeEvaluator.evaluateChange(3)){
                valuable.setValues(3);
            }else{
                Toast.makeText(getApplicationContext(),"no se puede poner valor 3",Toast.LENGTH_LONG).show();
            }
        }
        if(v.getId()==R.id.imageButtonCuatro){
            Log.i("infor","imageButtonUno pressed");
            if(valueChangeEvaluator.evaluateChange(4)){
                valuable.setValues(4);
            }else{
                Toast.makeText(getApplicationContext(),"no se puede poner valor 4",Toast.LENGTH_LONG).show();
            }
        }

        if(v.getId()==R.id.imageButtonVacio){
            Log.i("infor","imageButtonVacio pressed");
            IPositionedValuableStack valuableStack=HistoricValuableStack.getInstance();
            IValuable valuable2=valuableStack.remove();
            if(valuable2!=null){
                valuable2.setValues(-1);
            }

        }

    }

    protected void onPause(){
        super.onPause();
        sudokuGame.saveState();
    }
}
