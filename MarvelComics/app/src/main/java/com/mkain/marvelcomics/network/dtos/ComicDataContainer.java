package com.mkain.marvelcomics.network.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComicDataContainer {

    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;

    @SerializedName("results")
    private List<MarvelComic> comics;

    public ComicDataContainer() {
        this.comics = new ArrayList<>();
    }
}
