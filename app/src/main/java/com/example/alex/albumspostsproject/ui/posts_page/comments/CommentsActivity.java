package com.example.alex.albumspostsproject.ui.posts_page.comments;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alex.albumspostsproject.R;
import com.example.alex.albumspostsproject.StartApplication;
import com.example.alex.albumspostsproject.data.entity.CommentsModel;
import com.example.alex.albumspostsproject.ui.base.BaseActivity;

import java.util.ArrayList;

public class CommentsActivity extends BaseActivity implements CommentsContract.View {

    private CommentsContract.Presenter mPresenter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_listview);
        mListView = findViewById(R.id.listView);
        initToolbar(true);

        mPresenter = new CommentsPresenter(StartApplication.get(this).getService());

        mPresenter.bind(this);
        mPresenter.getComments(getIntent().getIntExtra("postId", 0));
    }

    @Override
    public void onSuccess(ArrayList<CommentsModel> arrayList) {
        mListView.setAdapter(new CommentsAdapter(this, arrayList));
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingIndicator() {
        showProgressBar();
    }

    @Override
    public void hideLoadingIndicator() {
        dismissProgressBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
