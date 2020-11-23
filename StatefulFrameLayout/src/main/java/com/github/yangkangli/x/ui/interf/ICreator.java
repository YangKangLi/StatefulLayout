package com.github.yangkangli.x.ui.interf;

import android.content.Context;

import com.github.yangkangli.x.ui.StatefulLayout;

public interface ICreator {

    /**
     * 创建加载页面
     *
     * @param context
     * @param statefulLayout
     * @return
     */
    IStateView createLoadingView(Context context, StatefulLayout statefulLayout);

    /**
     * 创建空数据页面
     *
     * @param context
     * @return
     */
    IStateView createEmptyView(Context context, StatefulLayout statefulLayout);

    /**
     * 创建加载出错页面
     *
     * @param context
     * @return
     */
    IStateView createErrorView(Context context, StatefulLayout statefulLayout);

    /**
     * 加载网络错误页面
     *
     * @param context
     * @return
     */
    IStateView createNetworkErrorView(Context context, StatefulLayout statefulLayout);
}
