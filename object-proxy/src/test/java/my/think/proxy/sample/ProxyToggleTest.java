package my.think.proxy.sample;

import my.think.proxy.sample.domain.noshery.McDonalds;
import org.junit.Test;

import static my.think.proxy.sample.domain.noshery.Country.Japan;
import static my.think.proxy.sample.domain.noshery.Country.Others;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProxyToggleTest {

    @Test
    public void should_not_put_tomato_sauce_in_japan() {
        McDonalds mcDonalds = new McDonalds(Japan);

        String hamburgerDesc = mcDonalds.makeHamburg();

        assertThat(hamburgerDesc, is("Bread|Lettuce|Meat|Bread|"));
    }

    @Test
    public void should_not_put_tomato_sauce_in_other_countries_of_world() {
        McDonalds mcDonalds = new McDonalds(Others);

        String hamburgerDesc = mcDonalds.makeHamburg();

        assertThat(hamburgerDesc, is("Bread|TomatoSauce|Lettuce|Meat|Bread|"));
    }
}
