package my.think.proxy;

import java.lang.reflect.Constructor;

class ConstructorFinder {

    private Class<?> targetClass;
    private Object[] constructorArgs;

    public ConstructorFinder(Class<?> targetClass, Object[] constructorArgs) {
        this.targetClass = targetClass;
        this.constructorArgs = constructorArgs;
    }

    public Constructor<?> find() {
        Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            if (isCompatibleWithParameters(constructor)) {
                return constructor;
            }
        }

        return null;
    }

    private boolean isCompatibleWithParameters(Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        if (parameterTypes.length != constructorArgs.length) {
            return false;
        }

        for (int i = 0; i < parameterTypes.length; i++) {
            if (!isCompatible(parameterTypes[i], constructorArgs[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean isCompatible(Class<?> type, Object object) {
        if (isBoolean(object) && isBooleanCompatible(type, object.getClass())) {
            return true;
        }

        if (isNumeric(object) && isNumericCompatible(type, object.getClass())) {
            return true;
        }

        if (type.isInstance(object)) {
            return true;
        }

        return false;
    }

    private boolean isNumericCompatible(Class<?> type1, Class<?> type2) {
        return new NumericCompatibility(type1, type2).isCompatible();
    }

    private boolean isBooleanCompatible(Class<?> unknownType, Class<?> objectType) {
        return objectType == Boolean.class &&
                (unknownType == boolean.class || unknownType == Boolean.class);
    }

    private boolean isBoolean(Object object) {
        return object.getClass() == Boolean.class;
    }

    private boolean isNumeric(Object object) {
        Class<?> clazz = object.getClass();
        return clazz == Byte.class       ||
                clazz == Character.class ||
                clazz == Integer.class   ||
                clazz == Long.class      ||
                clazz == Float.class     ||
                clazz == Double.class;
    }
}
