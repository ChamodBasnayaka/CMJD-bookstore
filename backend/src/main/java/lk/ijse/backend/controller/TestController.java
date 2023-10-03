package lk.ijse.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.backend.payloads.response.MassegeResponse;

@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new MassegeResponse("This is test controller for Authentication"));
    }
}
