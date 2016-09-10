package com.webservice.ticketingservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "tickets")
@ToString(exclude = "tickets")
@Entity
@Table(name="STATUS")
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STATUS_ID")
	private int id;
	
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<Ticket> tickets;
}
