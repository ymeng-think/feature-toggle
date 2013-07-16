package my.think.proxy;

public class DefaultValue {
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
