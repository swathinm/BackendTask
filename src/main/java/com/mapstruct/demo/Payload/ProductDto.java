package com.mapstruct.demo.Payload;



import java.sql.Date;
import java.util.Map;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	private String entityType; 
	
	private long id; 
	
	private String insuranceName;
	
	private String productName;
	
	private String claimStatus;
	
	private String premium;

	private long min_max_Range;
	
	private String DownLoad;
	
	private String MoreDetails;
	

	
	private long provider_id;

	private String provider_name;

	private String Description;

	private String insurance_Type;

	private String claim_Status;

	private String website;

	private String contact;
	
	
	private long benefits_id;

	private long product_id;

	private String benefits_name;

	private String benefits_priority;

	private boolean benefits_status ;


	private long product_feature_id;

	private String product_Feature_name;

	private String product_Feature_priority;

	private boolean product_feature_status ;
	
	
	private long product_faq_id;

	private String product_Faq_name;

	private String product_Faq_priority;

	private boolean product_faq_status ;
	
	private boolean isactive= Boolean.FALSE;
	  
	    private Date start_date;
	    
	   
	    private Date update_date;
	    
	   
	    private Date end_date;

	
}
