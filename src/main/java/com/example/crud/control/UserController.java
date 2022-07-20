package com.example.crud.control;

import com.example.crud.dto.UserDTO;
import com.example.crud.model.Role;
import com.example.crud.model.User;
import com.example.crud.service.RoleService;
import com.example.crud.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "Check if a user exists")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User exists"),
            @ApiResponse(code = 404, message = "User not exists")
    })
    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal){
        if(principal == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(principal);
    }

    @ApiOperation(value = "Add a new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Create a new user and add a(or more) role(s) through parameter"),
            @ApiResponse(code = 404, message = "Email or cpf already registered")
    })
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody @Valid UserDTO userDTO,
                                     @RequestParam(name = "role", defaultValue = "user") List<String> roles){
        Optional<User> optionalEmail = this.userService.findByEmail(userDTO.getEmail());
        Optional<User> optionalCpf = this.userService.findByCpf(userDTO.getCpf());

        if(optionalEmail.isPresent()) return ResponseEntity.badRequest().body("Email already registered");

        if(optionalCpf.isPresent()) return ResponseEntity.badRequest().body("Cpf already registered");

        var user = new User();
        var listRole = new ArrayList();

        for(Role role : roleService.findAll()){
            for(String roleParam : roles){
                if(role.getName().equalsIgnoreCase("role_".concat(roleParam))){
                    listRole.add(role);
                }
            }
        }

        BeanUtils.copyProperties(userDTO, user);

        user.setRoles(listRole);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return ResponseEntity.ok(this.userService.save(user));
    }

    @ApiOperation(value = "Return a list of users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List found"),
            @ApiResponse(code = 404, message = "No one content found")
    })
    @GetMapping("/")
    public ResponseEntity<Page<User>> findAll(Pageable pageable){
        Page<User> listUser = this.userService.findAll(pageable);

        if(listUser.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(listUser);
    }
}
