package com.webservice.ticketingservice.iotypes;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode()
@ToString()
public class Ticket {
	
	private String title;
	private String description;
	private String status;
	private String severity;
	private String requesterHandle;
	private String assigneeHandle;
	private String rootCause;
	private String resolution;
	private Date creationDate;
	private Date lastUpdateDate;
}
