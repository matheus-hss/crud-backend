package com.example.crud.control;

import com.example.crud.model.City;
import com.example.crud.service.CityService;
import com.example.crud.service.StateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private StateService stateService;

    @ApiOperation(value = "Return a list of cities by state")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List found"),
            @ApiResponse(code = 404, message = "List not found")
    })
    @GetMapping("/state/{stateId}")
    public ResponseEntity<List<City>> findAllByState(@PathVariable Long stateId){
        var optionalStateId = this.stateService.findById(stateId);

        if(optionalStateId.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(this.cityService.findByState(optionalStateId.get()));
    }
}
