package com.github.yangkangli.x.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.github.yangkangli.x.ui.R;
import com.github.yangkangli.x.ui.StatefulLayout;
import com.github.yangkangli.x.ui.interf.IStateView;

public class DefaultEmptyView extends BaseStateView {

    public DefaultEmptyView(Context context, StatefulLayout statefulLayout) {
        super(context, statefulLayout);
    }

    @Override
    public View onGetView() {
        if (rootView == null) {
            rootView = LayoutInflater.from(context).inflate(R.layout.default_empty_layout, null);
            if (rootView != null) {
                rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (statefulLayout.getOnRetryListener() != null) {
                            statefulLayout.getOnRetryListener().onRetry();
                        }
                    }
                });
            }
        }
        return rootView;
    }

    @Override
    public void onVisible(boolean visible) {

    }
}
