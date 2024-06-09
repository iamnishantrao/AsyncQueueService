package org.example.commons.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Data Transfer Object class for Hospital to represent information related to a hospital.
 */
@Builder
@Data
public class HospitalDto {

    @NotBlank
    private final int hospitalId;

    @NotBlank @NonNull
    private final String hospitalName;

    @NotBlank @NonNull
    private final String hospitalAddress;

    @NotBlank @NonNull
    private final int contactNumber;
}
