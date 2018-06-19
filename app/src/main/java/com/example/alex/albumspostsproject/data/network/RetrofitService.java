package com.example.alex.albumspostsproject.data.network;

import com.example.alex.albumspostsproject.config.AppConstants;
import com.example.alex.albumspostsproject.data.entity.AlbumModel;
import com.example.alex.albumspostsproject.data.entity.CommentsModel;
import com.example.alex.albumspostsproject.data.entity.PhotoModel;
import com.example.alex.albumspostsproject.data.entity.PostsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET(AppConstants.POSTS_ENDPOINT)
    Call<ArrayList<PostsModel>> getPosts ();

    @GET(AppConstants.COMMENTS_ENDPOINT)
    Call<ArrayList<CommentsModel>> geComments (@Query("postId") int postId);

    @GET(AppConstants.ALBUMS_ENDPOINT)
    Call<ArrayList<AlbumModel>> getAlbums();

    @GET(AppConstants.PHOTOS_ENDPOINT)
    Call<ArrayList<PhotoModel>> getPhotos (@Query("albumId") int albumId);
}
