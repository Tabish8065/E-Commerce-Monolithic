package com.ecommerce.app.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;

	@NotNull(message = "First name can not be null.")
	private String firstName;

	@SuppressWarnings("unused")
	private String lastName;

	@NotNull(message = "Email can not be null.")
	@Email(message = "Please define email in 'abc@xyz.com' format")
	private String email;

	@NotNull(message = "Password can not be null.")
	private String password;

	@Length(min = 10, max = 10, message = "The phone number should be of 10 digits")
	private String phoneNumber;

	@NotNull(message = "Role can not be null. Please select either Admin or User.")
	@Pattern(regexp = "^(?i)(admin|user)$", message = "Role can either be admin or user.")
	private String role;

	private List<Integer> order_id;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Integer> getOrder_id() {
		return order_id;
	}

	public void setOrder_id(List<Integer> order_id) {
		this.order_id = order_id;
	}


	public UserModel(int user_id, @NotNull(message = "First name can not be null.") String firstName, String lastName,
			@NotNull(message = "Email can not be null.") @Email(message = "Please define email in 'abc@xyz.com' format") String email,
			@NotNull(message = "Password can not be null.") String password,
			@Length(min = 10, max = 10, message = "The phone number should be of 10 digits") String phoneNumber,
			@NotNull(message = "Role can not be null. Please select either Admin or User.") @Pattern(regexp = "^(?i)(admin|user)$", message = "Role can either be admin or user.") String role,
			List<Integer> order_id) {
		super();
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.order_id = order_id;
	}

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserModel [user_id=" + user_id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ",Role= " + role +"]";
	}
}
