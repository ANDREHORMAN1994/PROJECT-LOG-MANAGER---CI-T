package com.javabugs.logmanager.controller;

import com.javabugs.logmanager.entity.Origin;
import com.javabugs.logmanager.service.interfaces.OriginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/origin")
public class OriginController {

    private final OriginService originService;

    public OriginController(final OriginService originService) {
        this.originService = originService;
    }

    @GetMapping
    public ResponseEntity<List<Origin>> getAllOrigin() {
        List<Origin> result = this.originService.findAll();
        return new ResponseEntity<List<Origin>>(result, HttpStatus.OK);
    }

}
