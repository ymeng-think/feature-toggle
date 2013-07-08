package my.think.aspect.runner;

public class ProceedingResult {

    private boolean shouldBeExecuted;
    private Object defaultValue;

    public ProceedingResult(boolean shouldBeExecuted, Object defaultValue) {
        this.shouldBeExecuted = shouldBeExecuted;
        this.defaultValue = defaultValue;
    }

    public boolean shouldBeExecuted() {
        return shouldBeExecuted;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }
}
