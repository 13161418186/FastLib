package com.aries.library.fast.demo.module.main.sample.news;

import android.os.Bundle;

import com.aries.library.fast.demo.R;
import com.aries.library.fast.demo.adapter.WidgetAdapter;
import com.aries.library.fast.manager.RxJavaManager;
import com.aries.library.fast.module.fragment.FastRefreshLoadFragment;
import com.aries.library.fast.retrofit.FastObserver;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.trello.rxlifecycle3.android.FragmentEvent;

/**
 * @Author: AriesHoo on 2018/11/19 14:20
 * @E-Mail: AriesHoo@126.com
 * @Function: 腾讯新闻-刷新
 * @Description:
 */
public class NewsRefreshItemFragment extends FastRefreshLoadFragment {

    public static NewsRefreshItemFragment newInstance() {
        Bundle args = new Bundle();
        NewsRefreshItemFragment fragment = new NewsRefreshItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fast_layout_refresh_recycler;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public RefreshHeader getRefreshHeader() {
        return new ClassicsHeader(mContext);
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return new WidgetAdapter();
    }

    @Override
    public void loadData(int page) {
        mRefreshLayout.autoRefresh();
        RxJavaManager.getInstance().setTimer(1000)
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new FastObserver<Long>() {
                    @Override
                    public void _onNext(Long entity) {
                        mRefreshLayout.finishRefresh();
                    }
                });
    }
}
