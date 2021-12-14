package com.genderfrender.autoServiceApi.entities;

import com.genderfrender.autoServiceApi.entities.Ids.OrderServiceId;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_services")
@Getter
@Setter
@ToString
@Entity
public class OrderService
{
	@EmbeddedId
	private OrderServiceId id;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("serviceId")
	@JoinColumn(name = "service_id")
	private Service service;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Column(name = "count_of_use", nullable = false)
	private int countOfUse;
}
