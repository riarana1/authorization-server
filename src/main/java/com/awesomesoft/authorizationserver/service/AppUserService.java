package com.awesomesoft.authorizationserver.service;

import com.awesomesoft.authorizationserver.dto.AppUserDto;
import com.awesomesoft.authorizationserver.dto.MessageDto;
import com.awesomesoft.authorizationserver.entity.AppUser;
import com.awesomesoft.authorizationserver.entity.Role;
import com.awesomesoft.authorizationserver.enums.RoleName;
import com.awesomesoft.authorizationserver.repository.AppUserRepository;
import com.awesomesoft.authorizationserver.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository repository;
    private final PasswordEncoder passwordEncoder;

    public MessageDto createUser(AppUserDto dto){
        AppUser appUser = AppUser.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .build();
        Set<Role> roles = new HashSet<>();
        dto.roles().forEach(r -> {
            Role role = repository.findByRole(RoleName.valueOf(r))
                    .orElseThrow(()-> new RuntimeException("role not found"));
            roles.add(role);
        });
        appUser.setRoles(roles);
        appUserRepository.save(appUser);
        return new MessageDto("user " + appUser.getUsername() + " saved");
    }
}
