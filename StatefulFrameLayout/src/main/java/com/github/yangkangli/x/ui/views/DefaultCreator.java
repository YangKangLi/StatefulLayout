package com.github.yangkangli.x.ui.views;

import android.content.Context;

import com.github.yangkangli.x.ui.StatefulLayout;
import com.github.yangkangli.x.ui.interf.ICreator;
import com.github.yangkangli.x.ui.interf.IStateView;

public class DefaultCreator implements ICreator {
    @Override
    public IStateView createLoadingView(Context context, StatefulLayout statefulLayout) {
        return new DefaultLoadingView(context, statefulLayout);
    }

    @Override
    public IStateView createErrorView(Context context, StatefulLayout statefulLayout) {
        return new DefaultErrorView(context, statefulLayout);
    }

    @Override
    public IStateView createNetworkErrorView(Context context, StatefulLayout statefulLayout) {
        return new DefaultNetworkErrorView(context, statefulLayout);
    }

    @Override
    public IStateView createEmptyView(Context context, StatefulLayout statefulLayout) {
        return new DefaultEmptyView(context, statefulLayout);
    }
}
