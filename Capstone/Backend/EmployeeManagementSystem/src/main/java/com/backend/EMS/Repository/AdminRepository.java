package com.backend.EMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.EMS.Model.Admin;

public interface AdminRepository extends JpaRepository <Admin, Long> {
	Admin findByEmail(String email);

}
