package com.leia.material;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mikepenz.materialdrawer.Drawer;

/*
    Author:leia
    Write The Code Change The World    
*/public class HomeFragment extends Fragment {
    private  static  final String KEY_TITLE="Home";
    private Drawer result;

    public HomeFragment() {

    }
    public  static HomeFragment newInstance(String title){
        HomeFragment f=new HomeFragment();
        Bundle args=new Bundle();
        args.putString(KEY_TITLE,title);

        return (f);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_home, container, false);
        return view;
        /**
         * View view =inflater.inflate(R.layout.fragment_home,container,false);
         *  return view;
         */
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState=result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}
