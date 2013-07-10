package my.think.aspect.runner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class FeatureToggleAspect {

    @Around("methodProxy(toggleRunner)")
    public Object beforeExecute(ProceedingJoinPoint joinPoint, ToggleRunner toggleRunner) throws Throwable {
        ProceedingResult processingResult = execute(joinPoint, toggleRunner);
        if (processingResult.shouldBeExecuted()) {
            return joinPoint.proceed();
        }

        return processingResult.getDefaultValue();
    }

    @Pointcut(value = "@annotation(runner)")
    public void methodProxy(ToggleRunner runner) {
    }

    private ProceedingResult execute(ProceedingJoinPoint joinPoint, ToggleRunner toggleRunner) {
        try {
            Runner runner = toggleRunner.value().newInstance();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            return runner.execute(signature, joinPoint.getArgs());
        } catch (Exception e) {
            throw new RuntimeException("Runner should have a default constructor.", e);
        }
    }
}
