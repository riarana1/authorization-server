package com.awesomesoft.authorizationserver.repository;

import com.awesomesoft.authorizationserver.entity.Role;
import com.awesomesoft.authorizationserver.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(RoleName roleName);
}
