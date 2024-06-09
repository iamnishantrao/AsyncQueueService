package org.example.commons.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Model class used internally by services which represent information related to Hospital.
 */
@Data
@NoArgsConstructor
public class HospitalModel {

    private int hospitalId;

    @NonNull
    private String hospitalName;

    @NonNull
    private String hospitalAddress;

    private int contactNumber;
}
