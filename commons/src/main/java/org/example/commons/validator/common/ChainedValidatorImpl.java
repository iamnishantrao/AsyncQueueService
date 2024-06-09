package org.example.commons.validator.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.example.commons.model.ValidationError;
import org.example.commons.validator.Validator;

import java.util.List;

@Data
@Builder
public class ChainedValidatorImpl<T> implements Validator<T> {

    @NonNull private final List<Validator<T>> validators;

    @Override
    public List<ValidationError> validate(@NonNull final T entity) {
        return validators
                .stream()
                .flatMap(validator -> validator.validate(entity).stream())
                .toList();
    }
}
