package com.mapstruct.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapstruct.demo.Entity.ProductBenfits;
import com.mapstruct.demo.Entity.ProductFaq;

@Repository
public interface ProductFaqRepo extends JpaRepository<ProductFaq , Long>{
	
}
