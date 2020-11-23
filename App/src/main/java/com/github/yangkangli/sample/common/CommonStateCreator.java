package com.github.yangkangli.sample.common;

import android.content.Context;

import com.github.yangkangli.x.ui.StatefulLayout;
import com.github.yangkangli.x.ui.interf.ICreator;
import com.github.yangkangli.x.ui.interf.IStateView;
import com.github.yangkangli.x.ui.views.DefaultEmptyView;
import com.github.yangkangli.x.ui.views.DefaultErrorView;
import com.github.yangkangli.x.ui.views.DefaultNetworkErrorView;

public class CommonStateCreator implements ICreator {
    @Override
    public IStateView createLoadingView(Context context, StatefulLayout statefulLayout) {
        return new CommonLoadingView(context, statefulLayout);
    }

    @Override
    public IStateView createEmptyView(Context context, StatefulLayout statefulLayout) {
        return new DefaultEmptyView(context, statefulLayout);
    }

    @Override
    public IStateView createErrorView(Context context, StatefulLayout statefulLayout) {
        return new DefaultErrorView(context, statefulLayout);
    }

    @Override
    public IStateView createNetworkErrorView(Context context, StatefulLayout statefulLayout) {
        return new DefaultNetworkErrorView(context, statefulLayout);
    }
}
