package com.example.nearme.ui.Acitivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nearme.R;
import com.example.nearme.ui.Acitivities.objects.GoogleApiObject;

import java.util.List;

public class GeneralPlaceAdapter extends BaseAdapter {

    private Context c;
    private List<GoogleApiObject> googleApiObjects;

    public GeneralPlaceAdapter(Context c, List<GoogleApiObject> googleApiObjects) {
        this.c = c;
        this.googleApiObjects = googleApiObjects;
    }

    @Override
    public int getCount() {
        return googleApiObjects.size();
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
        convertView = inflater.inflate(R.layout.adapterview, parent,false);

        TextView name,vicinity;

        name=convertView.findViewById(R.id.name);
//        vicinity=convertView.findViewById(R.id.vicinity);
        name.setText(googleApiObjects.get(position).getName());
//        vicinity.setText(googleApiObjects.get(position).getVicinity());


        return convertView;
    }
}
