package my.think.aspect.runner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class ConditionRunAspect {

    @Around("methodsToBeConditionRun(conditionRun)")
    public Object beforeExecute(ProceedingJoinPoint pjp, ToggleRunner conditionRun) throws Throwable {
        final ProceedingResult processingResult = isExec(pjp, conditionRun);
        if (processingResult.shouldBeExecuted()) {
            return pjp.proceed();
        }
        return processingResult.getDefaultValue();
    }

    private ProceedingResult isExec(ProceedingJoinPoint pjp, ToggleRunner conditionRun) {
        try {
            final Runner runner = conditionRun.value().newInstance();
            final MethodSignature signature = (MethodSignature) pjp.getSignature();
            return runner.exec(signature, pjp.getArgs());
        } catch (Exception e) {
            throw new RuntimeException("Runner must be empty constructor and make sure the config is ok.", e);
        }
    }

    @Pointcut(value = "@annotation(conditionRun)")
    public void methodsToBeConditionRun(ToggleRunner conditionRun) {
    }
}
