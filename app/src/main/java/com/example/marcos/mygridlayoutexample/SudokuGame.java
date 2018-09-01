package com.example.marcos.mygridlayoutexample;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SudokuGame{
    private String JSONFileNAme;
    private int number;
    private IPositionedValuableStack positionedValuableStack;
    private static SudokuGame sudokuGame;

    private SudokuGame(String JSONFileNAme){
        this.JSONFileNAme=JSONFileNAme;
        GridLayoutValueSetter gridLayoutValueSetter=GridLayoutValueSetter.getInstance();
        this.positionedValuableStack=HistoricValuableStack.getInstance();

        String JSONString=SingleContextContainer.getContext().getSharedPreferences("sudokuGame",0).getString(JSONFileNAme,"{\"number\":0,\"valoresIngresados\":{}}");
            try {
                JSONObject jsonObject=new JSONObject(JSONString);
                this.number=jsonObject.getInt("number");
                JSONObject JsonVI=jsonObject.getJSONObject("valoresIngresados");

                Iterator<String> claves=JsonVI.keys();
                while(claves.hasNext()){
                    String clave=claves.next();


                    gridLayoutValueSetter.select(Integer.parseInt(clave));
                    gridLayoutValueSetter.setValues(JsonVI.getInt(clave));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }




    }

    public static synchronized SudokuGame getInstance(){
        if(sudokuGame==null){
            sudokuGame=new SudokuGame("sudokuGameJSON");
        }
        return sudokuGame;
    }

    public int getNumber(){
        return this.number;
    }


    private JSONObject toJSONObject(){
        JSONObject jsonObject=new JSONObject();
        JSONObject JSONValoresIngresados=valoresIngresados();
        try {
            jsonObject.put("number",number);
            jsonObject.put("valoresIngresados",JSONValoresIngresados);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private JSONObject valoresIngresados(){
        PositionedValuable positionedValuable;
        JSONObject jsonObject=new JSONObject();
        List<PositionedValuable> positionedValuables=new ArrayList<>();

        while((positionedValuable=positionedValuableStack.remove())!=null){
            positionedValuables.add(positionedValuable);

        }
        Collections.reverse(positionedValuables);
        for(PositionedValuable positionedValuable2: positionedValuables){
            try {
                jsonObject.put(String.valueOf(positionedValuable2.getPosition()),positionedValuable2.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public void saveState(){
        SharedPreferences sharedPreferences=SingleContextContainer.getContext().getSharedPreferences("sudokuGame",0);
        sharedPreferences.edit().putString(JSONFileNAme,toJSONObject().toString()).apply();
    }
}
