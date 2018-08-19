package com.example.marcos.mygridlayoutexample;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SudokuGame {
    private String JSONFileNAme;
    private int number;
    private Map<String,Integer> valoresIngresados;

    public SudokuGame(String JSONFileNAme){
        this.JSONFileNAme=JSONFileNAme;

        String JSONString=SingleContextContainer.getContext().getSharedPreferences("sudokuGame",0).getString(JSONFileNAme,"{\"number\":0,\"valoresIngresados\":{}}");
            try {
                JSONObject jsonObject=new JSONObject(JSONString);
                this.number=jsonObject.getInt("number");
                JSONObject JsonVI=jsonObject.getJSONObject("valoresIngresados");
                valoresIngresados=new HashMap<>();
                Iterator<String> claves=JsonVI.keys();
                while(claves.hasNext()){
                    String clave=claves.next();
                    valoresIngresados.put(clave,JsonVI.getInt(clave));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }




    }

    public int getNumber(){
        return this.number;
    }
    public Map<String,Integer> getValoresIngresados(){
        return this.valoresIngresados;
    }

    public void addValorIngresado(String pos,Integer valor){
        valoresIngresados.put(pos,valor);
    }

    private JSONObject toJSONObject(){
        JSONObject jsonObject=new JSONObject();
        JSONObject JSONValoresIngresados=new JSONObject(valoresIngresados);
        try {
            jsonObject.put("number",number);
            jsonObject.put("valoresIngresados",JSONValoresIngresados);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void saveState(){
        SharedPreferences sharedPreferences=SingleContextContainer.getContext().getSharedPreferences("sudokuGame",0);
        sharedPreferences.edit().putString(JSONFileNAme,toJSONObject().toString()).apply();
    }
}
