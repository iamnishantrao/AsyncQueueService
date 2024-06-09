package org.example.commons.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Data Transfer Object class for Hospital to represent information related to a hospital.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDto {

    @NotBlank
    private int hospitalId;

    @NotBlank @NonNull
    private String hospitalName;

    @NotBlank @NonNull
    private String hospitalAddress;

    @NotBlank @NonNull
    private int contactNumber;
}
