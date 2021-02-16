package com.example.nearme.ui.Acitivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nearme.R;
import com.example.nearme.ui.Acitivities.objects.GoogleApiObject;
import com.example.nearme.ui.Acitivities.objects.ReviewObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends BaseAdapter {

    private Context c;
    private final List<ReviewObject> reviewObjects;

    public ReviewAdapter(Context c, List<ReviewObject> reviewObjects) {
        this.c = c;
        this.reviewObjects = reviewObjects;
    }

    @Override
    public int getCount() {
        return reviewObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.review_layout, parent,false);

        TextView name,comment,timestamp;
        CircleImageView circleImageView=convertView.findViewById(R.id.profilephoto);

        name=convertView.findViewById(R.id.name);
        comment=convertView.findViewById(R.id.comment);
        timestamp=convertView.findViewById(R.id.timestamp);
//        vicinity=convertView.findViewById(R.id.vicinity);
        name.setText(reviewObjects.get(position).getAuthorName());
        comment.setText(reviewObjects.get(position).getComment());
        timestamp.setText(reviewObjects.get(position).getTimeDescuription());
//        vicinity.setText(googleApiObjects.get(position).getVicinity());
        Glide.with(c).load(reviewObjects.get(position).getProfilePhoto()).into(circleImageView);


        return convertView;
    }
}
