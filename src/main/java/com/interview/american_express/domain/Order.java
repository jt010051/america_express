package com.interview.american_express.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="order2")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ElementCollection
	@Column(name="fruits")
	List<Product> fruits = new ArrayList<>();
	@Column(name="quanOfApples")
	  private int appleQuan;
	  @Column(name="quanOfOranges")
	  private int orangeQuan;
	  @Column(name="total")
	    private double total;
	  
	  
}
