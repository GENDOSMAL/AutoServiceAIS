package com.genderfrender.autoServiceApi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.genderfrender.autoServiceApi.dto.PartForUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

	@JsonIgnore
	@OneToMany(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<OrderEmployee> employer = new HashSet<OrderEmployee>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private Set<OrderPart> parts = new HashSet<>();

	@Transient
	private List<PartForUser> partsUser;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<OrderService> services = new HashSet<OrderService>();

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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return true;
		//return Id == order.Id && Float.compare(order.endPrice, endPrice) == 0 && Objects.equals(registrationDate, order.registrationDate) && Objects.equals(preferEndDate, order.preferEndDate) && Objects.equals(dateOfCreate, order.dateOfCreate) && Objects.equals(lastEditDate, order.lastEditDate) && Objects.equals(codeName, order.codeName) && Objects.equals(client, order.client) && Objects.equals(employer, order.employer) && Objects.equals(parts, order.parts) && Objects.equals(services, order.services);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(Id, registrationDate, preferEndDate, endPrice, dateOfCreate, lastEditDate, codeName, client, employer, parts, services);
	}
	public LocalDateTime getLastEditDate()
	{
		return lastEditDate;
	}

	public List<PartForUser> getPartsUser()
	{
		return partsUser;
	}

	public void setPartsUser(List<PartForUser> partsUser)
	{
		this.partsUser = partsUser;
	}
}
