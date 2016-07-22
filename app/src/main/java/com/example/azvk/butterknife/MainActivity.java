package com.example.azvk.butterknife;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.azvk.butterknife.Fragments.ButtonsFragment;
import com.example.azvk.butterknife.Fragments.RecycleViewFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) private Toolbar toolbar;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (savedInstanceState == null){
            Log.i(TAG, "savedInstanceState == null");
            fragmentTransaction
                    .replace(R.id.buttonFrame, new ButtonsFragment(), "buttonFragment")
                    .replace(R.id.recycleViewFrame, new RecycleViewFragment(), "recycleViewFragment")
                    .commit();
        }
        else{
            Log.i(TAG, "savedInstanceState NOT null");
            ButtonsFragment buttonsFragment = (ButtonsFragment) fragmentManager.findFragmentByTag("buttonFragment");
            RecycleViewFragment recycleViewFragment = (RecycleViewFragment) fragmentManager.findFragmentByTag("recycleViewFragment");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy called");
    }
}