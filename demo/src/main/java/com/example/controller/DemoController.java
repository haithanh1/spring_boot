package com.example.controller;

import com.example.exption.ResourceNotFoundException;
import com.example.repo.AddressRepository;
//import com.example.service.address.AddressService;
import com.example.service.kafka.KafkaProducerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController

@RequestMapping("/user")
public class DemoController {

    Logger logger = LogManager.getLogger(DemoController.class);
    private final AddressRepository addressRepository;

    private final KafkaProducerService kafkaProducerService;

//    private final AddressService addressService;

    public DemoController(AddressRepository addressRepository, KafkaProducerService kafkaProducerService) {
        this.addressRepository = addressRepository;
        this.kafkaProducerService = kafkaProducerService;
//        this.addressService = addressService;
    }



    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public ResponseEntity<?> demo(Principal principal) throws ResourceNotFoundException {
        System.out.println(principal.getName());
        return null;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void sendToKafka() {
//        kafkaProducerService.sendMessage("demo1", "message1111");
//        ThreadPoolTaskExecutor threadPoolTaskExecutor =new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.execute(() -> {
//
//        });
//        addressService.calculateFactorial(4, 5);
    }


}
