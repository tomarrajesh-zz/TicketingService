package com.webservice.ticketingservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "content")
@Entity
@Table(name="CORRESPONDENCE")
public class Correspondence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CORRESPONDENCE_ID")
	private int id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "TICKET_ID")
	private Ticket ticket;

	@ManyToOne(optional = false)
	@JoinColumn(name = "USER_ID")
	private User correspondent;
	
	@Column(name = "CONTENT", nullable = false)
	private String content;
	//private byte[] correspondence;
}
