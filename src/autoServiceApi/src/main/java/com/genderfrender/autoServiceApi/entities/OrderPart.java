package com.genderfrender.autoServiceApi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Table(name = "order_part")
@Getter
@Setter
@Entity
public class OrderPart 
{
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "part_id")
	private Part part;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Column(name = "count_of_use", nullable = false)
	private int countOfUse;

	public OrderPart()
	{
		
	}

	public OrderPart(Order order, int countOfUse)
	{
		this.order = order;
		this.countOfUse = countOfUse;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Part getPart()
	{
		return part;
	}

	public void setPart(Part part)
	{
		this.part = part;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public int getCountOfUse()
	{
		return countOfUse;
	}

	public void setCountOfUse(int countOfUse)
	{
		this.countOfUse = countOfUse;
	}
}
