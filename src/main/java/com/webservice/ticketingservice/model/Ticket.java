package com.webservice.ticketingservice.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@ToString(exclude = "description")
@Entity
@Table(name="TICKET")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_ID")
	private int id;
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "SEVERITY_ID", nullable = false)
	private Severity severity;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "STATUS_ID", nullable = false)
	private Status status;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "REQUESTER_ID", nullable = false)
	private User requester;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ASSIGNEE_ID", nullable = false)
	private User assignee;
	
	@Column(name = "ROOT_CAUSE", nullable = true)
	private String rootCause;
	
	@Column(name = "RESOLUTION", nullable = true)
	private String resolution;
	
	@Column(name = "CREATION_DATE", nullable = true)
	private Date creationDate;
	
	@Column(name = "LAST_UPDATED_DATE", nullable = true)
	private Date lastUpdatedDate;
}	
