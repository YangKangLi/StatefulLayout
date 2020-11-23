package com.github.yangkangli.x.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.github.yangkangli.x.ui.R;
import com.github.yangkangli.x.ui.StatefulLayout;

public class DefaultLoadingView extends BaseStateView {

    public DefaultLoadingView(Context context, StatefulLayout statefulLayout) {
        super(context, statefulLayout);
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
