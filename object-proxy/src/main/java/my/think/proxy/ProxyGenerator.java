package my.think.proxy;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.InvocationTargetException;

public class ProxyGenerator<T> {

    private Class<T> targetClass;
    private Object[] constructorArgs;

    public ProxyGenerator(Class<T> targetClass, Object[] constructorArgs) {
        this.targetClass = targetClass;
        this.constructorArgs = constructorArgs;
    }

    public T generate(MethodFilter methodFilter, MethodHandler methodHandler) {
        Class<?>[] argTypes = extractTypes(constructorArgs);

        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(targetClass);
        factory.setFilter(methodFilter);

        try {
            return (T) factory.create(argTypes, constructorArgs, methodHandler);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Can not find constructor");
        } catch (InstantiationException e) {
            throw new RuntimeException("Can not initialize action object");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Can not call constructor");
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Can not call constructor");
        }
    }

    private Class<?>[] extractTypes(Object[] constructorArgs) {
        return null;
    }

}
