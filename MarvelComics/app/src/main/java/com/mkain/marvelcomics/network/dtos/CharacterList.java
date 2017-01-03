package com.mkain.marvelcomics.network.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterList {

    private Integer available;
    private Integer returned;
    private String collectionURI;
    private List<CharacterSummary> items;

    public CharacterList() {
        this.items = new ArrayList<>();
    }

}
