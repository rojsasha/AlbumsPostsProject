package com.example.alex.albumspostsproject.ui.posts_page.posts;

import com.example.alex.albumspostsproject.IProgressBar;
import com.example.alex.albumspostsproject.Lifecycle;
import com.example.alex.albumspostsproject.data.entity.PostsModel;

import java.util.ArrayList;

public interface PostsContract {
    interface View extends IProgressBar{
        void onSuccess(ArrayList<PostsModel> arrayList);

        void onError(String msg);

    }

    interface Presenter extends Lifecycle<View>{
        void getPosts();

    }
}
