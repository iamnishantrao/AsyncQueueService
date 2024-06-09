package org.example.commons.validator.common;

import lombok.NonNull;
import org.example.commons.model.ValidationError;
import org.example.commons.validator.Validator;

import java.util.List;
import java.util.regex.Pattern;

import static org.example.commons.util.Constants.PHONE_NUMBER_VALIDATION_REGEX;

public class PhoneNumberValidatorImpl implements Validator<Integer> {

    @Override
    public List<ValidationError> validate(@NonNull final Integer entity) {

        final boolean isValid = Pattern
                .compile(PHONE_NUMBER_VALIDATION_REGEX)
                .matcher(entity.toString())
                .matches();
        return isValid ? List.of() : List.of(
                ValidationError
                        .builder()
                        .field("phoneNumber")
                        .errorMessage(String.format("Provided phone number %d is not valid.", entity))
                        .build()
        );
    }
}
