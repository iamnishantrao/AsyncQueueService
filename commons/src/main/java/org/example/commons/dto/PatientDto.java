package org.example.commons.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Data Transfer Object class for Patient to represent information related to a patient.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    @NotBlank
    private int patientId;

    @NotBlank @NonNull
    private String firstName;

    @NotBlank @NonNull
    private String lastName;

    @NotBlank @NonNull
    private String email;

    @NotBlank @NonNull
    private int phone;
}
