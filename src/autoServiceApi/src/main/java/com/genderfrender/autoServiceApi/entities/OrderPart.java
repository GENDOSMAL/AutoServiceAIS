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

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("part_id")
	private Part part;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("order_Id")
	private Order order;
	
	@Column(name = "count_of_use", nullable = false)
	private int countOfUse;
}
