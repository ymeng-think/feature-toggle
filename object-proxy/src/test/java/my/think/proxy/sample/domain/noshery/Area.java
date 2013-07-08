package my.think.proxy.sample.domain.noshery;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import my.think.proxy.ProxyGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Area {

    private Country country;

    public static Area area(Country country) {
        return new Area(country);
    }

    public <T> T create(Class<T> targetClass) {
        return create(targetClass, new Object[0]);
    }

    public <T> T create(Class<T> targetClass, Object[] args) {
        return (T) new ProxyGenerator(targetClass, args)
                .generate(new MarkedByLocation(), new LocationDependingHandler());
    }

    private Area(Country country) {
        this.country = country;
    }

    private static class MarkedByLocation implements MethodFilter {
        @Override
        public boolean isHandled(Method method) {
            return method.getAnnotation(Location.class) != null;
        }
    }

    private class LocationDependingHandler implements MethodHandler {
        @Override
        public Object invoke(Object target, Method method, Method methodDelegation, Object[] args) throws Throwable {
            Location markedLocation = method.getAnnotation(Location.class);
            if (markedLocation == null) {
                return originalInvocation(target, methodDelegation, args);
            }

            if (markedLocation.value() == country) {
                return originalInvocation(target, methodDelegation, args);
            }

            return new DefaultValue(method.getReturnType()).value();
        }

        private Object originalInvocation(Object target, Method method, Object[] args)
                throws IllegalAccessException, InvocationTargetException {
            return method.invoke(target, args);
        }
    }

    private class DefaultValue {
        private Class<?> type;

        public DefaultValue(Class<?> type) {
            this.type = type;
        }

        public Object value() {
            if (isPrimitiveBoolean()) {
                return false;
            }
            if (isPrimitiveNumber()) {
                return 0;
            }
            if (isString()) {
                return "";
            }
            return null;
        }

        private boolean isPrimitiveBoolean() {
            return type == boolean.class;
        }

        private boolean isString() {
            return type == String.class;
        }

        private boolean isPrimitiveNumber() {
            return type == byte.class   ||
                    type == char.class  ||
                    type == short.class ||
                    type == int.class   ||
                    type == long.class  ||
                    type == float.class ||
                    type == double.class;
        }
    }
}
