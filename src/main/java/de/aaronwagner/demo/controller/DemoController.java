package de.aaronwagner.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@BasePathAwareController
@RequiredArgsConstructor
public class DemoController {

    @GetMapping(value = "demo")
    public ResponseEntity<?> demo(
    ) {
        return ResponseEntity.ok(LocalDateTime.now());
    }

}
