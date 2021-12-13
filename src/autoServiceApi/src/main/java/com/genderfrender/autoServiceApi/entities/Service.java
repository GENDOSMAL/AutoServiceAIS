package com.genderfrender.autoServiceApi.entities;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "services")
@Getter
@Setter
@ToString
@Entity
public class Service
{
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private long Id;
	
	@Column(name = "name")
	public String name;

	@Column(name = "price", nullable = false)
	public float price;

	public long getId()
	{
		return Id;
	}

	public void setId(long id)
	{
		Id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}
}
