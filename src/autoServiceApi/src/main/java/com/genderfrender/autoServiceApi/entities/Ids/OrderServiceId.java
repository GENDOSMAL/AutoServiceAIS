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
public class OrderServiceId implements Serializable
{
	@Column(name = "order_id")
	private long orderId;

	@Column(name = "service_id")
	private long serviceId;

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderServiceId that = (OrderServiceId) o;
		return orderId == that.orderId && serviceId == that.serviceId;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(orderId, serviceId);
	}

	public long getOrderId()
	{
		return orderId;
	}

	public void setOrderId(long orderId)
	{
		this.orderId = orderId;
	}

	public long getServiceId()
	{
		return serviceId;
	}

	public void setServiceId(long serviceId)
	{
		this.serviceId = serviceId;
	}
}
