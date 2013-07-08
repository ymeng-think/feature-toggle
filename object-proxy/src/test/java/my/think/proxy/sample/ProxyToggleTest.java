package my.think.proxy.sample;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import my.think.proxy.ProxyGenerator;
import my.think.proxy.sample.domain.noshery.McDonalds;
import my.think.proxy.sample.domain.school.EnglishClass;
import my.think.proxy.sample.domain.school.Grade;
import my.think.proxy.sample.domain.school.Teacher;
import org.junit.Test;

import java.lang.reflect.Method;

import static my.think.proxy.sample.domain.noshery.Country.Japan;
import static my.think.proxy.sample.domain.noshery.Country.Others;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void should_create_proxy_with_constructor_that_includes_compatible_inherited_subclasses() {
        Teacher teacher = new ProxyGenerator<Teacher>(Teacher.class, new Object[]{new EnglishClass()})
                .generate(nullFilter(), nullHandler());

        assertTrue(teacher.teach() instanceof EnglishClass);
    }

    @Test
    public void should_create_proxy_with_constructor_that_includes_primitive_data_type() {
        Grade grade = new ProxyGenerator<Grade>(Grade.class, new Object[]{100})
                .generate(nullFilter(), nullHandler());

        assertThat(grade.value(), is(100.0));
    }

    private MethodFilter nullFilter() {
        return new MethodFilter() {
            @Override
            public boolean isHandled(Method method) {
                return false;
            }
        };
    }

    private MethodHandler nullHandler() {
        return new MethodHandler() {
            @Override
            public Object invoke(Object target, Method method, Method methodDelegate, Object[] args) throws Throwable {
                return methodDelegate.invoke(target, args);
            }
        };
    }
}
