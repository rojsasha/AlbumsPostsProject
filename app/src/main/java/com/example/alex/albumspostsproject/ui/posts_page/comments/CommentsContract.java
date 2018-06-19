package com.example.alex.albumspostsproject.ui.posts_page.comments;

import com.example.alex.albumspostsproject.IProgressBar;
import com.example.alex.albumspostsproject.Lifecycle;
import com.example.alex.albumspostsproject.data.entity.CommentsModel;

import java.util.ArrayList;

public interface CommentsContract {
    interface View extends IProgressBar{
        void onSuccess(ArrayList<CommentsModel> model);

        void onError(String msg);
    }

    interface Presenter extends Lifecycle<View>{

        void getComments(int postId);
    }
}
