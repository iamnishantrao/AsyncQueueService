package org.example.commons.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Data Transfer Object class for Patient to represent information related to a patient.
 */
@Builder
@Data
public class PatientDto {

    @NotBlank
    private final int patientId;

    @NotBlank @NonNull
    private final String firstName;

    @NotBlank @NonNull
    private final String lastName;

    @NotBlank @NonNull
    private final String email;

    @NotBlank @NonNull
    private final int phone;
}
