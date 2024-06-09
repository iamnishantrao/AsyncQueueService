package org.example.commons.dto;

import jakarta.validation.Valid;
import lombok.*;

/**
 * Request DTO to be based when using generateReport API call of producerService.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    @Valid @NonNull
    private PatientDto patient;

    @Valid @NonNull
    private HospitalDto hospital;
}
