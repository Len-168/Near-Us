package app.near.us.controller;

import app.near.us.service.TestingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/test")
public class TestController {

    private final TestingService service;

    @PostMapping
    public void create(){
        service.createTest();
    }

}
