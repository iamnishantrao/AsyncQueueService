package org.example.producer.controller;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.commons.dto.RequestDto;
import org.example.commons.model.ValidationError;
import org.example.producer.exception.MalformedRequestException;
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

    @NonNull private final ProducerService producerService;

    @Autowired
    public ProducerController(@NonNull final ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/produce")
    public ResponseEntity<String> produceMessage(@Valid @RequestBody RequestDto requestDTO) {

        final String requestId = producerService.getRequestId();
        log.debug("Processing requestId: {}, request {}", requestId, requestDTO);

        final List<ValidationError> validationErrors = producerService.validateRequest(requestDTO);
        if (!validationErrors.isEmpty()) {
            log.info("RequestID {}. Validation errors: {}", requestId, validationErrors);
            //return ResponseEntity.badRequest().body(validationErrors.toString());
        }

        try {
            producerService.produceRequest(requestDTO, requestId);
            log.info("Processed requestId: {} successfully", requestId);
            return ResponseEntity.ok("Message processed successfully");
        } catch (final MalformedRequestException e) {
            log.info("RequestID {}. Malformed request: {}", requestId, e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (final Exception e) {
            log.error("Error processing requestId: {}. Error: {}", requestId, e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
