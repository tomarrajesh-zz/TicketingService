package com.webservice.ticketingservice.iotypes;

import com.webservice.ticketingservice.aop.EventType;
import com.webservice.ticketingservice.aop.SubEventType;

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
public class Activity {
	private Integer ticketId;
	private EventType event;
	private SubEventType subEvent;
}
