package com.github.caoyouxin.taoke.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.github.caoyouxin.taoke.R;
import com.github.caoyouxin.taoke.adapter.SearchHintAdapter;
import com.github.caoyouxin.taoke.datasource.SearchHintDataSource;
import com.github.caoyouxin.taoke.model.SearchHintItem;
import com.github.caoyouxin.taoke.ui.activity.SearchActivity;
import com.shizhefei.mvc.MVCNormalHelper;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.next.tagview.TagCloudView;

/**
 * Created by jasontsang on 10/24/17.
 */

public class SearchHistoryFragment extends Fragment implements TagCloudView.OnTagClickListener {

    View rootView;

    @BindView(R.id.search_history_list)
    TagCloudView searchHistoryList;

    private List<String> tags;
    private SearchActivity context;

    public SearchHistoryFragment setSearchActivity(SearchActivity context) {
        this.context = context;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_search_history, container, false);
            ButterKnife.bind(this, rootView);

            if (savedInstanceState != null) {
                //restore
            }

            initSearchHintList();
        }
        return rootView;
    }



    private void initSearchHintList() {
        searchHistoryList.setTags(tags);
        searchHistoryList.setOnTagClickListener(this);
    }

    @OnClick(R.id.clear_history)
    public void onClick(View view) {
        this.tags = new ArrayList<>();
        searchHistoryList.setTags(tags);
        this.context.clearHistory();
    }

    @Override
    public void onTagClick(int position) {
        this.context.setText(this.tags.get(position));
    }

    public void setHistory(List<String> strings) {
        this.tags = strings;
    }
}
