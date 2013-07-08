package my.think.proxy;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NumericCompatibilityTest {

    @Test
    public void should_be_compatible_when_type1_is_int_and_type2_is_integer() {
        NumericCompatibility number = new NumericCompatibility(int.class, Integer.class);

        assertThat(number.isCompatible(), is(true));
    }

    @Test
    public void should_be_compatible_when_type1_is_double_and_type2_is_int() {
        NumericCompatibility number = new NumericCompatibility(double.class, int.class);

        assertThat(number.isCompatible(), is(true));
    }
}
