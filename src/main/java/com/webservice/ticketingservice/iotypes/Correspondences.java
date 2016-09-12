package com.webservice.ticketingservice.iotypes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@ToString()
public class Correspondences {
	
	private Ticket ticket;
	private List<Correspondence> correspondences;
}
