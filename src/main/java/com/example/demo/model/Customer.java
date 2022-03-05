package com.example.demo.model;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Customer  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerNumber;
	private String customerName;
	private String customerMobile;
	private String customerEmail;
	private String address1;
	private String address2;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id", referencedColumnName = "customerNumber")
	private List<Savings> savings;
	

	public Long getCustomerNumber() {
		return customerNumber;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	public List<Savings> getSavings() {
		return savings;
	}
	public void setSavings(List<Savings> savings) {
		this.savings = savings;
	}

}
