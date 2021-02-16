package com.example.nearme.ui.store;

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

public class StoreFragment extends BaseFragment implements LocationUpdateUiContract.View {

    private TextView textView;
    private CardView pharmacycard,supermarket,laundry, bookCard, mosque,bakery;
    private Double lat,lon;
    private BannerAddController bannerAddController;
    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        PermissionManagers permissionManagers = new PermissionManagers(getActivity());
        permissionManagers.checkLocationPermission();

        //Use Location Helper to get location Update.... Location Updated Ui contract updates the UI !!!
        LocationHelper locationHelper=new LocationHelper(getActivity(),this);
        locationHelper.startLocationUpdates();


        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        textView = root.findViewById(R.id.text_home);
        mAdView = root.findViewById(R.id.adView);
        bannerAddController=new BannerAddController(getContext(),mAdView);
        bannerAddController.LoadBannerAdd();

        pharmacycard=root.findViewById(R.id.pharmacycard);
        supermarket=root.findViewById(R.id.supermarket);
        bookCard =root.findViewById(R.id.electronic);
        mosque =root.findViewById(R.id.mosque);
        laundry=root.findViewById(R.id.laundry);
        bakery=root.findViewById(R.id.bakery);
        initViewListeners();
        return root;
    }




    void initViewListeners(){

        pharmacycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","pharmacy");
                intent.putExtra("keyword","pharmacy");
                startActivity(intent);

            }
        });


        supermarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","supermarket");
                intent.putExtra("keyword","supermarket");
                startActivity(intent);

            }
        });


        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","laundry");
                intent.putExtra("keyword","laundry");
                startActivity(intent);

            }
        });

        bakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","bakery");
                intent.putExtra("keyword","bakery");
                startActivity(intent);

            }
        });


        bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","book_store");
                intent.putExtra("keyword","book_store");
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