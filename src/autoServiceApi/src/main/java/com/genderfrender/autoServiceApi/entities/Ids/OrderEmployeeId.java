package com.genderfrender.autoServiceApi.entities.Ids;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderEmployeeId implements Serializable
{
	@Column(name = "order_id")
	private long orderId;

	@Column(name = "employee_id")
	private long employeeId;

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderEmployeeId that = (OrderEmployeeId) o;
		return orderId == that.orderId && employeeId == that.employeeId;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(orderId, employeeId);
	}

	public long getOrderId()
	{
		return orderId;
	}

	public void setOrderId(long orderId)
	{
		this.orderId = orderId;
	}

	public long getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(long employeeId)
	{
		this.employeeId = employeeId;
	}
}
