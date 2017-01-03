package com.mkain.marvelcomics.network.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacterSummary {

    private String resourceURI;
    private String name;
    private String role;

}
