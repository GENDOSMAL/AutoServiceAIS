package com.genderfrender.autoServiceApi.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Getter
@Setter
@ToString
@Entity
public class Order
{
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long Id;
		
	@Column(name = "registration_date")
	private LocalDateTime registrationDate;

	@Column(name = "prefer_end_date")
	private LocalDateTime preferEndDate;
	
	@Column(name = "end_price", nullable = false)
	private float endPrice;

	@Column(name = "date_of_create")
	private LocalDateTime dateOfCreate;
	
	@Column(name = "last_edit_date")
	private LocalDateTime lastEditDate;
	
	@Column(name = "code_name")
	private String codeName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToMany(mappedBy = "employee")
	Set<OrderEmployee> ratings;
}
