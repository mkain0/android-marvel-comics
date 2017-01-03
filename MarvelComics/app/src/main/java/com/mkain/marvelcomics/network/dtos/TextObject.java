package com.mkain.marvelcomics.network.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TextObject {

    private String type;
    private String language;
    private String text;

}
