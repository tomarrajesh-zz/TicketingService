package com.webservice.ticketingservice.iotypes;

import lombok.NoArgsConstructor;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@ToString()
public class Correspondence {
	
	private int ticketId;
	private int userId;
	private String content;
	private Date creationDate;
	private Date lastUpdateDate;
}
