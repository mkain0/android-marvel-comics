package com.mkain.marvelcomics.network.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatorList {

    private Integer available;
    private Integer returned;
    private String collectionURI;
    private List<CreatorSummary> items;

    public CreatorList() {
        this.items = new ArrayList<>();
    }

}
