package com.github.yangkangli.x.ui.views;

import android.content.Context;
import android.view.View;

import com.github.yangkangli.x.ui.StatefulLayout;
import com.github.yangkangli.x.ui.interf.IStateView;

public abstract class BaseStateView implements IStateView {

    protected Context context;

    protected View rootView;

    protected StatefulLayout statefulLayout;

    public BaseStateView(Context context, StatefulLayout statefulLayout) {
        this.context = context;
        this.statefulLayout = statefulLayout;
    }
}
