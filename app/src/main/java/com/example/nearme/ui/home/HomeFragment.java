package com.example.nearme.ui.home;
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

public class HomeFragment extends BaseFragment implements LocationUpdateUiContract.View {

    private TextView textView;
    private CardView resturantcard,cafecard,atmcard,hospitalcard, mosque;
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


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);

        mAdView = root.findViewById(R.id.adView);
        bannerAddController=new BannerAddController(getContext(),mAdView);
        bannerAddController.LoadBannerAdd();

        resturantcard=root.findViewById(R.id.resturantcard);
        cafecard=root.findViewById(R.id.cafecard);
        hospitalcard=root.findViewById(R.id.hospitalcard);
        mosque =root.findViewById(R.id.mosque);
        atmcard=root.findViewById(R.id.atmcard);
        initViewListeners();
        return root;
    }




    void initViewListeners(){

        resturantcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","resturant");
                intent.putExtra("keyword","resturant");
                startActivity(intent);

            }
        });


        cafecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","cafe");
                intent.putExtra("keyword","cafe");
                startActivity(intent);

            }
        });


        atmcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","atm");
                intent.putExtra("keyword","atm");
                startActivity(intent);

            }
        });

        hospitalcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","hospital");
                intent.putExtra("keyword","hospital");
                startActivity(intent);

            }
        });


        mosque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ResturantActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type","mosque");
                intent.putExtra("keyword","mosque");
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