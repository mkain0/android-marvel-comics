package com.mkain.marvelcomics.transformers;

import com.mkain.marvelcomics.network.dtos.CharacterSummary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CharacterTransformerTest {

    private static final String CHARACTER_NAME = "Captain America";

    private String character;

    @Mock
    private CharacterSummary characterSummary;

    @InjectMocks
    private CharacterTransformer characterTransformer;

    @Test
    public void shouldTransformSuccessfullyFromCharacterSummaryToString() {
        when(characterSummary.getName()).thenReturn(CHARACTER_NAME);
        character = characterTransformer.transform(characterSummary);
        assertThat(character, is(CHARACTER_NAME));
    }

}
