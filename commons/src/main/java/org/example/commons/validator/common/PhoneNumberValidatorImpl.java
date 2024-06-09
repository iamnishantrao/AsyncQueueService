package org.example.commons.validator.common;

import lombok.NonNull;
import org.example.commons.model.ValidationError;
import org.example.commons.validator.Validator;

import java.util.List;
import java.util.regex.Pattern;

import static org.example.commons.util.Constants.PHONE_NUMBER_VALIDATION_REGEX;

public class PhoneNumberValidatorImpl implements Validator<String> {

    @Override
    public List<ValidationError> validate(@NonNull final String entity) {

        final boolean isValid = Pattern
                .compile(PHONE_NUMBER_VALIDATION_REGEX)
                .matcher(entity)
                .matches();
        return isValid ? List.of() : List.of(
                ValidationError
                        .builder()
                        .field("phoneNumber")
                        .errorMessage(String.format("Provided phone number %s is not valid.", entity))
                        .build()
        );
    }
}
