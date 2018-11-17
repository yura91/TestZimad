package com.test.user.testzimadapplication.fragments;

import android.os.Bundle;
import android.util.Log;

import com.test.user.testzimadapplication.MainActivity;
import com.test.user.testzimadapplication.RetrofitInstance;
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

public class DogsFragment extends BaseFragment {
  public static final String TAG = "DogsFragment";
  private String LIST_STATE_KEY = "mListState";

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    ((MainActivity) getActivity()).setmListStateDogs(getLayoutManager().onSaveInstanceState());
    outState.putParcelable(LIST_STATE_KEY, getLayoutManager().onSaveInstanceState());
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
    getTittle().setText("DogsFragment");
    GetCatDogService service = RetrofitInstance.getRetrofitInstance().create(GetCatDogService.class);
    Call<ApiResponse> call = service.getCatDogData("dog");
    MainActivity.setTag(TAG);
    call.enqueue(new Callback<ApiResponse>() {
      @Override
      public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
        generateEmployeeList((ArrayList<Data>) response.body().getData(), TAG);
        Log.wtf("URL Called", call.request().url() + "");
      }

      @Override
      public void onFailure(Call<ApiResponse> call, Throwable t) {
        Log.wtf("URL Called", call.request().url() + "");
      }
    });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ((MainActivity) getActivity()).setmListStateDogs(getLayoutManager().onSaveInstanceState());
  }
  //endregion

}
