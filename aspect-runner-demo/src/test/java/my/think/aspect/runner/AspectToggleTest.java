package my.think.aspect.runner;

import my.think.aspect.runner.sample.McDonalds;
import org.junit.Test;

import static my.think.aspect.runner.sample.Country.Japan;
import static my.think.aspect.runner.sample.Country.Others;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AspectToggleTest {

    @Test
    public void should_not_put_tomato_sauce_in_japan() throws Exception {
        McDonalds mcDonalds = new McDonalds(Japan);

        String hamburgerDesc = mcDonalds.makeHamburg();

        assertThat(hamburgerDesc, is("Bread|Lettuce|Meat|Bread|"));
    }

    @Test
    public void should_not_put_tomato_sauce_in_other_countries_of_world() throws Exception {
        McDonalds mcDonalds = new McDonalds(Others);

        String hamburgerDesc = mcDonalds.makeHamburg();

        assertThat(hamburgerDesc, is("Bread|TomatoSauce|Lettuce|Meat|Bread|"));
    }
}
