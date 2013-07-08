package my.think.proxy;

public class NumericCompatibility {

    private static final Class<?>[] numericTypes1 = {byte.class, char.class, short.class, int.class,
            long.class, float.class, double.class};
    private static final Class<?>[] numericTypes2 = {Byte.class, Character.class, Short.class, Integer.class,
            Long.class, Float.class, Double.class};

    private Class<?> largerScopeType;
    private Class<?> smallerScopeType;

    public NumericCompatibility(Class<?> largerScopeType, Class<?> smallerScopeType) {
        this.largerScopeType = largerScopeType;
        this.smallerScopeType = smallerScopeType;
    }

    public boolean isCompatible() {
        int index1 = findIndex(largerScopeType);
        int index2 = findIndex(smallerScopeType);

        return !(index1 < 0 || index2 < 0) && index2 <= index1;

    }

    private int findIndex(Class<?> type) {
        Class<?>[][] numericTypeMap = {numericTypes1, numericTypes2};
        for (Class<?>[] types : numericTypeMap) {
            for (int i = 0; i < types.length; i++) {
                if (types[i] == type) {
                    return i;
                }
            }
        }
        return -1;
    }


}
