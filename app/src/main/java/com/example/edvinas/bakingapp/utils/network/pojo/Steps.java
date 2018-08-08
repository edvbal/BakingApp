package com.example.edvinas.bakingapp.utils.network.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Steps implements Serializable {
    private final String shortDescription;
    private final String description;
    @SerializedName("videoURL")
    private final String videoUrl;

    public Steps(String shortDescription, String description, String videoUrl) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
