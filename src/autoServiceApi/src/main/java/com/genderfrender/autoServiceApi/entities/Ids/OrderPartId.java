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
public class OrderPartId implements Serializable
{
	@Column(name = "order_id")
	private long orderId;

	@Column(name = "part_id")
	private long partId;

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderPartId that = (OrderPartId) o;
		return orderId == that.orderId && partId == that.partId;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(orderId, partId);
	}

	public long getOrderId()
	{
		return orderId;
	}

	public void setOrderId(long orderId)
	{
		this.orderId = orderId;
	}

	public long getPartId()
	{
		return partId;
	}

	public void setPartId(long partId)
	{
		this.partId = partId;
	}
}
