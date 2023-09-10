package com.mapstruct.demo.Service;
import java.util.List;


import com.mapstruct.demo.Payload.ProductDto;


public interface  ProductService {
	
	List<ProductDto> createEntities(List<ProductDto> productDtoList);
	
	List<ProductDto> updateEntitiesById(ProductDto updatedEntityDto, String entityType, Long entityId);
	
	List<Object> getAllEntities();

	Object getEntityByTypeAndId(String entityType, Long entityId);


	
	
	void remove(ProductDto productDto);
	
	void removeProductBenfits(ProductDto productDto);
	
	void removeProductFeatures(ProductDto productDto);
	
	void removeProductFaq(ProductDto productDto);
	
	void removeProvider(ProductDto productDto);
	
}
