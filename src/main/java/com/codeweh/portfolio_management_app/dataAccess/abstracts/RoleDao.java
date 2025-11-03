package com.codeweh.portfolio_management_app.dataAccess.abstracts;

import com.codeweh.portfolio_management_app.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
