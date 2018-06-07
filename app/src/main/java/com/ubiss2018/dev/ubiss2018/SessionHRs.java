package com.ubiss2018.dev.ubiss2018;

import android.util.Log;

import com.google.gson.JsonIOException;

import org.json.JSONException;
import org.json.JSONObject;

public class SessionHRs {

  public int  getLowestHR(JSONObject hr){

      int minValue=0;

 try {

     JSONObject lowestHR = hr.getJSONObject("activities-heart-intraday");

      minValue = lowestHR.getJSONArray("dataset").getJSONObject(0).getInt("value");

  for(int i=0;i<lowestHR.getJSONArray("dataset").length();i++){


      lowestHR.getJSONArray("dataset").getJSONObject(i).getInt("value");


      if(lowestHR.getJSONArray("dataset").getJSONObject(i).getInt("value") < minValue){
          minValue = lowestHR.getJSONArray("dataset").getJSONObject(i).getInt("value");
      }



  }



 }catch (JSONException e) {
     e.printStackTrace();
 }


      return minValue;

  }




    public int  getHighestHR(JSONObject hr){

        int maxValue=0;

        try {

            JSONObject maxHR = hr.getJSONObject("activities-heart-intraday");

            maxValue = maxHR.getJSONArray("dataset").getJSONObject(0).getInt("value");

            for(int i=0;i<maxHR.getJSONArray("dataset").length();i++){


                maxHR.getJSONArray("dataset").getJSONObject(i).getInt("value");


                if(maxHR.getJSONArray("dataset").getJSONObject(i).getInt("value") > maxValue){
                    maxValue = maxHR.getJSONArray("dataset").getJSONObject(i).getInt("value");
                }



            }



        }catch (JSONException e) {
            e.printStackTrace();
        }


        return maxValue;

    }



}
