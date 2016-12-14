package com.ysu.zyw.tc.model.validator.constraints;


import com.ysu.zyw.tc.model.validator.TcValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdConstraintValidator implements ConstraintValidator<Id, String> {

    @Override
    public boolean isValid(String validStr, ConstraintValidatorContext constraintContext) {
        return TcValidator.isNull(validStr) || TcValidator.isSafeString(validStr, 32, 32);
    }

    @Override
    public void initialize(Id mobile) {
    }

}
