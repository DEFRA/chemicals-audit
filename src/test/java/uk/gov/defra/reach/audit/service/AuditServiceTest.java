package uk.gov.defra.reach.audit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.defra.reach.audit.dto.AuditEvent;
import uk.gov.defra.reach.audit.entity.AuditEntity;
import uk.gov.defra.reach.audit.repository.AuditRepository;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuditServiceTest {

  @Mock
  AuditRepository auditRepository;

  @InjectMocks
  AuditService auditService;

  @Test
  public void testAuditServiceCallsAuditRepository() {
    AuditEvent auditEvent = AuditEvent.builder().action("createSubmission").actionParams("actionParams").role("REACH_MANAGER").httpCode(200).userId(UUID.fromString("AAAAAAAA-0000-0100-FFFF-FFFFFFFFFFFF")).networkAddress("127.0.0.1").build();
    AuditEntity auditEntity = new AuditEntity();
    auditEntity.setAction("createSubmission");
    auditEntity.setActionParams("actionParams");
    auditEntity.setRole("REACH_MANAGER");
    auditEntity.setHttpCode(200);
    auditEntity.setUserId(UUID.fromString("AAAAAAAA-0000-0100-FFFF-FFFFFFFFFFFF"));
    auditEntity.setNetworkAddress("127.0.0.1");

    auditService.audit(auditEvent);
    verify(auditRepository).save(auditEntity);
  }
}
