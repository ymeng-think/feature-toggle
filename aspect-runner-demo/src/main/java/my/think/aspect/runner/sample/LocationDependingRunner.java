package my.think.aspect.runner.sample;

import my.think.aspect.runner.ProceedingResult;
import my.think.aspect.runner.Runner;
import org.aspectj.lang.reflect.MethodSignature;

public class LocationDependingRunner implements Runner {

    @Override
    public ProceedingResult execute(MethodSignature signature, Object[] args) {
        Location annotation = signature.getMethod().getAnnotation(Location.class);
        if (annotation == null) {
            return shouldBeExecuted();
        }

        if (!isJapan(annotation.value())) {
            return shouldBeExecuted();
        }

        return new ProceedingResult(false, "");
    }

    private boolean isJapan(Country country) {
        return country == Country.Others;
    }

    private ProceedingResult shouldBeExecuted() {
        return new ProceedingResult(true, null);
    }
}