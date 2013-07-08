package my.think.aspect.runner;

import my.think.aspect.runner.sample.McDonalds;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AspectToggleTest {

    @Test
    public void should_not_put_tomato_sauce() throws Exception {
        McDonalds mcDonalds = new McDonalds();

        String hamburgerDesc = mcDonalds.makeHamburg();

        assertThat(hamburgerDesc, is("Bread|Lettuce|Meat|Bread|"));
    }
}
