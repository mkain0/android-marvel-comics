package com.mkain.marvelcomics.transformers;

import com.mkain.marvelcomics.network.dtos.CharacterSummary;

import java.util.ArrayList;
import java.util.List;

public class CharacterTransformer implements Transformer<String, CharacterSummary> {

    @Override
    public String transform(CharacterSummary characterSummary) {
        return characterSummary.getName();
    }

    @Override
    public List<String> transform(List<CharacterSummary> characterSummaries) {
        List<String> characters = new ArrayList<>();
        for(CharacterSummary characterSummary : characterSummaries) {
            characters.add(transform(characterSummary));
        }
        return characters;
    }

}
