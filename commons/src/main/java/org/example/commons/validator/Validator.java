package org.example.commons.validator;

import lombok.NonNull;
import org.example.commons.model.ValidationError;

import java.util.List;

public interface Validator<T> {

    public List<ValidationError> validate(@NonNull final T entity);
}
