package com.wipro.model;
//https://www.baeldung.com/learn-spring-course
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int accountNumber;
	
	String accountType;
	double balanceAmount;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balanceAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balanceAmount) != Double.doubleToLongBits(other.balanceAmount))
			return false;
		return true;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cid")
	Customer customer;

	
	
	public Account() {
		super();
	}

	public Account(int accountNumber, String accountType, double balanceAmount, Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balanceAmount = balanceAmount;
		this.customer = customer;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
