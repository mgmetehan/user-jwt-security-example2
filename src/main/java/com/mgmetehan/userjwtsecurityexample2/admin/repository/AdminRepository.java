package com.mgmetehan.userjwtsecurityexample2.admin.repository;

import com.mgmetehan.userjwtsecurityexample2.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {
    boolean existsAdminByEmail(final String email);
    Optional<Admin> findAdminByEmail(final String email);
}
