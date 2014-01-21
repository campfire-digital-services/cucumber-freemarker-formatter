package au.com.permeance.cucumber;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class Base64DecodeTest {

    private Base64Decode subject;

    @Before
    public void setUp() throws Exception {

        subject = new Base64Decode();
    }

    @Test
    public void testExec() throws Exception {

        final String result = subject.exec(singletonList("SGkgTWFydGluIQ=="));

        assertThat(result, is(equalTo("Hi Martin!")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecWithMultipleArguments() throws Exception {

        subject.exec(asList("one", "two"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecWithNoArguments() throws Exception {

        subject.exec(emptyList());
    }

    @Test
    public void testExecWithNullArgument() throws Exception {

        final String result = subject.exec(singletonList(null));

        assertThat(result, is(nullValue()));
    }

}
