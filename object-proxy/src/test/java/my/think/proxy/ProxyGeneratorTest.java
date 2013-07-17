package my.think.proxy;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProxyGeneratorTest {

    private Dog dog;

    @Before
    public void setUp() {
        ProxyGenerator<Dog> generator = new ProxyGenerator<Dog>(Dog.class, new Object[0]);

        dog = generator.generate(
                new MethodFilter() {
                    @Override
                    public boolean isHandled(Method method) {
                        return method.getAnnotation(Enable.class) != null;
                    }
                },
                new MethodHandler() {
                    @Override
                    public Object invoke(Object target, Method method, Method delegation, Object[] args) throws Throwable {
                        Enable isEnable = method.getAnnotation(Enable.class);
                        if (isEnable.value()) {
                            return delegation.invoke(target, args);
                        }

                        return new DefaultValue(method.getReturnType()).value();
                    }
                }
        );
    }

    @Test
    public void should_bark() {
        assertThat(dog.Bark(), is("bark"));
    }

    @Test
    public void should_NOT_bite() {
        assertThat(dog.Bite(), is(""));
    }
}


@Retention(RUNTIME)
@Target(METHOD)
@interface Enable {
    boolean value();
}

class Dog {

    @Enable(true)
    public String Bark() {
        return "bark";
    }

    @Enable(false)
    public String Bite() {
        return "bite";
    }
}
