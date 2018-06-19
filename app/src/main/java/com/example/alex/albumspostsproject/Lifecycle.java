package com.example.alex.albumspostsproject;

public interface Lifecycle<V> {
    void bind(V view);

    void unbind();

}
