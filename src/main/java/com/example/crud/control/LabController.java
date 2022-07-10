package com.example.crud.control;

import com.example.crud.dto.LabDTO;
import com.example.crud.model.AddressModel;
import com.example.crud.model.LabModel;
import com.example.crud.service.LabService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/lab")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LabController {
    @Autowired
    private LabService labService;

    @ApiOperation(value = "Add a new lab.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Create a new lab."),
            @ApiResponse(code = 400, message = "Email already registered.")
    })
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody @Valid LabDTO labDTO){
        Optional<LabModel> optional = labService.findByEmail(labDTO.getEmail());

        if(optional.isPresent()) return ResponseEntity.badRequest().build();

        var labModel = new LabModel();
        var addressModel = new AddressModel();

        BeanUtils.copyProperties(labDTO, labModel);
        BeanUtils.copyProperties(labDTO.getAddress(), addressModel);

        labModel.setAddress(addressModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(labService.save(labModel));
    }

    @ApiOperation(value = "Delete a lab.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lab deleted."),
            @ApiResponse(code = 404, message = "Lab not found."),
            @ApiResponse(code = 409, message = "This lab has one or more products bindings to it.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<LabModel> delete(@PathVariable UUID id){
        Optional<LabModel> optional = labService.findById(id);

        if(optional.isEmpty()) return ResponseEntity.notFound().build();

        try{
            labService.delete(optional.get());
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok(optional.get());
    }

    @ApiOperation(value = "Update a lab.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lab updated."),
            @ApiResponse(code = 404, message = "Lab not found."),
            @ApiResponse(code = 409, message = "Email already registered.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid LabDTO labDTO){
        Optional<LabModel> optional = labService.findById(id);

        if(optional.isEmpty()) return ResponseEntity.notFound().build();

        Optional<LabModel> optionalEmail = labService.findByEmail(labDTO.getEmail());

        if(!optional.get().getEmail().equalsIgnoreCase(labDTO.getEmail()) && optionalEmail.isPresent())
            return ResponseEntity.badRequest().build();

        var labModel = new LabModel();
        var address = new AddressModel();

        BeanUtils.copyProperties(labDTO, labModel);
        BeanUtils.copyProperties(labDTO.getAddress(), address);

        labModel.setId(id);
        labModel.setAddress(address);

        return ResponseEntity.ok(labService.save(labModel));
    }

    @ApiOperation(value = "Return a lab by id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lab found."),
            @ApiResponse(code = 404, message = "Lab not found.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LabModel> findById(@PathVariable UUID id){
        Optional<LabModel> optional = labService.findById(id);

        if(optional.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(optional.get());
    }

    @ApiOperation(value = "Return a list of labs.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List found."),
            @ApiResponse(code = 204, message = "No one content found.")
    })
    @GetMapping("/")
    public ResponseEntity<Page<LabModel>> findAll(@PageableDefault Pageable pageable){
        var list = labService.findAll(pageable);

        if(list.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(list);
    }
}
