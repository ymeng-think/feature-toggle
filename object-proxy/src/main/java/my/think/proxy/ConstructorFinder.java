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
        if (type.isInstance(object)) {
            return true;
        }

        return false;
    }
}
