package com.example.azvk.butterknife.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.azvk.butterknife.EventList;
import com.example.azvk.butterknife.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ButtonsFragment extends Fragment {

    private static final String TAG = ButtonsFragment.class.getSimpleName();
    //@BindView(R.id.get_button) Button get_button;
    //@BindView(R.id.clear_button) Button clear_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buttons, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.get_button)
    private void onGetButtonClicked(View view) {

        Log.i(TAG, "onGetButtonClicked");

        EventList eventList = new EventList();
        eventList.setResultCode(111);
        EventBus.getDefault().post(eventList);
    }

    @OnClick(R.id.clear_button)
    private void onClearButtonClicked(View view) {

        Log.i(TAG, "onClearButtonClicked");

        EventList eventList = new EventList();
        eventList.setResultCode(222);
        EventBus.getDefault().post(eventList);
    }
}