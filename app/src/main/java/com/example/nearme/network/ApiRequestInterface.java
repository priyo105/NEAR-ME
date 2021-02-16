package com.example.nearme.network;

import org.json.JSONException;

public interface ApiRequestInterface {

    interface View{
        void apiRequestOnSuccess(String response) throws JSONException;
        void apiRequestOnFailure(String error);
    }

}
