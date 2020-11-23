package com.github.yangkangli.sample;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.yangkangli.sample.common.CommonStateCreator;
import com.github.yangkangli.x.ui.StatefulLayout;
import com.github.yangkangli.x.ui.interf.OnRetryListener;

public class MainActivity extends AppCompatActivity implements OnRetryListener {

    private StatefulLayout statefulLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statefulLayout = findViewById(R.id.stateful_layout);

        statefulLayout.setStateViewCreator(new CommonStateCreator());
        statefulLayout.setOnRetryListener(this);
    }
    @Override
    public void onRetry() {
        Log.d("Adam", "onRetry");
        statefulLayout.switchState(StatefulLayout.State.LOADING);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                statefulLayout.switchState(StatefulLayout.State.CONTENT);
            }
        }, 3000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_state_content:
                statefulLayout.switchState(StatefulLayout.State.CONTENT);
                break;
            case R.id.menu_state_ing:
                statefulLayout.switchState(StatefulLayout.State.LOADING);
                break;
            case R.id.menu_state_empty:
                statefulLayout.switchState(StatefulLayout.State.EMPTY);
                break;
            case R.id.menu_state_error:
                statefulLayout.switchState(StatefulLayout.State.ERROR);
                break;
            case R.id.menu_state_network_error:
                statefulLayout.switchState(StatefulLayout.State.NETWORK_ERROR);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
