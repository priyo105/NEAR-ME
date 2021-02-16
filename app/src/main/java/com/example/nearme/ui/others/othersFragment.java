package com.example.nearme.ui.others;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.nearme.LocationHelpers.LocationHelper;
import com.example.nearme.LocationHelpers.LocationUpdateUiContract;
import com.example.nearme.LocationHelpers.PermissionManagers;
import com.example.nearme.R;
import com.example.nearme.addcontrollers.BannerAddController;
import com.example.nearme.base.BaseFragment;
import com.example.nearme.ui.Acitivities.ResturantActivity;
import com.google.android.gms.ads.AdView;

public class othersFragment extends BaseFragment implements LocationUpdateUiContract.View {

    private TextView textView;
    private CardView gymCard,bankcard,buscard, saloncard, library,bar;
    private Double lat,lon;
    private BannerAddController bannerAddController;
    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        PermissionManagers permissionManagers = new PermissionManagers(getActivity());
        permissionManagers.checkLocationPermission();

        //Use Location Helper to get location Update.... Location Updated Ui contract updates the UI !!!
        LocationHelper locationHelper = new LocationHelper(getActivity(), this);
        locationHelper.startLocationUpdates();


        View root = inflater.inflate(R.layout.fragment_others, container, false);
        textView = root.findViewById(R.id.text_home);
        mAdView = root.findViewById(R.id.adView);
        bannerAddController=new BannerAddController(getContext(),mAdView);
        bannerAddController.LoadBannerAdd();

        gymCard = root.findViewById(R.id.gymcard);
        bankcard = root.findViewById(R.id.bankcard);
        saloncard = root.findViewById(R.id.saloon);
        library = root.findViewById(R.id.library);
        buscard = root.findViewById(R.id.buscard);
        bar = root.findViewById(R.id.bar);
        initViewListeners();
        return root;

    }



    void initViewListeners(){

        gymCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","gym");
                intent.putExtra("keyword","gym");
                startActivity(intent);

            }
        });
        saloncard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","beauty_salon");
                intent.putExtra("keyword","salon");
                startActivity(intent);

            }
        });


        bankcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","bank");
                intent.putExtra("keyword","bank");
                startActivity(intent);

            }
        });


        buscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","bus_station");
                intent.putExtra("keyword","bus_station");
                startActivity(intent);

            }
        });

        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","bar");
                intent.putExtra("keyword","bar");
                startActivity(intent);

            }
        });


        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","library");
                intent.putExtra("keyword","library");
                startActivity(intent);

            }
        });

    }
    public void setViewAfterLocationUpdates(Location location){
        lat=location.getLatitude();
        lon=location.getLongitude();
        textView.setText(location.getLatitude()+" , "+location.getLongitude());
//        String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+location.getLatitude()+","+location.getLongitude()+"&radius=1000&types=food&key=AIzaSyCqEEDdypCvLeSVWqN2JGlQ2pMvCCQKG24&fbclid=IwAR1q9S0-qvMNmxi51s1Klw95JV6XXmam_oxaZy6XFWD9zsKFB5mB9Hqk7x4";
//        Log.e("url",url);
    }






}