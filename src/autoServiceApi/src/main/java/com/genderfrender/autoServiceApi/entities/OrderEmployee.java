package com.genderfrender.autoServiceApi.entities;

import com.genderfrender.autoServiceApi.entities.Ids.OrderEmployeeId;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_employee")
@Getter
@Setter
@ToString
@Entity
public class OrderEmployee
{
	@EmbeddedId
	private OrderEmployeeId id;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	@MapsId("employeeId")
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(name = "count_of_hour", nullable = false)
	private int countOfHour;
}
