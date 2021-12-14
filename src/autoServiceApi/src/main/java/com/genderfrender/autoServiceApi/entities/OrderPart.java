package com.genderfrender.autoServiceApi.entities;

import com.genderfrender.autoServiceApi.entities.Ids.OrderPartId;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_part")
@Getter
@Setter
@ToString
@Entity
public class OrderPart
{
	@EmbeddedId
	private OrderPartId id;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("partId")
	@JoinColumn(name = "part_id")
	private Part part;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Column(name = "count_of_use", nullable = false)
	private int countOfUse;
}
