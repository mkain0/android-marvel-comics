package com.mkain.marvelcomics.network.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventSummary {

    private String resourceURI;
    private String name;

}
