package com.interview.american_express.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;


@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name="product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    @Column(name="fruit") 
private String fruit;
    
    @Column(name="price")
    private double price;
    @Column(name="quantity")
    private int quantity;
 
}
