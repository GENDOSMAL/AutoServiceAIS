package com.genderfrender.autoServiceApi.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Getter
@Setter
@Entity
public class Order
{
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long Id;

	@Column(name = "registration_date")
	private LocalDateTime registrationDate;

	@Column(name = "prefer_end_date")
	private LocalDateTime preferEndDate;

	@Column(name = "end_price", nullable = false)
	private float endPrice;

	@Column(name = "date_of_create")
	private LocalDateTime dateOfCreate;

	@Column(name = "last_edit_date")
	private LocalDateTime lastEditDate;

	@Column(name = "code_name")
	private String codeName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToMany(mappedBy = "employee")
	private Set<OrderEmployee> employer = new HashSet<>();

	@OneToMany(mappedBy = "part")
	private Set<OrderPart> parts = new HashSet<>();

	@OneToMany(mappedBy = "service")
	private Set<OrderService> services = new HashSet<>();

	public long getId()
	{
		return Id;
	}

	public void setId(long id)
	{
		Id = id;
	}

	public LocalDateTime getRegistrationDate()
	{
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate)
	{
		this.registrationDate = registrationDate;
	}

	public LocalDateTime getPreferEndDate()
	{
		return preferEndDate;
	}

	public void setPreferEndDate(LocalDateTime preferEndDate)
	{
		this.preferEndDate = preferEndDate;
	}

	public float getEndPrice()
	{
		return endPrice;
	}

	public void setEndPrice(float endPrice)
	{
		this.endPrice = endPrice;
	}

	public LocalDateTime getDateOfCreate()
	{
		return dateOfCreate;
	}

	public void setDateOfCreate(LocalDateTime dateOfCreate)
	{
		this.dateOfCreate = dateOfCreate;
	}

	public void setLastEditDate(LocalDateTime lastEditDate)
	{
		this.lastEditDate = lastEditDate;
	}

	public String getCodeName()
	{
		return codeName;
	}

	public void setCodeName(String codeName)
	{
		this.codeName = codeName;
	}

	public Client getClient()
	{
		return client;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	public Set<OrderEmployee> getEmployer()
	{
		return employer;
	}

	public void setEmployer(Set<OrderEmployee> employer)
	{
		this.employer = employer;
	}

	public Set<OrderPart> getParts()
	{
		return parts;
	}

	public void setParts(Set<OrderPart> parts)
	{
		this.parts = parts;
	}

	public Set<OrderService> getServices()
	{
		return services;
	}

	public void setServices(Set<OrderService> services)
	{
		this.services = services;
	}
}
