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
public class UpdateTicketRequest {
	
	private int assigneeId;
	private int severityId;
	private int statusId;
	private String rootCause;
	private String resolution;
}
