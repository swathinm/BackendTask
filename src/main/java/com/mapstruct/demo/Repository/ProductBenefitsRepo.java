package com.mapstruct.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapstruct.demo.Entity.ProductBenfits;


@Repository
public interface ProductBenefitsRepo extends JpaRepository<ProductBenfits, Long>{
	
}
