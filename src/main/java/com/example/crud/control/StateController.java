package com.example.crud.control;

import com.example.crud.model.State;
import com.example.crud.service.StateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/state/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StateController {
    @Autowired
    private StateService stateService;

    @ApiOperation(value = "Return a list of states")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List found"),
            @ApiResponse(code = 404, message = "List not found")
    })
    @GetMapping
    public ResponseEntity<List<State>> findAll(){
        var listState = this.stateService.findAll();

        if(listState.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(listState);
    }
}
