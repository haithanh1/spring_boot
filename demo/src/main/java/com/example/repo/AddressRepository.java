package com.example.repo;

import com.example.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepository extends JpaRepository<Address, Integer>, JpaSpecificationExecutor<Address> {
    
}