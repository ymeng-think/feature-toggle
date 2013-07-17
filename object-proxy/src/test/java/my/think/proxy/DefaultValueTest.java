package my.think.proxy;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class DefaultValueTest {

    @Test
    public void should_return_false_as_default_value_of_boolean_type() {
        DefaultValue defaultValue = new DefaultValue(boolean.class);

        assertThat((Boolean)defaultValue.value(), is(false));
    }

    @Test
    public void should_return_0_as_default_value_of_number_type() {
        DefaultValue defaultValue = new DefaultValue(int.class);

        assertThat((Integer)defaultValue.value(), is(0));
    }

    @Test
    public void should_return_blank_as_default_value_of_string() {
        DefaultValue defaultValue = new DefaultValue(String.class);

        assertThat((String)defaultValue.value(), is(""));
    }

    @Test
    public void should_return_null_as_default_value_of_object_type() {
        DefaultValue defaultValue = new DefaultValue(Object.class);

        assertThat(defaultValue.value(), nullValue());
    }
}
