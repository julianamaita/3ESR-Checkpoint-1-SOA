package br.com.fiap.autoescola.web;

import br.com.fiap.autoescola.dto.PasswordChangeDTO;
import br.com.fiap.autoescola.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordChangeDTO dto, Authentication auth) {
        userService.changeOwnPassword(auth.getName(), dto);
        return ResponseEntity.noContent().build();
    }
}
