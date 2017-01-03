package com.mkain.marvelcomics.transformers;

import com.mkain.marvelcomics.domain.Comic;
import com.mkain.marvelcomics.domain.Comic.ComicBuilder;
import com.mkain.marvelcomics.network.dtos.CharacterList;
import com.mkain.marvelcomics.network.dtos.CreatorList;
import com.mkain.marvelcomics.network.dtos.Image;
import com.mkain.marvelcomics.network.dtos.MarvelComic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComicTransformer implements Transformer<Comic, MarvelComic> {

    // TODO: use Dagger2.
    private CreatorTransformer creatorTransformer = new CreatorTransformer();
    private CharacterTransformer characterTransformer = new CharacterTransformer();

    @Override
    public Comic transform(MarvelComic marvelComic) {
        List<String> creators = buildCreators(marvelComic.getCreators());
        List<String> characters = buildCharacters(marvelComic.getCharacters());
        return new ComicBuilder()
                .withTitle(marvelComic.getTitle())
                .withImage(buildImagePath(marvelComic.getThumbnail()))
                .withPrice(marvelComic.getPrices().get(0).getPrice())
                .withDate(marvelComic.getModified())
                .withPageCount(marvelComic.getPageCount())
                .withSeries(marvelComic.getSeries().getName())
                .withCreators(creators)
                .withCharacters(characters)
                .build();
    }

    @Override
    public List<Comic> transform(List<MarvelComic> marvelComics) {
        List<Comic> comics = new ArrayList<>();
        for(MarvelComic marvelComic : marvelComics) {
            comics.add(transform(marvelComic));
        }
        return comics;
    }

    private List<String> buildCreators(CreatorList creators) {
        if (creators.getReturned() == 0) {
            return Collections.emptyList();
        }
        return creatorTransformer.transform(creators.getItems());
    }

    private List<String> buildCharacters(CharacterList characters) {
        if(characters.getReturned() == 0){
            return Collections.emptyList();
        }
        return characterTransformer.transform(characters.getItems());
    }

    private String buildImagePath(Image image) {
        return image.getPath() + "." + image.getExtension();
    }

}
