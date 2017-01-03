package com.mkain.marvelcomics.transformers;

import com.mkain.marvelcomics.network.dtos.CreatorSummary;

import java.util.ArrayList;
import java.util.List;

public class CreatorTransformer implements Transformer<String, CreatorSummary> {

    @Override
    public String transform(CreatorSummary creatorSummary) {
        return creatorSummary.getName();
    }

    @Override
    public List<String> transform(List<CreatorSummary> creatorSummaries) {
        List<String> creators = new ArrayList<>();
        for(CreatorSummary creatorSummary : creatorSummaries) {
            creators.add(transform(creatorSummary));
        }
        return creators;
    }
}
