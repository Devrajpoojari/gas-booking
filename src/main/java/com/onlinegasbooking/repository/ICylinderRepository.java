package com.onlinegasbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinegasbooking.entity.Cylinder;

@Repository
public interface ICylinderRepository extends JpaRepository< Cylinder,Long> {

}
