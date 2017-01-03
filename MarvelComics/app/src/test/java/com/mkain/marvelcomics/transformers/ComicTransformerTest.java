package com.mkain.marvelcomics.transformers;

import com.mkain.marvelcomics.domain.Comic;
import com.mkain.marvelcomics.network.dtos.CharacterList;
import com.mkain.marvelcomics.network.dtos.CharacterSummary;
import com.mkain.marvelcomics.network.dtos.CreatorList;
import com.mkain.marvelcomics.network.dtos.CreatorSummary;
import com.mkain.marvelcomics.network.dtos.MarvelComic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComicTransformerTest {

    private static final String TITLE = "Ant-Man: So (Trade Paperback)";
    private static final String CHARACTER_NAME = "Ant-Man";
    private static final String CREATOR_NAME = "Jeff Youngquist";
    private static final String UNAVAILABLE = "unavailable";
    private static final String DATE = "2015-10-15T11:13:52-0400";
    private static final String SERIES = "Ant-Man: So (2011 - Present)";
    private static final String PRICE = "USD 10.5";
    private static final String PATH = "http://image";
    private static final String EXTENSION = "jpg";
    private static final String IMAGE = PATH + "." + EXTENSION;
    private static final Double PRICE_DIFFERENT_FROM_ZERO = 10.50d;
    private static final Double ZERO = 0d;
    private static final int PAGE_COUNT = 35;

    private List<String> creators = new ArrayList<>(Arrays.asList(CREATOR_NAME));
    private List<String> characters = new ArrayList<>(Arrays.asList(CHARACTER_NAME));
    private CreatorList creatorList = buildCreatorList();
    private CharacterList characterList = buildCharacterList();
    private Comic comic;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private MarvelComic marvelComic;

    @Mock
    private CreatorTransformer creatorTransformer;

    @Mock
    private CharacterTransformer characterTransformer;
    @InjectMocks
    private ComicTransformer comicTransformer;

    @Test
    public void shouldTransformSuccessfullyFromMarvelComicToComicWhenComicHasPriceEqualsZero() {
        setupStubs(ZERO);
        comic = comicTransformer.transform(marvelComic);
        verifyTransformers();
        assertComics(UNAVAILABLE);
    }

    @Test
    public void shouldTransformSuccessfullyFromMarvelComicToComicWhenComicHasPriceDifferentFromZero() {
        setupStubs(PRICE_DIFFERENT_FROM_ZERO);
        comic = comicTransformer.transform(marvelComic);
        verifyTransformers();
        assertComics(PRICE);
    }

    private CreatorList buildCreatorList() {
        CreatorList creatorList = new CreatorList();
        creatorList.setReturned(1);
        creatorList.setItems(new ArrayList<>(Arrays.asList(new CreatorSummary())));
        return creatorList;
    }

    private CharacterList buildCharacterList() {
        CharacterList characterList = new CharacterList();
        characterList.setReturned(1);
        characterList.setItems(new ArrayList<>(Arrays.asList(new CharacterSummary())));
        return characterList;
    }

    private void setupStubs(Double price) {
        when(creatorTransformer.transform(ArgumentMatchers.<CreatorSummary>anyList())).thenReturn(creators);
        when(characterTransformer.transform(ArgumentMatchers.<CharacterSummary>anyList())).thenReturn(characters);
        when(marvelComic.getTitle()).thenReturn(TITLE);
        when(marvelComic.getPrices().get(0).getPrice()).thenReturn(price);
        when(marvelComic.getModified()).thenReturn(DATE);
        when(marvelComic.getPageCount()).thenReturn(PAGE_COUNT);
        when(marvelComic.getSeries().getName()).thenReturn(SERIES);
        when(marvelComic.getCreators()).thenReturn(creatorList);
        when(marvelComic.getCharacters()).thenReturn(characterList);
        when(marvelComic.getThumbnail().getPath()).thenReturn(PATH);
        when(marvelComic.getThumbnail().getExtension()).thenReturn(EXTENSION);
    }

    private void verifyTransformers() {
        verify(creatorTransformer).transform(ArgumentMatchers.<CreatorSummary>anyList());
        verify(characterTransformer).transform(ArgumentMatchers.<CharacterSummary>anyList());
    }

    private void assertComics(String price) {
        assertThat(comic.getTitle(), is(TITLE));
        assertThat(comic.getImage(), is(IMAGE));
        assertThat(comic.getPrice(), is(price));
        assertThat(comic.getDate(), is(DATE));
        assertThat(comic.getPageCount(), is(PAGE_COUNT));
        assertThat(comic.getSeries(), is(SERIES));
        assertThat(comic.getCreators(), is(creators));
        assertThat(comic.getCharacters(), is(characters));
    }

}
