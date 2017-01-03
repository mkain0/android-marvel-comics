package com.mkain.marvelcomics.network.helpers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HashGeneratorTest {

    private static final String TIMESTAMP = "1";
    private static final String PUBLIC_KEY = "1234";
    private static final String PRIVATE_KEY = "abcd";
    private static final String VALID_MD5 = "ffd275c5130566a2916217b101f26150";

    private HashGenerator hashGenerator = new HashGenerator();

    @Test
    public void shouldRetrieveValidMd5() throws Exception {
        String md5 = hashGenerator.generate(TIMESTAMP, PUBLIC_KEY, PRIVATE_KEY);
        assertThat(md5, is(VALID_MD5));
    }

}
