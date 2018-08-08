package com.example.edvinas.bakingapp.base;

public interface BasePresenter<T> {
    void takeView(T view);

    void dropView();
}
