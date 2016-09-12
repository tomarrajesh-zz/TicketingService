package com.webservice.ticketingservice.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.webservice.ticketingservice.aop.EventType;
import com.webservice.ticketingservice.aop.SubEventType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString()
@Entity
@Table(name = "ACTIVITY")
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACTIVITY_ID")
	private int id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "TICKET_ID")
	private Ticket ticket;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "EVENT", nullable = false)
	private EventType event;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SUB_EVENT", nullable = true)
	private SubEventType subEvent;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name = "ACTIVITY_DATE", nullable = true)
	private Date activityDate;
}
