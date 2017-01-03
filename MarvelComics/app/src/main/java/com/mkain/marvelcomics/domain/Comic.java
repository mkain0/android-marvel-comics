package com.mkain.marvelcomics.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class Comic {

    private static final String UNAVAILABLE = "unavailable";
    private static final String CURRENCY_TYPE = "USD";
    private static final Double ZERO = 0d;

    private String title;
    private String image;
    private String price;
    private String date;
    private Integer pageCount;
    private String series;
    private List<String> creators;
    private List<String> characters;

    public Comic(String title, String image, Double price, String date, Integer pageCount,
                 String series, List<String> creators, List<String> characters) {
        this.title = title;
        this.image = image;
        this.price = ZERO.equals(price) ? UNAVAILABLE : retrievePrice(price);
        this.date = date;
        this.pageCount = pageCount;
        this.series = series;
        this.creators = creators;
        this.characters = characters;
    }

    private String retrievePrice(Double price) {
        return CURRENCY_TYPE + " " + price.toString();
    }

    public static class ComicBuilder {

        private String title;
        private String image;
        private Double price;
        private String date;
        private Integer pageCount;
        private String series;
        private List<String> creators;
        private List<String> characters;

        public ComicBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ComicBuilder withImage(String image) {
            this.image = image;
            return this;
        }

        public ComicBuilder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public ComicBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        public ComicBuilder withPageCount(Integer pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public ComicBuilder withSeries(String series) {
            this.series = series;
            return this;
        }

        public ComicBuilder withCreators(List<String> creators) {
            this.creators = creators;
            return this;
        }

        public ComicBuilder withCharacters(List<String> characters) {
            this.characters = characters;
            return this;
        }

        public Comic build() {
            return new Comic(title, image, price, date, pageCount, series, creators, characters);
        }

    }

}
