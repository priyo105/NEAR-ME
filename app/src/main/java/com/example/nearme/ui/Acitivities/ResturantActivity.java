package com.example.nearme.ui.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.nearme.R;
import com.example.nearme.addcontrollers.BannerAddController;
import com.example.nearme.ui.Acitivities.objects.GoogleApiObject;
import com.example.nearme.network.ApiRequest;
import com.example.nearme.network.ApiRequestInterface;
import com.example.nearme.ui.home.HomeFragment;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResturantActivity extends AppCompatActivity implements ApiRequestInterface.View{
    private ListView listview;
    private Double lat,lon;
    ArrayList<GoogleApiObject> googleApiObjects=new ArrayList<>();
    private int counter=0;
    GeneralPlaceAdapter generalPlaceAdapter;
    String keyword,type;
    ProgressBar progressBar;
    private BannerAddController bannerAddController;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant);
        listview=findViewById(R.id.listview);
        lat=getIntent().getExtras().getDouble("lat");
        lon=getIntent().getExtras().getDouble("lon");
        mAdView = findViewById(R.id.adView);
        bannerAddController=new BannerAddController(getApplicationContext(),mAdView);
        bannerAddController.LoadBannerAdd();

        if(lat==0.0){
           Intent intent=new Intent(this,LocationExceptionActivity.class);
           startActivity(intent);
        }

        type=getIntent().getExtras().getString("type");
        keyword=getIntent().getExtras().getString("keyword");
        String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lon+"&keyword="+keyword+"&rankby=distance&types="+type+"&rankby=distance&key=AIzaSyCqEEDdypCvLeSVWqN2JGlQ2pMvCCQKG24&fbclid=IwAR1q9S0-qvMNmxi51s1Klw95JV6XXmam_oxaZy6XFWD9zsKFB5mB9Hqk7x4";


         progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new ChasingDots();
        progressBar.setIndeterminateDrawable(doubleBounce);

        //Api Call example !!! process in the On Api Success Class !!!
        ApiRequest apiRequest=new ApiRequest(getApplicationContext(),this);
        apiRequest.ApiCallStringRequest(url);


    }

    void NextTokenApiCall(String nextpageToken){
        if(!nextpageToken.equals("")) {
            String Url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=23.8055886,90.3808578&keyword=" + keyword + "&rankby=distance&types=" + type + "&rankby=distance&key=AIzaSyCqEEDdypCvLeSVWqN2JGlQ2pMvCCQKG24&fbclid=IwAR1q9S0-qvMNmxi51s1Klw95JV6XXmam_oxaZy6XFWD9zsKFB5mB9Hqk7x4&pagetoken=" + nextpageToken;
            ApiRequest apiRequest = new ApiRequest(getApplicationContext(), this);
            apiRequest.ApiCallStringRequest(Url);
        }else {
        progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void  apiRequestOnSuccess(String response) throws JSONException {
        JSONObject jsonObject=new JSONObject(response);
        String placeId = "";
        JSONArray jsonArray=jsonObject.getJSONArray("results");
        String nextpageToken=jsonObject.optString("next_page_token");
        String status=jsonObject.optString("status");
        for(int i=0;i<jsonArray.length();i++){

            JSONObject jsonObject1=jsonArray.getJSONObject(i);
            JSONObject jsonObject2=jsonObject1.getJSONObject("geometry");
            JSONObject jsonObject3=jsonObject2.getJSONObject("location");
            Double lat=jsonObject3.getDouble("lat");
            Double lon= jsonObject3.getDouble("lng");

            String name=jsonObject1.getString("name");
            placeId=jsonObject1.getString("place_id");
            String reference=jsonObject1.getString("reference");
            String vicinity=jsonObject1.getString("vicinity");

//            JSONArray photoArray=jsonObject1.optJSONArray("photos");
//            JSONObject jsonObject4=photoArray.optJSONObject(0);
//            String photoReference=jsonObject4.getString("photo_reference");
            GoogleApiObject googleApiObject=new GoogleApiObject(lat,lon,name,"",placeId,reference,vicinity);
            googleApiObjects.add(googleApiObject);

        }


        if(counter<=1){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
                    Sprite doubleBounce = new DoubleBounce();
                    progressBar.setIndeterminateDrawable(doubleBounce);

                    NextTokenApiCall(nextpageToken);
                    counter++;
                }
            }, 1500);

        }

        generalPlaceAdapter=new GeneralPlaceAdapter(getApplicationContext(),googleApiObjects);
        listview.setAdapter(generalPlaceAdapter);
        generalPlaceAdapter.notifyDataSetChanged();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),PlacesDetailsActivity.class);
                intent.putExtra("placeid", googleApiObjects.get(position).getPlaceId());
                startActivity(intent);
            }
        });
        generalPlaceAdapter.notifyDataSetChanged();

    }



    @Override
    public void apiRequestOnFailure(String error) {

    }
}