package com.mapstruct.demo.Service.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import com.mapstruct.demo.Entity.Product;
import com.mapstruct.demo.Entity.ProductBenfits;
import com.mapstruct.demo.Entity.ProductFaq;
import com.mapstruct.demo.Entity.ProductFeatures;
import com.mapstruct.demo.Entity.Provider;
import com.mapstruct.demo.Payload.ProductDto;
import com.mapstruct.demo.Repository.ProductBenefitsRepo;
import com.mapstruct.demo.Repository.ProductFaqRepo;
import com.mapstruct.demo.Repository.ProductFeaturesRepo;
import com.mapstruct.demo.Repository.ProductRepo;
import com.mapstruct.demo.Repository.ProviderRepo;
import com.mapstruct.demo.Service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private ProductRepo productRepo;	
	@Autowired
	private ProviderRepo providerRepo;
	@Autowired
	private ProductBenefitsRepo productBenefitsRepo;	
	@Autowired
	private ProductFeaturesRepo productFeaturesRepo;
	@Autowired
	private ProductFaqRepo productFaqRepo;
	private Map<String, Class<? extends Object>> entityClassMaps;
	
	public ProductServiceImpl(ModelMapper modelmapper, ProductRepo productRepo, ProviderRepo providerRepo,
				ProductBenefitsRepo productBenefitsRepo, ProductFeaturesRepo productFeaturesRepo,
				ProductFaqRepo productFaqRepo) {
			super();
			this.modelmapper = modelmapper;
			this.productRepo = productRepo;
			this.providerRepo = providerRepo;
			this.productBenefitsRepo = productBenefitsRepo;
			this.productFeaturesRepo = productFeaturesRepo;
			this.productFaqRepo = productFaqRepo;
			this.entityClassMaps = Map.of(
		            "Product", Product.class,
		            "Provider", Provider.class,
		            "ProductBenfits", ProductBenfits.class,
		            "ProductFeatures", ProductFeatures.class,
		            "ProductFaq", ProductFaq.class
		            
		        );
		}
	 

	@Override
	public List<ProductDto> createEntities(List<ProductDto> productDtoList) {
    Map<String, Class<?>> entityClassMap = Map.of(
        "Product", Product.class,
        "Provider", Provider.class,
        "ProductBenfits", ProductBenfits.class,
        "ProductFeatures", ProductFeatures.class,
        "ProductFaq", ProductFaq.class
    );

    List<ProductDto> result = new ArrayList<>();

    for (ProductDto productDto : productDtoList) {
        String entityType = productDto.getEntityType();
        Class<?> entityClass = entityClassMap.get(entityType);

        if (entityClass != null) {
            Object entity = modelmapper.map(productDto, entityClass);
            entity = saveEntity(entity, entityClass);
            ProductDto entityDto = modelmapper.map(entity, ProductDto.class);
            entityDto.setEntityType(entityType);
            result.add(entityDto);
        }
    }

    return result;
	}
	private Object saveEntity(Object entity, Class<?> entityClass) {
	    switch (entityClass.getSimpleName()) {
	        case "Product":
	            return productRepo.save((Product) entity);
	        case "Provider":
	            return providerRepo.save((Provider) entity);
	        case "ProductBenfits":
	            return productBenefitsRepo.save((ProductBenfits) entity);
	        case "ProductFeatures":
	            return productFeaturesRepo.save((ProductFeatures) entity);
	        case "ProductFaq":
	            return productFaqRepo.save((ProductFaq) entity);
	        default:
	            throw new IllegalArgumentException("Unsupported entity type: " + entityClass.getSimpleName());
	    }
	}
		
	
	@Override
	public List<Object> getAllEntities() {
	    List<Object> allEntities = new ArrayList<>();
	
	    List<Product> products = productRepo.findAll();
	    List<Provider> providers = providerRepo.findAll();
	    List<ProductBenfits> productBenfits = productBenefitsRepo.findAll();
	    List<ProductFaq> productFaq = productFaqRepo.findAll();
	    List<ProductFeatures> productFeatures = productFeaturesRepo.findAll();
	
	    allEntities.addAll(products);
	    allEntities.addAll(providers);
	    allEntities.addAll(productBenfits);
	    allEntities.addAll(productFaq);
	    allEntities.addAll(productFeatures);
		
	    return allEntities;
	}


	public Object getEntityByTypeAndId(String entityType, Long entityId) {
	    Class<?> entityClass = entityClassMaps.get(entityType);

	    if (entityClass != null) {
	        switch (entityType) {
	            case "Product":
	                return getProductById(entityId);
	            case "Provider":
	                return getProviderById(entityId);
	            case "ProductBenfits":
	                return getProductBenefitsById(entityId);
	            case "ProductFeatures":
	                return getProductFeaturesById(entityId);
	            case "ProductFaq":
	                return getProductFaqById(entityId);
	            default:
	                throw new IllegalArgumentException("Unsupported entity type: " + entityType);
	        }
	    }

	    return null;
	}
	private Product getProductById(Long id) {
	    return productRepo.findById(id).orElse(null);
	}

	private Provider getProviderById(Long id) {
	    return providerRepo.findById(id).orElse(null);
	}

	private ProductBenfits getProductBenefitsById(Long id) {
	    return productBenefitsRepo.findById(id).orElse(null);
	}

	private ProductFeatures getProductFeaturesById(Long id) {
	    return productFeaturesRepo.findById(id).orElse(null);
	}

	private ProductFaq getProductFaqById(Long id) {
	    return productFaqRepo.findById(id).orElse(null);
	}	
	
	
	@Override
	public void remove(ProductDto productDto){
		Product product = productRepo.findById(productDto.getId()).orElseThrow();		
		productRepo.delete(product);
		
    }


	@Override
	public void removeProductBenfits(ProductDto productDto) {
		ProductBenfits productBenfits = productBenefitsRepo.findById(productDto.getBenefits_id()).orElseThrow();		
		productBenefitsRepo.delete(productBenfits);
		
	}


	@Override
	public void removeProductFeatures(ProductDto productDto) {
		ProductFeatures productFeatures = productFeaturesRepo.findById(productDto.getProduct_feature_id()).orElseThrow();	
		productFeaturesRepo.delete(productFeatures);
		
	}


	@Override
	public void removeProductFaq(ProductDto productDto) {
		ProductFaq productFaq = productFaqRepo.findById(productDto.getProduct_faq_id()).orElseThrow();	
		productFaqRepo.delete(productFaq);
		
	}


	@Override
	public void removeProvider(ProductDto productDto) {
		Provider provider = providerRepo.findById(productDto.getProvider_id()).orElseThrow();	
		providerRepo.delete(provider);
	}



	
	@Override
	public List<ProductDto> updateEntitiesById(ProductDto updatedEntityDto, String entityType, Long entityId) {
	    List<ProductDto> result = new ArrayList<>();
	    Class<?> entityClass = getEntityClass(entityType);

	    if (entityClass != null) {
	        Object existingEntity = getEntityById(entityClass, entityId);
	        if (existingEntity != null) {
	            modelmapper.map(updatedEntityDto, existingEntity);
	            Object updatedEntity = saveEntity(existingEntity, entityClass);
	            ProductDto updatedEntityDto1 = modelmapper.map(updatedEntity, ProductDto.class);
	            updatedEntityDto1.setEntityType(entityType);
	            result.add(updatedEntityDto1);
	        }
	    }

	    return result;
	}

	private Class<?> getEntityClass(String entityType) {
	    Map<String, Class<?>> entityClassMap = Map.of(
	        "Product", Product.class,
	        "Provider", Provider.class,
	        "ProductBenfits", ProductBenfits.class, // Change to the correct class name
	        "ProductFeatures", ProductFeatures.class,
	        "ProductFaq", ProductFaq.class
	        // Add more mappings for other entity types here
	    );

	    return entityClassMap.get(entityType);
	}

	private Object getEntityById(Class<?> entityClass, Long entityId) {
	    if (entityClass.equals(Product.class)) {
	        return productRepo.findById(entityId).orElse(null);
	    } else if (entityClass.equals(Provider.class)) {
	        return providerRepo.findById(entityId).orElse(null);
	    } else if (entityClass.equals(ProductBenfits.class)) { // Change to the correct class name
	        return productBenefitsRepo.findById(entityId).orElse(null);
	    } else if (entityClass.equals(ProductFeatures.class)) {
	        return productFeaturesRepo.findById(entityId).orElse(null);
	    } else if (entityClass.equals(ProductFaq.class)) {
	        return productFaqRepo.findById(entityId).orElse(null);
	    }
	    
	    // Add similar cases for other entity types here
	    return null;
	}

	private Object saveEntity1(Object entity, Class<?> entityClass) {
	    if (entityClass.equals(Product.class)) {
	        return productRepo.save((Product) entity);
	    } else if (entityClass.equals(Provider.class)) {
	        return providerRepo.save((Provider) entity);
	    } else if (entityClass.equals(ProductBenfits.class)) { // Change to the correct class name
	        return productBenefitsRepo.save((ProductBenfits) entity);
	    } else if (entityClass.equals(ProductFeatures.class)) {
	        return productFeaturesRepo.save((ProductFeatures) entity);
	    } else if (entityClass.equals(ProductFaq.class)) {
	        return productFaqRepo.save((ProductFaq) entity);
	    }
	    // Add similar cases for other entity types here

	    return null;
	}

}



	
