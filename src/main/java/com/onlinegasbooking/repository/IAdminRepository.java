package com.onlinegasbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinegasbooking.entity.Admin;

@Repository
public interface IAdminRepository extends JpaRepository< Admin,Long> {

}
