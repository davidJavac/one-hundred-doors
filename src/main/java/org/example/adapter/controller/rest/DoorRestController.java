package org.example.adapter.controller.rest;

import com.google.inject.Inject;
import org.example.config.rest.RouteModule;
import org.example.core.usecase.port.DoorsExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RouteModule.DOORS)
public class DoorRestController {

    private DoorsExecutor doorsExecutor;

    @Inject
    public DoorRestController(DoorsExecutor doorsExecutor) {
        this.doorsExecutor = doorsExecutor;
    }

    @PostMapping(value = RouteModule.QUANTITY)
    public ResponseEntity<Object> postDoorsQuantity(@PathVariable Integer quantity) {
        return ResponseEntity.status(202).build();
    }
}
