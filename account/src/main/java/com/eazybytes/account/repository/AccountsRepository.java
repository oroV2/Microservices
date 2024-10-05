package com.eazybytes.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eazybytes.account.entity.Accounts;

@Repository
public interface AccountsRepository extend JpaRepository<Accounts, Long> {

	Optional<Accounts> findByCustomerId(Long customerId);
	
	@Transactional
	@Modifying
	void deleteByCustomerId(Long customerId);
	
	
}
