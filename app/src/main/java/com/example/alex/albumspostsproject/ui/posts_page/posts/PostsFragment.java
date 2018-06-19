package com.example.alex.albumspostsproject.ui.posts_page.posts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.alex.albumspostsproject.R;
import com.example.alex.albumspostsproject.StartApplication;
import com.example.alex.albumspostsproject.data.entity.PostsModel;
import com.example.alex.albumspostsproject.ui.base.BaseFragment;
import com.example.alex.albumspostsproject.ui.posts_page.comments.CommentsActivity;

import java.util.ArrayList;

public class PostsFragment extends BaseFragment implements PostsContract.View, AdapterView.OnItemClickListener {
    private ListView mListView;
    private PostsContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContext() != null) mPresenter = new PostsPresenter(StartApplication.get(getContext()).getService());
        mPresenter.bind(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        mListView = view.findViewById(R.id.listView);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setOnItemClickListener(this);
        mPresenter.getPosts();
    }

    @Override
    public void onSuccess(ArrayList<PostsModel> arrayList) {
        if (getContext() != null)
        mListView.setAdapter(new PostsAdapter(getContext(), arrayList));

    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getContext(), CommentsActivity.class)
                .putExtra("postId", ((PostsModel) parent.getItemAtPosition(position)).getUserId()));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
