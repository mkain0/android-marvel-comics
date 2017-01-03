package com.mkain.marvelcomics.network.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventList {

    private Integer available;
    private Integer returned;
    private String collectionURI;
    private List<EventSummary> items;

    public EventList() {
        this.items = new ArrayList<>();
    }
}
