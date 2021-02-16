package com.example.nearme.LocationHelpers;

import android.location.Location;

public interface LocationUpdateUiContract {

     interface  View{
        void setViewAfterLocationUpdates(Location location);
    }


}
