package org.example.commons.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Request DTO to be based when using generateReport API call of producerService.
 */
@Data
@Builder
public class RequestDto {

    @Valid @NonNull
    private final PatientDto patient;

    @Valid @NonNull
    private final HospitalDto hospital;
}
