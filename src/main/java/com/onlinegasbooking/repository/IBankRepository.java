package com.onlinegasbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlinegasbooking.entity.Bank;

@Repository
public interface IBankRepository extends JpaRepository<Bank, Long> {
	
	@Query(value = "select b from Bank b where b.bankName=?1")
	public Optional<Bank> findBankByName(String bankName);

}
