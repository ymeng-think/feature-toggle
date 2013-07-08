package my.think.aspect.runner;

import org.aspectj.lang.reflect.MethodSignature;

public interface Runner {
    ProceedingResult exec(MethodSignature signature, Object[] args);
}