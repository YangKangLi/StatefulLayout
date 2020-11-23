package com.github.yangkangli.sample.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.github.yangkangli.sample.R;
import com.github.yangkangli.x.ui.StatefulLayout;
import com.github.yangkangli.x.ui.interf.IStateView;

public class CommonLoadingView implements IStateView {

    private Context context;

    private View rootView;

    private StatefulLayout statefulLayout;

    public CommonLoadingView(Context context, StatefulLayout statefulLayout) {
        this.context = context;
        this.statefulLayout = statefulLayout;
    }

    @Override
    public View onGetView() {
        if (rootView == null) {
            rootView = LayoutInflater.from(context).inflate(R.layout.default_loading_layout, null);
        }
        return rootView;
    }

    @Override
    public void onVisible(boolean visible) {

    }
}
