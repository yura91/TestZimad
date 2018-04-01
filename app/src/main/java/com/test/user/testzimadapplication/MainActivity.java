package com.test.user.testzimadapplication;

import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.roughike.bottombar.OnTabSelectListener;
import com.test.user.testzimadapplication.databinding.ActivityMainBinding;
import com.test.user.testzimadapplication.fragments.CatsFragment;
import com.test.user.testzimadapplication.fragments.ContentFragment;
import com.test.user.testzimadapplication.fragments.DogsFragment;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding dataBinding;
    private OnTabSelectListener selectedListener;
    private static Parcelable mListStateCats;
    private static Parcelable mListStateDogs;


    public Parcelable getmListStateDogs() {
        return mListStateDogs;
    }

    public void setmListStateDogs(Parcelable mListStateDogs) {
        this.mListStateDogs = mListStateDogs;
    }


    public Parcelable getmListStateCats() {
        return mListStateCats;
    }

    public void setmListStateCats(Parcelable mListStateCats) {
        this.mListStateCats = mListStateCats;
    }

    {
        selectedListener = new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_cats:
                        showFragment(CatsFragment.TAG);
                        break;

                    case R.id.tab_dogs:
                        showFragment(DogsFragment.TAG);
                        break;
                }
            }
        };
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataBinding.setPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataBinding.bottomBar.setDefaultTab(R.id.tab_cats);
        dataBinding.bottomBar.setOnTabSelectListener(selectedListener);
    }

    public  void setVisiblityBottomBar(int visiblity) {
        dataBinding.bottomBar.setVisibility(visiblity);
    }

    private void showFragment(String tag) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment newFragment = null;
            Fragment cachedFragment = null;
            switch (tag) {
                case CatsFragment.TAG:
                    cachedFragment = getSupportFragmentManager().findFragmentByTag(tag);
                    if (cachedFragment == null) {
                        newFragment = new CatsFragment();
                    }
                    break;

                case DogsFragment.TAG:
                    cachedFragment = getSupportFragmentManager().findFragmentByTag(tag);
                    if (cachedFragment == null) {
                        newFragment = new DogsFragment();
                    }
                    break;

            }
        if (newFragment != null) {
            try {
                transaction
                        .replace(R.id.contentContainer, newFragment, tag)
                        .commit();
            } catch (IllegalStateException e) {
                // catchIllegalStateException(e, tag);
            }
        }

        }

    public void showContentFragment(String tag, String url, String text) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment newFragment = null;
        switch (tag) {
            case ContentFragment.TAG:
                newFragment = new ContentFragment();
                Bundle arguments = new Bundle();
                arguments.putString( "url" , url);
                arguments.putString( "tittle" , text);
                newFragment.setArguments(arguments);
                try {
                    transaction
                            .replace(R.id.contentContainer,newFragment, tag)
                            .addToBackStack(null)
                            .commit();
                } catch (IllegalStateException e) {
                    // catchIllegalStateException(e, tag);
                }
                break;
        }
    }

}


    //}

