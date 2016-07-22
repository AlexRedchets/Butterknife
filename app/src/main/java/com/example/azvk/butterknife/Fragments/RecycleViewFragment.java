package com.example.azvk.butterknife.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.azvk.butterknife.Adapters.RecycleViewAdapter;
import com.example.azvk.butterknife.EventList;
import com.example.azvk.butterknife.Models.Player;
import com.example.azvk.butterknife.MyPresenter;
import com.example.azvk.butterknife.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewFragment extends Fragment{

    @BindView(R.id.recycleView) private RecyclerView recyclerView;

    private static final String TAG = RecycleViewFragment.class.getSimpleName();
    private RecycleViewAdapter adapter;

    private static MyPresenter presenter;
    private static boolean isGetButtonClicked = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView called");
        View view = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated called");
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(getActivity()) ;
        recyclerView.setAdapter(adapter);

        if (isGetButtonClicked){
            Log.i(TAG, "GetButtonClicked");
            presenter = new MyPresenter();
            presenter.onGetView(this);
        }
    }

    @Override
    public void onStart() {
        Log.i(TAG, "onStart called");
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop called");
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void setView(List<Player> playerList){
        Log.i(TAG, "setView called");
        adapter.updateAdapter(playerList);
    }

    @Subscribe
    public void onEvent(EventList eventList){
        switch (eventList.getResultCode()){
            case 111:
                Log.i(TAG, "GET click received");
                isGetButtonClicked = true;
                presenter = new MyPresenter();
                presenter.onGetView(this);

                break;
            case 222:
                Log.i(TAG, "CLEAR click received");
                isGetButtonClicked = false;
                presenter = null;
                adapter.clearAll();
                break;
        }
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy called");
        super.onDestroy();
        if (presenter != null) {
            Log.i(TAG, "onDestroy presenter.onGetView(null)");
            presenter.onGetView(null);
        }
        if (!getActivity().isChangingConfigurations()){
            Log.i(TAG, "isChangingConfigurations false");
            presenter = null;
        }
    }

}