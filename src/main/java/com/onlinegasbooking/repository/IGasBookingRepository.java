package com.onlinegasbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinegasbooking.entity.GasBooking;

@Repository
public interface IGasBookingRepository extends JpaRepository< GasBooking,Long> {

}
