package com.mkain.marvelcomics.transformers;

import com.mkain.marvelcomics.network.dtos.CreatorSummary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreatorTransformerTest {

    private static final String CREATOR_NAME = "Jeff Youngquist";

    private String creator;

    @Mock
    private CreatorSummary creatorSummary;

    @InjectMocks
    private CreatorTransformer creatorTransformer;

    @Test
    public void shouldTransformSuccessfullyFromCreatorSummaryToString() {
        when(creatorSummary.getName()).thenReturn(CREATOR_NAME);
        creator = creatorTransformer.transform(creatorSummary);
        assertThat(creator, is(CREATOR_NAME));
    }

}
