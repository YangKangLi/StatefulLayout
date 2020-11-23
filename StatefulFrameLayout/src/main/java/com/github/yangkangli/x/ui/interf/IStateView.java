package com.github.yangkangli.x.ui.interf;

import android.view.View;

public interface IStateView {

    /**
     * 当获取View时回调
     *
     * @return
     */
    View onGetView();

    /**
     * 当可见性变化时回调
     *
     * @param visible
     */
    void onVisible(boolean visible);
}
