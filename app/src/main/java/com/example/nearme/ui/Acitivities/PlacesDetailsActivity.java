package com.example.nearme.ui.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nearme.ListViewHelper;
import com.example.nearme.R;
import com.example.nearme.addcontrollers.BannerAddController;
import com.example.nearme.ui.Acitivities.objects.ReviewObject;
import com.example.nearme.network.ApiRequest;
import com.example.nearme.network.ApiRequestInterface;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlacesDetailsActivity extends AppCompatActivity implements ApiRequestInterface.View {
    private WebView webview;
    private TextView title;
    private TextView txtAddress,phone,txtwebsite;
    private ImageView pic;
    private Button showInMap;
    private RatingBar ratingBar;
    private ListView reviewlist;
    private ArrayList<String> photoReferences=new ArrayList<>();
    private ArrayList<ReviewObject> reviewObjectsList=new ArrayList<>();
    private ProgressBar progressBar;
    private BannerAddController bannerAddController;
    private AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_details);
        String placeId=getIntent().getExtras().getString("placeid");
        String url="https://maps.googleapis.com/maps/api/place/details/json?place_id="+placeId+"&key=AIzaSyCqEEDdypCvLeSVWqN2JGlQ2pMvCCQKG24";
        Log.e("url",url);

        pic=findViewById(R.id.pic);
        title=findViewById(R.id.title);
        txtAddress=findViewById(R.id.address);
        phone=findViewById(R.id.phone);
        txtwebsite=findViewById(R.id.website);
        ratingBar=findViewById(R.id.rating);
        showInMap=findViewById(R.id.showinmap);
        reviewlist=findViewById(R.id.reviewlist);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        mAdView = findViewById(R.id.adView);
        bannerAddController=new BannerAddController(getApplicationContext(),mAdView);
        bannerAddController.LoadBannerAdd();

        ApiRequest apiRequest=new ApiRequest(getApplicationContext(),this);
        apiRequest.ApiCallStringRequest(url);
    }

    @Override
    public void apiRequestOnSuccess(String response) throws JSONException {
        JSONObject jsonObject=new JSONObject(response);

        JSONObject jsonObject1=jsonObject.getJSONObject("result");

        JSONObject jsonObject2=jsonObject1.getJSONObject("geometry");
        JSONObject jsonObject3=jsonObject2.getJSONObject("location");
        Double lat=jsonObject3.getDouble("lat");
        Double lon= jsonObject3.getDouble("lng");

        String address=jsonObject1.optString("formatted_address");
        String phoneNumber=jsonObject1.optString("formatted_phone_number");
        String name=jsonObject1.getString("name");
        Double rating=jsonObject1.optDouble("rating");
        String url=jsonObject1.optString("url");
        String website=jsonObject1.optString("website");

        JSONArray photos=jsonObject1.optJSONArray("photos");


        for(int i = 0; i< (photos != null ? photos.length() : 0); i++){
            JSONObject jsonObject4=photos.getJSONObject(i);
            String photoReference=jsonObject4.getString("photo_reference");
            photoReferences.add(photoReference);
        }


        JSONArray review=jsonObject1.optJSONArray("reviews");

        for(int i=0;i< (review != null ? review.length() : 0);i++){
            JSONObject jsonObject4=review.getJSONObject(i);
            ReviewObject reviewObject=new ReviewObject();

            reviewObject.setAuthorName(jsonObject4.getString("author_name"));
            reviewObject.setProfilePhoto(jsonObject4.getString("profile_photo_url"));
            reviewObject.setComment(jsonObject4.getString("text"));
            reviewObject.setRating(jsonObject4.getDouble("rating"));
            reviewObject.setTimeDescuription(jsonObject4.getString("relative_time_description"));

            reviewObjectsList.add(reviewObject);
        }

        if(photoReferences.size()>0) {
            String picUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photoReferences.get(0) + "&key=" + getString(R.string.APIKEY);
            Glide.with(this).load(picUrl).into(pic);
        }
        ReviewAdapter reviewAdapter=new ReviewAdapter(this,reviewObjectsList);
        reviewlist.setAdapter(reviewAdapter);
        ListViewHelper.getListViewSize(reviewlist);

        title.setText(name);
        txtAddress.setText(address);
        ratingBar.setRating((float) Double.parseDouble(String.valueOf(rating)));
        phone.setText(phoneNumber);
        txtwebsite.setText(website);


        showInMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MapWebViewActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        txtwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse(website));

                startActivity(httpIntent);

            }
        });
        if(!txtwebsite.getText().toString().equals(""))
        {
            txtwebsite.append(" (Click)");
        }

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void apiRequestOnFailure(String error) {

    }
}