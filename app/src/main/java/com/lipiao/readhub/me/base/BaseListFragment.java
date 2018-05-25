package com.lipiao.readhub.me.base;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lipiao.readhub.me.R;
import com.lipiao.readhub.me.api.ApiStore;
import com.lipiao.readhub.me.bean.BaseData;
import com.lipiao.readhub.me.bean.HotTopic;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportActivity;
import mehdi.sakout.dynamicbox.DynamicBox;

/**
 * @author lipiao
 * @data 2018/5/24.
 * @description 列表基类fragment
 */
public class BaseListFragment extends BaseFragment {
    private FrameLayout frame_list_container;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private SupportActivity mActivity;
    private LinearLayoutManager mManager;
    private MyAdapter adapter;
    private DynamicBox mBox; // 加载界面
    private String TAG = "BaseListFragment";

    public static BaseListFragment newInstance() {
        return new BaseListFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_base;
    }

    @Override
    protected void initView(View view) {
        mActivity = (SupportActivity) getActivity();
        frame_list_container = view.findViewById(R.id.frame_list_container);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);

//        mBox = new DynamicBox(mActivity, frame_list_container);
//        mBox.setClickListener(v -> {
//
//        });

        mManager = new LinearLayoutManager(mActivity);
        adapter = new MyAdapter();
        recyclerView.setLayoutManager(mManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                fab.setVisibility(mManager.findFirstVisibleItemPosition() > 0 ? View.VISIBLE : View.GONE);
            }
        });
        fab.setOnClickListener(v -> mManager.smoothScrollToPosition(recyclerView, null, 0));
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#607D8B"), Color.BLACK, Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.clear();
            initData();
        });

    }

    @Override
    protected void initData() {
        ApiStore.getHotTopicStore().getHotTopic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseData>() {
                    @Override
                    public void accept(BaseData baseData) throws Exception {
                        Log.e(TAG, "data: " + baseData.toString());
                        Log.e(TAG, "size: " + baseData.data.size() + "");
                        List<HotTopic> data = baseData.data;
                        adapter.setData(data);
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "throwable: ", throwable);
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
    }

    public void onTabClick() {
        if (mManager.findFirstCompletelyVisibleItemPosition() != 0) {
            recyclerView.smoothScrollToPosition(0);
            return;
        }
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
            adapter.clear();
            initData();
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        private List<HotTopic> data = new ArrayList<>();

        public void setData(List<HotTopic> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder( LayoutInflater.from(getActivity()).inflate(R.layout.list_item_topic, parent, false));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.txt_topic_title.setText(data.get(position).title);
            holder.txt_topic_summary.setText(data.get(position).summary);
        }


        @Override
        public int getItemCount() {
            return data.size();
        }

        public void clear() {
            this.data.clear();
            notifyDataSetChanged();
        }

        class MyHolder extends RecyclerView.ViewHolder{
            TextView txt_topic_title;
            TextView txt_topic_summary;

            public MyHolder(View itemView) {
                super(itemView);
                txt_topic_title = itemView.findViewById(R.id.txt_topic_title);
                txt_topic_summary = itemView.findViewById(R.id.txt_topic_summary);
            }
        }

    }
}
