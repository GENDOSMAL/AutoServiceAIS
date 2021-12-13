package com.genderfrender.autoServiceApi.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")

@ToString
@Entity

public class Client
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Long id;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "patronymics")
	private String patronymics;
	
	@Column(name = "births_day")
	private LocalDateTime birthDay;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "prefer_communication_type")
	private String preferCommunicationType;
	
	@Column(name = "auto_type")
	private String AutoType;
	
	@Column(name = "auto_number")
	private String AutoNumber;
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPatronymics()
	{
		return patronymics;
	}

	public void setPatronymics(String patronymics)
	{
		this.patronymics = patronymics;
	}

	public LocalDateTime getBirthDay()
	{
		return birthDay;
	}

	public void setBirthDay(LocalDateTime birthDay)
	{
		this.birthDay = birthDay;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPreferCommunicationType()
	{
		return preferCommunicationType;
	}

	public void setPreferCommunicationType(String preferCommunicationType)
	{
		this.preferCommunicationType = preferCommunicationType;
	}

	public String getAutoType()
	{
		return AutoType;
	}

	public void setAutoType(String autoType)
	{
		AutoType = autoType;
	}

	public String getAutoNumber()
	{
		return AutoNumber;
	}

	public void setAutoNumber(String autoNumber)
	{
		AutoNumber = autoNumber;
	}
	//@OneToMany (mappedBy="client_id", fetch=FetchType.EAGER)
	//private Collection<Order> tenants;
}
