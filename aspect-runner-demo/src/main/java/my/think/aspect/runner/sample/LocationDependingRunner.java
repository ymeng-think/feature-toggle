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

        Country currentCountry = (Country) args[0];
        Country expectedScope = annotation.value();
        if (currentCountry == expectedScope) {
            return shouldBeExecuted();
        }

        return new ProceedingResult(false, "");
    }

    private ProceedingResult shouldBeExecuted() {
        return new ProceedingResult(true, null);
    }
}