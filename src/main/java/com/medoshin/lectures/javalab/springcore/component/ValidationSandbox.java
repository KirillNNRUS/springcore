package com.medoshin.lectures.javalab.springcore.component;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.*;
import java.util.Set;

@Component
public class ValidationSandbox {
    private ValidateBean validateBean;

    public ValidationSandbox(ValidateBean validateBean) {
        this.validateBean = validateBean;
    }

    @PostConstruct
    void initIt() {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        Validator validator = factory.getValidator();
        factory.close();
        validateBean.field = "ds";
        Set<ConstraintViolation<ValidateBean>> violations = validator.validate(validateBean);
        System.out.println(violations.size());
        violations.forEach(v -> System.out.println(
                v.getPropertyPath() + "-------------------------------" + v.getMessage())
        );
    }
}
