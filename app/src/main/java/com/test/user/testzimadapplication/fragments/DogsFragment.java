package com.test.user.testzimadapplication.fragments;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class DogsFragment extends Fragment {
    public static final String TAG = "DogsFragment";
    private CatsDogsAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String LIST_STATE_KEY = "mListState";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dogs, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.dogs_list);
        layoutManager = new LinearLayoutManager(getContext());
        AppBarLayout appBarLayout = (AppBarLayout) rootView.findViewById(R.id.includedToolbar);
        Toolbar toolbar = (Toolbar) appBarLayout.findViewById(R.id.toolbar);
        TextView tittle = (TextView) appBarLayout.findViewById(R.id.toolbarTitleTextView);
        tittle.setText("Dog Fragment");
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getActivity().finish();
                        return true;
                    }
                }
                return false;
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((MainActivity) getActivity()).setmListStateDogs(layoutManager.onSaveInstanceState());
        outState.putParcelable(LIST_STATE_KEY, layoutManager.onSaveInstanceState());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            ((MainActivity) getActivity()).setmListStateDogs(savedInstanceState.getParcelable(LIST_STATE_KEY));
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        GetCatDogService service = RetrofitInstance.getRetrofitInstance().create(GetCatDogService.class);
        Call<ApiResponse> call = service.getCatDogData("dog");

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                generateEmployeeList((ArrayList<Data>) response.body().getData());
                Log.wtf("URL Called", call.request().url() + "");
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.wtf("URL Called", call.request().url() + "");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((MainActivity) getActivity()).getmListStateDogs() != null) {
            layoutManager.onRestoreInstanceState(((MainActivity) getActivity()).getmListStateDogs());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
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
        ((MainActivity) getActivity()).setmListStateDogs(layoutManager.onSaveInstanceState());
    }
    private void generateEmployeeList(ArrayList<Data> dataList) {


        adapter = new CatsDogsAdapter(dataList);

        recyclerView.setAdapter(adapter);
    }
    //endregion

}
