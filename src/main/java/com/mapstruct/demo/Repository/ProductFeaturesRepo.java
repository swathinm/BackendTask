package com.mapstruct.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapstruct.demo.Entity.ProductFeatures;


@Repository
public interface ProductFeaturesRepo extends JpaRepository<ProductFeatures,Long>{
	

}
