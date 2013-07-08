package my.think.aspect.runner;

import org.aspectj.lang.reflect.MethodSignature;

public interface Runner {

    ProceedingResult execute(MethodSignature signature, Object[] args);
}