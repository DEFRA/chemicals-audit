package uk.gov.defra.reach.audit.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.defra.reach.audit.dto.AuditEvent;
import uk.gov.defra.reach.audit.service.AuditService;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuditControllerTest {

  @Mock
  AuditService auditService;

  @InjectMocks
  AuditController auditController;

  @Test
  public void testAuditControllerCallsAuditService() {
    AuditEvent auditEvent = new AuditEvent();
    auditController.audit(auditEvent);
    verify(auditService).audit(auditEvent);
  }

}
