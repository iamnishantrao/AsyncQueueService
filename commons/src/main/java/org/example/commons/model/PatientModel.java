package org.example.commons.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Model class used internally by services which represent information related to patient.
 */
@Data
@NoArgsConstructor
public class PatientModel {

    private int patientId;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    private int phone;
}
