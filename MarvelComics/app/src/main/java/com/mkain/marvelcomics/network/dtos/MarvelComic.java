package com.mkain.marvelcomics.network.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarvelComic {

    private Integer id;
    private Integer digitalId;
    private String title;
    private Double issueNumber;
    private String variantDescription;
    private String description;
    private String modified;
    private String isbn;
    private String upc;
    private String diamondCode;
    private String ean;
    private String issn;
    private String format;
    private Integer pageCount;
    private List<TextObject> textObjects;
    private String resourceURI;
    private List<Url> urls;
    private SeriesSummary series;
    private List<ComicSummary> variants;
    private List<ComicSummary> collections;
    private List<ComicSummary> collectedIssues;
    private List<ComicDate> dates;
    private List<ComicPrice> prices;
    private Image thumbnail;
    private List<Image> images;
    private CreatorList creators;
    private CharacterList characters;
    private StoryList stories;
    private EventList events;

    public MarvelComic() {
        this.textObjects = new ArrayList<>();
        this.urls = new ArrayList<>();
        this.variants = new ArrayList<>();
        this.collections = new ArrayList<>();
        this.collectedIssues = new ArrayList<>();
        this.dates = new ArrayList<>();
        this.prices = new ArrayList<>();
        this.images = new ArrayList<>();
    }
}
