package com.lege.android.base.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lege.android.base.log.APPLog;

import static com.lege.android.base.ui.BaseActivity.BASE_ACTIVITY_TAG;

/**
 * Description:
 * Created by loctek on 2020/6/18.
 */
public abstract class BaseFragment extends Fragment {
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APPLog.log(BASE_ACTIVITY_TAG,getClass().getSimpleName()+"    onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        APPLog.log(BASE_ACTIVITY_TAG,getClass().getSimpleName()+"    onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        APPLog.log(BASE_ACTIVITY_TAG,getClass().getSimpleName()+"    onStart");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        APPLog.log(BASE_ACTIVITY_TAG,getClass().getSimpleName()+"    onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //APPLog.log(BASE_ACTIVITY_TAG,getClass().getSimpleName()+"    onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        APPLog.log(BASE_ACTIVITY_TAG,getClass().getSimpleName()+"    onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        APPLog.log(BASE_ACTIVITY_TAG,getClass().getSimpleName()+"    onDestroyView");
    }

    @Override
    public void onStop() {
        super.onStop();
        APPLog.log(BASE_ACTIVITY_TAG,getClass().getSimpleName()+"    onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        APPLog.log(BASE_ACTIVITY_TAG,getClass().getSimpleName()+"    onDestroy");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    private Fragment currentFrg;
    protected void showFragment(int id, Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (currentFrg != fragment) {
            if (currentFrg != null) {
                ft.hide(currentFrg);
            }
            if (fragment.isAdded()) {
                ft.show(fragment).commit();
            } else {
                ft.add(id, fragment).show(fragment).commit();
            }
            currentFrg = fragment;
        }
    }
}
