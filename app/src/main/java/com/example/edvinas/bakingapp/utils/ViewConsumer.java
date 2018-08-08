package com.example.edvinas.bakingapp.utils;

import android.support.annotation.NonNull;

public interface ViewConsumer<T> {
    void accept(@NonNull T view);
}
