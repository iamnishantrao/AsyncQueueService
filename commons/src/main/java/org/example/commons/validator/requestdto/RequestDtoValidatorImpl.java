package org.example.commons.validator.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.example.commons.dto.RequestDto;
import org.example.commons.model.ValidationError;
import org.example.commons.validator.Validator;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class RequestDtoValidatorImpl implements Validator<RequestDto> {

    @NonNull private final Validator<String> emailValidator;
    @NonNull private final Validator<String> phoneValidator;

    @Override
    public List<ValidationError> validate(@NonNull final RequestDto entity) {

        final ArrayList<ValidationError> errors = new ArrayList<>();

        errors.addAll(emailValidator.validate(entity.getPatient().getEmail()));
        errors.addAll(phoneValidator.validate(entity.getPatient().getPhone()));
        errors.addAll(phoneValidator.validate(entity.getHospital().getContactNumber()));

        return errors;
    }
}
