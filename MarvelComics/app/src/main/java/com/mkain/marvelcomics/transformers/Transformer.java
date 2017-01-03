package com.mkain.marvelcomics.transformers;

import java.util.List;

public interface Transformer<T,S> {

    T transform(S object);
    List<T> transform(List<S> objects);

}
