package br.com.fiap.autoescola.service;

import br.com.fiap.autoescola.domain.Role;
import br.com.fiap.autoescola.domain.User;
import br.com.fiap.autoescola.dto.PasswordChangeDTO;
import br.com.fiap.autoescola.dto.UserCreateDTO;
import br.com.fiap.autoescola.dto.UserDTO;
import br.com.fiap.autoescola.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public UserDTO createUser(UserCreateDTO dto) {
        if (repo.existsByUsername(dto.username())) {
            throw new IllegalArgumentException("Username already exists");
        }
        Role role = dto.role() == null ? Role.USER : dto.role();
        User u = new User(dto.username(), encoder.encode(dto.password()), role);
        repo.save(u);
        return toDTO(u);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> listUsers() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional
    public UserDTO updateUser(Long id, Role newRole, Boolean enabled) {
        User u = repo.findById(id).orElseThrow();
        if (newRole != null) u.setRole(newRole);
        if (enabled != null) u.setEnabled(enabled);
        return toDTO(repo.save(u));
    }

    @Transactional
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    @Transactional
    public void changeOwnPassword(String username, PasswordChangeDTO dto) {
        User u = repo.findByUsername(username).orElseThrow();
        if (!encoder.matches(dto.oldPassword(), u.getPassword())) {
            throw new IllegalArgumentException("Old password does not match");
        }
        u.setPassword(encoder.encode(dto.newPassword()));
        repo.save(u);
    }

    private UserDTO toDTO(User u) {
        return new UserDTO(u.getId(), u.getUsername(), u.getRole(), u.isEnabled(), u.getCreatedAt());
    }
}
