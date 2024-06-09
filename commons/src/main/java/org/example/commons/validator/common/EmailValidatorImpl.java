package org.example.commons.validator.common;

import lombok.NonNull;
import org.example.commons.model.ValidationError;
import org.example.commons.validator.Validator;

import java.util.List;
import java.util.regex.Pattern;

import static org.example.commons.util.Constants.EMAIL_VALIDATION_REGEX;

public class EmailValidatorImpl implements Validator<String> {

    @Override
    public List<ValidationError> validate(@NonNull final String email) {
        final boolean isValid = Pattern
                .compile(EMAIL_VALIDATION_REGEX)
                .matcher(email)
                .matches();

        return isValid ? List.of() : List.of(
                ValidationError
                        .builder()
                        .field("email")
                        .errorMessage(String.format("Provided email %s is not valid.", email))
                        .build()
        );
    }
}
