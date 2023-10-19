package com.onlinegasbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlinegasbooking.entity.Admin;

@Repository
public interface IAdminRepository extends JpaRepository< Admin,Long> {
	
	@Query(value = "select a from Admin a where a.userName=?1")
	public Optional<?> getAdminByName(String userName);

}
