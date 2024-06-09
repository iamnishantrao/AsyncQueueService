package org.example.producer.controller;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.commons.dto.RequestDto;
import org.example.commons.model.ValidationError;
import org.example.commons.validator.requestdto.RequestDtoValidatorImpl;
import org.example.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@Slf4j
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(@NonNull final ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/produce")
    public ResponseEntity<String> produceMessage(@Valid @RequestBody RequestDto requestDTO) {

        log.debug("Processing request: {}", requestDTO);

        final List<ValidationError> validationErrors = producerService.validateRequest(requestDTO);
        if (!validationErrors.isEmpty()) {
            log.info("Validation errors: {}", validationErrors);
            return ResponseEntity.badRequest().body(validationErrors.toString());
        }

        String result = producerService.produceRequest(requestDTO);
        return ResponseEntity.ok("Processing Message: " + result);
    }
}
