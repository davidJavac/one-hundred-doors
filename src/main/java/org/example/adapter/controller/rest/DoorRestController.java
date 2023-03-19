package org.example.adapter.controller.rest;

import com.google.inject.Inject;
import org.example.config.rest.RouteModule;
import org.example.core.usecase.port.DoorsExecutor;
import org.example.core.usecase.port.dto.InputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
        Optional<Object> object = doorsExecutor.execute(new InputDto(quantity.toString()));
        return ResponseEntity.status(202).body(object.orElse(null));
    }
}
