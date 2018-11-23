package com.test.user.testzimadapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.user.testzimadapplication.MainActivity;
import com.test.user.testzimadapplication.R;
import com.test.user.testzimadapplication.adapter.CatsDogsAdapter;
import com.test.user.testzimadapplication.model.Data;

import java.util.ArrayList;


public class BaseFragment extends Fragment {
    private CatsDogsAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    TextView tittle;
    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public TextView getTittle() {
        return tittle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        recyclerView = rootView.findViewById(R.id.cats_list);
        AppBarLayout appBarLayout = rootView.findViewById(R.id.includedToolbar);
        Toolbar toolbar = appBarLayout.findViewById(R.id.toolbar);
        tittle = appBarLayout.findViewById(R.id.toolbarTitleTextView);
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
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
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    protected void generateEmployeeList(ArrayList<Data> dataList, String tag) {
      if (getActivity() != null) {
      if (tag.equals(CatsFragment.TAG)) {
        getLayoutManager().onRestoreInstanceState(((MainActivity) getActivity()).getmListStateCats());
      } else {
        getLayoutManager().onRestoreInstanceState(((MainActivity) getActivity()).getmListStateDogs());
      }

      }

        adapter = new CatsDogsAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    protected void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }
}
