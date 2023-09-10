package com.mapstruct.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapstruct.demo.Entity.Product;
import com.mapstruct.demo.Entity.Provider;

@Repository
public interface ProviderRepo extends JpaRepository<Provider ,Long> {
	
}
