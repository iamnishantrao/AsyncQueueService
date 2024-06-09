package org.example.commons.validator.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.example.commons.dto.RequestDto;
import org.example.commons.model.ValidationError;
import org.example.commons.validator.Validator;
import org.example.commons.validator.common.ChainedValidatorImpl;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class RequestDtoValidatorImpl implements Validator<RequestDto> {

    @NonNull private final ChainedValidatorImpl<String> chainedEmailValidator;
    @NonNull private final ChainedValidatorImpl<Integer> chainedPhoneValidator;

    @Override
    public List<ValidationError> validate(@NonNull final RequestDto entity) {

        final ArrayList<ValidationError> errors = new ArrayList<>();

        errors.addAll(chainedEmailValidator.validate(entity.getPatient().getEmail()));
        errors.addAll(chainedPhoneValidator.validate(entity.getPatient().getPhone()));
        errors.addAll(chainedPhoneValidator.validate(entity.getHospital().getContactNumber()));

        return errors;
    }
}
