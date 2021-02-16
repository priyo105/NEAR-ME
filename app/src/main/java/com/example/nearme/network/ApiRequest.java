package com.example.nearme.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest {

    private RequestQueue requestQueue;
    private StringRequest request;
    private Context c;
    private ApiRequestInterface.View view;


    public ApiRequest(Context c, ApiRequestInterface.View view) {
        requestQueue = Volley.newRequestQueue(c.getApplicationContext());
        this.c = c;
        this.view = view;
    }

    public void ApiCallStringRequest(String Url){
        Log.e("asdas",Url);
        request = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    view.apiRequestOnSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.apiRequestOnFailure(error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                return hashMap;
            }
        };

        requestQueue.add(request);


    }
}
