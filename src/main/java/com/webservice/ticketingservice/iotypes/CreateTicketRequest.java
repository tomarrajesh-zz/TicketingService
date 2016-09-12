package com.webservice.ticketingservice.iotypes;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString()
public class CreateTicketRequest {
	
	private String title;
	private String description;
	private int requesterId;
	private int assigneeId;
	private int severityId;
	private int statusId;
	
}
