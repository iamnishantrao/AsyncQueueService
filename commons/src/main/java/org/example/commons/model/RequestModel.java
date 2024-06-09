package org.example.commons.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Model class used internally by services which represent information related to request for generating a report.
 */
@Data
@NoArgsConstructor
public class RequestModel {

    @NonNull private String requestId;

    @NonNull private PatientModel patient;

    @NonNull private HospitalModel hospital;
}
