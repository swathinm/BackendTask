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
import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "table_product")
@Getter
@Setter
@SQLDelete(sql = "UPDATE table_product SET Is_Active = false WHERE id=?")
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "InsuranceName")
    private String insuranceName;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "ClaimStatus")
    private String claimStatus;

    @Column(name = "Premium")
    private String premium;

    @Column(name = "Range")
    private long min_max_Range;
    
    @Column(name = "IllustrationPDFDownLoad")
    private String DownLoad;
    
    @Column(name = "MoreDetails")
    private String MoreDetails;
    
    @Column(name = "Is_Active")
    private boolean isactive= Boolean.FALSE;
    
  
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Start_Date")
    private Date start_date;
    
  
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Update_Date")
    private Date update_date;
    
    
 
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="End_Date")
    private Date end_date;
    
    @PrePersist
    protected void onCreate() {
        start_date = new Date(); // Set the current date when the entity is created
    }

    
}

