package com.mkain.marvelcomics.network.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComicDataWrapper {

    private Integer code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private ComicDataContainer data;
    private String etag;

}
