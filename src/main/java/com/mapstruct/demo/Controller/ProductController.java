package com.mapstruct.demo.Controller;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.mapstruct.demo.Payload.ProductDto;
import com.mapstruct.demo.Service.ProductService;

@RestController
@RequestMapping("/api/productMatrx")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	  
	@Autowired
	 private ModelMapper modelMapper;
	
	  @PostMapping("/create")	
	   public List<ProductDto> createOrUpdateEntities(@RequestBody List<ProductDto> productDtoList) {
	        return productService.createEntities(productDtoList);
	    }
	
//	  private insertDefaultValue()
	  
	  @PutMapping("/update/{entityType}/{entityId}")
	    public ResponseEntity<List<ProductDto>> updateEntityById(
	        @PathVariable String entityType,
	        @PathVariable Long entityId,
	        @RequestBody ProductDto updatedEntityDto
	    ) {
	        List<ProductDto> updatedEntities = productService.updateEntitiesById(updatedEntityDto, entityType, entityId);

	        if (updatedEntities.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }

	        return ResponseEntity.ok(updatedEntities);
	    }
	  
	  @GetMapping("/all")
		public ResponseEntity<List<Object>> getAllEntities() {
		    List<Object> allEntities = productService.getAllEntities();
		    return ResponseEntity.ok(allEntities);
		}
	  
	  @GetMapping("/{entityType}/{entityId}")
	    public ResponseEntity<?> getEntityByTypeAndId(
	        @PathVariable String entityType,
	        @PathVariable Long entityId
	    ) {
	        Object entity = productService.getEntityByTypeAndId(entityType, entityId);

	        if (entity == null) {
	            return ResponseEntity.notFound().build();
	        }

	        return ResponseEntity.ok(entity);
	    }
	  
	  	  
	  @DeleteMapping("/softDelete/{id}")
	    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
	        
	        ProductDto productDto = new ProductDto();
	        productDto.setId(id);
	        
	        productService.remove(productDto);
	        return ResponseEntity.ok("Deleted Sucessfully");
	    }
	  
	  @DeleteMapping("/softDeleteProductBenefits/{benefits_id}")
	    public ResponseEntity<String> deleteProductBenefits(@PathVariable Long benefits_id) {
	        
	        ProductDto productDto = new ProductDto();
	        productDto.setBenefits_id(benefits_id);
	        
	        productService.removeProductBenfits(productDto);
	        return ResponseEntity.ok("Deleted Sucessfully");
	    }
	  
	  
	  @DeleteMapping("/softDeleteProductFeatres/{product_feature_id}")
	    public ResponseEntity<String> deleteProductFeatures(@PathVariable Long product_feature_id) {
	        
	        ProductDto productDto = new ProductDto();
	        productDto.setProduct_feature_id(product_feature_id);
	        
	        productService.removeProductFeatures(productDto);
	        return ResponseEntity.ok("Deleted Sucessfully");
	    }
	  
	  @DeleteMapping("/softDeleteProductFaq/{product_faq_id}")
	    public ResponseEntity<String> deleteProductFaq(@PathVariable Long product_faq_id) {
	        
	        ProductDto productDto = new ProductDto();
	        productDto.setProduct_faq_id(product_faq_id);
	        
	        productService.removeProductFaq(productDto);
	        return ResponseEntity.ok("Deleted Sucessfully");
	    }
	  
	  @DeleteMapping("/softDeleteProvider/{provider_id}")
	    public ResponseEntity<String> deleteProvider(@PathVariable Long provider_id) {
	        
	        ProductDto productDto = new ProductDto();
	        productDto.setProvider_id(provider_id);
	        
	        productService.removeProvider(productDto);
	        return ResponseEntity.ok("Deleted Sucessfully");
	    }
	  
	  
}

		
