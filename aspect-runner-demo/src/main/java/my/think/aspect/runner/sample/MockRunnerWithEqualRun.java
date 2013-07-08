package my.think.aspect.runner.sample;

import my.think.aspect.runner.ProceedingResult;
import my.think.aspect.runner.Runner;
import org.aspectj.lang.reflect.MethodSignature;

public class MockRunnerWithEqualRun implements Runner {

    @Override
    public ProceedingResult execute(MethodSignature signature, Object[] args) {
        return new ProceedingResult(true, "");
    }
}