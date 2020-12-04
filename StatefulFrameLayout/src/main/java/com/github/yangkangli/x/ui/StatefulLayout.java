package com.github.yangkangli.x.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.yangkangli.x.ui.interf.ICreator;
import com.github.yangkangli.x.ui.interf.IStateView;
import com.github.yangkangli.x.ui.interf.OnRetryListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StatefulLayout extends FrameLayout {

    public enum State {
        /**
         * 加载中的状态
         */
        LOADING,

        /**
         * 出错的状态
         */
        ERROR,

        /**
         * 网络错误
         */
        NETWORK_ERROR,

        /**
         * 数据为空的状态
         */
        EMPTY,

        /**
         * 显示数据的状态
         */
        CONTENT
    }

    private State currentState;

    private Map<State, IStateView> stateViewMap;

    private List<View> contentViews;

    private ICreator iCreator;

    private Context context;

    private OnRetryListener onRetryListener;


    public StatefulLayout(@NonNull Context context) {
        this(context, null);
    }

    public StatefulLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatefulLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        contentViews = new LinkedList<>();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initContentView();
    }

    private void initContentView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            contentViews.add(getChildAt(i));
        }
    }

    public void setStateViewCreator(ICreator creator) {
        this.iCreator = creator;
    }

    public void setOnRetryListener(OnRetryListener listener) {
        this.onRetryListener = listener;
    }

    public OnRetryListener getOnRetryListener() {
        return onRetryListener;
    }

    /**
     * 切换状态
     *
     * @param newState
     */
    public void switchState(State newState) {

        // 如果新的状态为null，或者与现在的状态一样，则直接返回
        if (newState == null || newState == currentState) {
            return;
        }

        // 切换状态对应页面
        switch (newState) {
            case LOADING:
                showStateView(State.LOADING, true);
                showStateView(State.ERROR, false);
                showStateView(State.NETWORK_ERROR, false);
                showStateView(State.EMPTY, false);
                showContentView(false);
                break;
            case ERROR:
                showStateView(State.LOADING, false);
                showStateView(State.ERROR, true);
                showStateView(State.NETWORK_ERROR, false);
                showStateView(State.EMPTY, false);
                showContentView(false);
                break;
            case NETWORK_ERROR:
                showStateView(State.LOADING, false);
                showStateView(State.ERROR, false);
                showStateView(State.NETWORK_ERROR, true);
                showStateView(State.EMPTY, false);
                showContentView(false);
                break;
            case EMPTY:
                showStateView(State.LOADING, false);
                showStateView(State.ERROR, false);
                showStateView(State.NETWORK_ERROR, false);
                showStateView(State.EMPTY, true);
                showContentView(false);
                break;
            default:
                showStateView(State.LOADING, false);
                showStateView(State.ERROR, false);
                showStateView(State.NETWORK_ERROR, false);
                showStateView(State.EMPTY, false);
                showContentView(true);
                break;
        }

        // 使用新的状态替换为现在的状态
        currentState = newState;
    }

    /**
     * 显示或不显示 状态页面
     *
     * @param state
     * @param show
     */
    private void showStateView(State state, boolean show) {
        if (show) {
            IStateView stateView = createAndAddStateView(state);
            if (stateView != null && stateView.onGetView() != null) {
                stateView.onGetView().setVisibility(VISIBLE);
            }
        } else {
            if (stateViewMap != null) {
                IStateView stateView = stateViewMap.get(state);
                if (stateView != null && stateView.onGetView() != null) {
                    stateView.onGetView().setVisibility(GONE);
                }
            }
        }
    }

    /**
     * 根据状态得到IStateView
     *
     * @param state
     * @return
     */
    public IStateView getStateView(State state) {
        return createAndAddStateView(state);
    }

    /**
     * 显示内容页面
     *
     * @param show true：显示；false：不显示
     */
    private void showContentView(boolean show) {
        if (contentViews != null) {
            for (View view : contentViews) {
                view.setVisibility(show ? VISIBLE : GONE);
            }
        }
    }

    /**
     * 创建并将创建的View加入到StatefulLayout
     *
     * @param state
     */
    private IStateView createAndAddStateView(State state) {
        if (stateViewMap == null) {
            stateViewMap = new HashMap<>();
        }
        IStateView stateView = stateViewMap.get(state);

        if (stateView == null) {

            switch (state) {
                // 加载页面
                case LOADING:
                    if (iCreator != null) {
                        stateView = iCreator.createLoadingView(context, this);
                    }
                    break;
                // 错误页面
                case ERROR:
                    if (iCreator != null) {
                        stateView = iCreator.createErrorView(context, this);
                    }
                    break;
                // 网络错误界面
                case NETWORK_ERROR:
                    if (iCreator != null) {
                        stateView = iCreator.createNetworkErrorView(context, this);
                    }
                    break;
                // 数据为空界面
                case EMPTY:
                    if (iCreator != null) {
                        stateView = iCreator.createEmptyView(context, this);
                    }
                    break;
            }
            if (stateView != null) {
                stateViewMap.put(state, stateView);
                addView(stateView.onGetView());
            }
        }
        return stateView;
    }
}
