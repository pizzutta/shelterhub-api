package com.elc1090.shelterhubapi.controller;


import com.elc1090.shelterhubapi.dto.LoginDTO;
import com.elc1090.shelterhubapi.dto.LoginResponseDTO;
import com.elc1090.shelterhubapi.dto.UserRegisterDTO;
import com.elc1090.shelterhubapi.dto.VolunteerLoginDTO;
import com.elc1090.shelterhubapi.model.User;
import com.elc1090.shelterhubapi.security.TokenService;
import com.elc1090.shelterhubapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService service;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO data) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();
        String token = tokenService.generateToken(user);
        LoginResponseDTO responseDTO = new LoginResponseDTO(user.getId(), user.getCpf(), user.getRole().toString(), token);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/volunteer-login")
    public ResponseEntity volunteerLogin(@RequestBody @Valid VolunteerLoginDTO data) {
        User user = service.registerVolunteer(data);

        UsernamePasswordAuthenticationToken username = new UsernamePasswordAuthenticationToken(user, null);
        this.authenticationManager.authenticate(username);

        String token = tokenService.generateToken(user);
        LoginResponseDTO responseDTO = new LoginResponseDTO(user.getId(), user.getCpf(), user.getRole().toString(), token);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO data) {
        if (this.service.findDetailsByCpf(data.cpf()) != null)
            return ResponseEntity.badRequest().build();

        service.register(data);

        return ResponseEntity.ok().build();
    }
}
