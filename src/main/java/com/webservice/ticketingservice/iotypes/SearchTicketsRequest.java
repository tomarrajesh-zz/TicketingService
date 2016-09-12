package com.webservice.ticketingservice.iotypes;

import java.util.Date;

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
public class SearchTicketsRequest {
	
	private Integer requesterId;
	private Integer assigneeId;
	private Integer statusId;
	private Integer severityId;
	private Date creationDate;
	private Date lastUpdatedDate;
	private Integer pageNumber;
	private Integer pageSize;
}
