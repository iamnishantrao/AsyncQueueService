package org.example.producer.validator;

import org.example.commons.validator.common.ChainedValidatorImpl;
import org.example.commons.validator.common.EmailValidatorImpl;
import org.example.commons.validator.common.PhoneNumberValidatorImpl;
import org.example.commons.validator.requestdto.RequestDtoValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestDtoValidatorProvider {

    @Bean
    public RequestDtoValidatorImpl chainedValidator() {

        final ChainedValidatorImpl<String> emailChainedValidator = ChainedValidatorImpl
                .<String>builder()
                .validators(List.of(new EmailValidatorImpl()))
                .build();

        final ChainedValidatorImpl<Integer> phoneNumberChainedValidator = ChainedValidatorImpl
                .<Integer>builder()
                .validators(List.of(new PhoneNumberValidatorImpl()))
                .build();

        return RequestDtoValidatorImpl
                .builder()
                .chainedEmailValidator(emailChainedValidator)
                .chainedPhoneValidator(phoneNumberChainedValidator)
                .build();

    }
}
