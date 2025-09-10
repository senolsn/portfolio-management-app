package com.codeweh.portfolio_management_app.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeweh.portfolio_management_app.core.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
