package com.example.nearme.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nearme.LocationHelpers.PermissionManagers;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionManagers permissionManagers=new PermissionManagers(getActivity());
        permissionManagers.promptGPS();
    }



}
