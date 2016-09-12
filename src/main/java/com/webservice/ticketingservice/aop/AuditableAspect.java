package com.webservice.ticketingservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
class AuditableAspect {
	
	@Autowired TicketCreationAuditer ticketCreationAuditer;
	@Autowired AddCorrespondenceHandler addCorrespondenceHandler;
	@Autowired TicketUpdationAuditer ticketUpdationAuditer; 
	
	@Pointcut("@annotation(com.webservice.ticketingservice.aop.Auditable)")
	public void AuditablePointCut() {
	}
	
	@AfterReturning("AuditablePointCut()")
	public void doAudit(JoinPoint joinPoint) {
		DoAudit doAudit = getAuditEventHandler(joinPoint);
		doAudit.audit(joinPoint.getArgs());
		System.out.println("audit logged");
	}

	private DoAudit getAuditEventHandler(JoinPoint joinPoint) {
		// TODO Auto-generated method stub
		Auditable auditableAnnotation = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(Auditable.class);
		
		DoAudit doAudit = null;
		switch(auditableAnnotation.eventType()) {
		case CorrespondenceAdded:
			doAudit = addCorrespondenceHandler;
			break;
		case CorrespondenceAskedFor:
			break;
		case TicketCreated:
			doAudit = ticketCreationAuditer;
			break;
		case TicketUpdated:
			doAudit = ticketUpdationAuditer;
			break;
		default:
			break;
		}
		return doAudit;
	}
}
