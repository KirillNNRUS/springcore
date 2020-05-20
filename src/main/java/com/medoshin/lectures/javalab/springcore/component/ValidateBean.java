package com.medoshin.lectures.javalab.springcore.component;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Validated
@Component
public class ValidateBean {

    @Min(10)
    String field = "sdfsdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddf";
}
