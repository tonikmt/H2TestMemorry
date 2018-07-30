package ru.ivan.test.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Entity
public class TableEntity {
	public TableEntity(Long id, String lastName, String firstName, Long account1, Long account2) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.account1 = account1;
		this.account2 = account2;
	}
	public TableEntity() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Long getAccount1() {
		return account1;
	}
	public void setAccount1(Long account1) {
		this.account1 = account1;
	}
	public Long getAccount2() {
		return account2;
	}
	public void setAccount2(Long account2) {
		this.account2 = account2;
	}
	@Id
	@GeneratedValue
	private Long id;
	private String lastName;
	private String firstName;
	private Long account1;
	private Long account2;
	
}
