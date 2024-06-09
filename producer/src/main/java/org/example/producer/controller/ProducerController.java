package org.example.producer.controller;

import jakarta.validation.Valid;
import org.example.commons.dto.RequestDto;
import org.example.commons.models.RequestModel;
import org.example.producer.service.ProducerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class ProducerController {

    private final ProducerService producerService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/produce")
    public ResponseEntity<String> produceMessage(@Valid @RequestBody RequestDto requestDTO) {
        RequestModel requestModel = modelMapper.map(requestDTO, RequestModel.class);
        String result = producerService.produceRequest(requestModel);
        System.out.println(result);
        return ResponseEntity.ok("Processing Message: " + result);
    }
}
