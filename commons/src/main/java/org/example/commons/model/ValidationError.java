package org.example.commons.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ValidationError {
    @NonNull private final String field;
    @NonNull private final String errorMessage;
}
