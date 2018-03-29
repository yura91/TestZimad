package com.test.user.testzimadapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.user.testzimadapplication.MainActivity;
import com.test.user.testzimadapplication.R;
import com.test.user.testzimadapplication.RetrofitInstance;
import com.test.user.testzimadapplication.adapter.CatsDogsAdapter;
import com.test.user.testzimadapplication.model.ApiResponse;
import com.test.user.testzimadapplication.model.Data;
import com.test.user.testzimadapplication.network.GetCatDogService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 28.03.2018.
 */

public class ContentFragment extends Fragment{
    public static final String TAG = "ContentFragment";
    private String url;
    private String tittle;
    private ImageView image;
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        url = arguments.getString("url");
        tittle = arguments.getString("tittle");

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);
        image = rootView.findViewById(R.id.cat_image);
        text = rootView.findViewById(R.id.txt_cat_image);
        AppBarLayout appBarLayout = (AppBarLayout) rootView.findViewById(R.id.includedToolbar);
        Toolbar toolbar = (Toolbar) appBarLayout.findViewById(R.id.toolbar);
        TextView tittle = (TextView) appBarLayout.findViewById(R.id.toolbarTitleTextView);
        tittle.setText("Details");
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        ((MainActivity) getActivity()).setVisiblityBottomBar(View.GONE);
        return rootView;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart() {
        super.onStart();
        Glide.with(getActivity())
                .load(url)
                .centerCrop()
                .into(image);

        text.setText(tittle);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity) getActivity()).setVisiblityBottomBar(View.VISIBLE
        );
    }

}
