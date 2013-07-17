package my.think.proxy;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class ConstructorFinderTest {

    @Test
    public void should_get_constructor_that_method_signature_is_matched_provided_parameters_exactly() {
        ConstructorFinder constructorFinder = new ConstructorFinder(Pet.class, new Object[]{"Paul"});

        Constructor<?> constructor = constructorFinder.find();

        assertThat(constructor, notNullValue());
        assertThat(constructor.getParameterTypes(), is(new Class<?>[]{String.class}));
    }

    @Test
    public void should_get_constructor_that_method_signature_is_compatible_with_provided_primitive_numeric_parameter() {
        ConstructorFinder constructorFinder = new ConstructorFinder(Pet.class, new Object[]{100});

        Constructor<?> constructor = constructorFinder.find();

        assertThat(constructor, notNullValue());
        assertThat(constructor.getParameterTypes(), is(new Class<?>[]{double.class}));
    }

    @Test
    public void should_get_constructor_that_method_signature_is_compatible_with_provided_primitive_bool_parameter() {
        ConstructorFinder constructorFinder = new ConstructorFinder(Pet.class, new Object[]{true});

        Constructor<?> constructor = constructorFinder.find();

        assertThat(constructor, notNullValue());
        assertThat(constructor.getParameterTypes(), is(new Class<?>[]{boolean.class}));
    }

    @Test
    public void should_get_constructor_that_method_signature_is_compatible_with_inheritance() {
        ConstructorFinder constructorFinder = new ConstructorFinder(Pet.class, new Object[]{new ArrayList<String>()});

        Constructor<?> constructor = constructorFinder.find();

        assertThat(constructor, notNullValue());
        assertThat(constructor.getParameterTypes(), is(new Class<?>[]{Collection.class}));
    }

    @Test
    public void should_return_null_when_can_not_find_matched_constructor_with_same_count_of_parameters() {
        ConstructorFinder constructorFinder = new ConstructorFinder(Pet.class, new Object[]{new Object(), new Object()});

        Constructor<?> constructor = constructorFinder.find();

        assertThat(constructor, nullValue());
    }

    @Test
    public void should_return_null_when_can_not_find_matched_constructor_with_compatible_type_parameters() {
        ConstructorFinder constructorFinder = new ConstructorFinder(Pet.class, new Object[]{new Object()});

        Constructor<?> constructor = constructorFinder.find();

        assertThat(constructor, nullValue());
    }

    private static class Pet {
        private boolean clever;
        private Collection<String> friendNames;
        private String name;
        private double price;

        public Pet(String name) {
            this.name = name;
        }

        public Pet(double price) {
            this.price = price;
        }

        public Pet(boolean isClever) {
            clever = isClever;
        }

        public Pet(Collection<String> friendNames) {
            this.friendNames = friendNames;
        }
    }
}
