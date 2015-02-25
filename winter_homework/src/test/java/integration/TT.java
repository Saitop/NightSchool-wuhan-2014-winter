package integration;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Administrator on 2015/2/16.
 */
public class TT {
    @Test
    public void testName() throws Exception {
        assertThat("i",is("i"));
    }
}
