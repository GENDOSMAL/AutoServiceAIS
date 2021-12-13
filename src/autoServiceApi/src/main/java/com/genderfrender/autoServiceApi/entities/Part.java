package com.genderfrender.autoServiceApi.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parts")
@Getter
@Setter
@ToString
@Entity
public class Part
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "part_id")
	private long Id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "count", nullable = false)
	private int count;
	
	@Column(name = "price", nullable = false)
	private float price;
	
	@Column(name = "user_price", nullable = false)
	private float userPrice;
	
	@Column(name = "date_of_Add")
	private LocalDateTime dateOfAdd;

	@Column(name = "last_edit_date")
	private LocalDateTime lastEditDate;

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

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public float getUserPrice()
	{
		return userPrice;
	}

	public void setUserPrice(float userPrice)
	{
		this.userPrice = userPrice;
	}

	public LocalDateTime getDateOfAdd()
	{
		return dateOfAdd;
	}

	public void setDateOfAdd(LocalDateTime dateOfAdd)
	{
		this.dateOfAdd = dateOfAdd;
	}
	
	
	public void setLastEditDate(LocalDateTime lastEditDate)
	{
		this.lastEditDate = lastEditDate;
	}
}
