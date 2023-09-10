package com.mapstruct.demo.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "provider") // Corrected table name here
@Getter
@Setter
@SQLDelete(sql = "UPDATE provider SET Is_Active = false WHERE provider_id=?") // Corrected table name here
public class Provider   {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long provider_id;
	@Column(name="Provider_Name")
	private String provider_name;
	@Column(name="Description")
	private String Description;
	@Column(name="Insurance_Type")
	private String insurance_Type;
	@Column(name="Claim_Status")
	private String claim_Status;
	@Column(name="Website")
	private String website;
	@Column(name="Contact")
	private String contact;
	
	@Column(name = "Is_Active")
	private boolean isactive=Boolean.FALSE;
	 
	@Temporal(TemporalType.DATE)
	@Column(name="Start_Date")
	private Date start_date;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Update_Date")
	private Date update_date;
	
	@Temporal(TemporalType.DATE)
	@Column(name="End_Date")
	private Date end_date;	
	
	@PrePersist
    protected void onCreate() {
        start_date = new Date(); // Set the current date when the entity is created
    }
}
