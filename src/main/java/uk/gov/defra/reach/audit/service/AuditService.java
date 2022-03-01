package uk.gov.defra.reach.audit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.defra.reach.audit.dto.AuditEvent;
import uk.gov.defra.reach.audit.entity.AuditEntity;
import uk.gov.defra.reach.audit.repository.AuditRepository;

@Service
@Slf4j
public class AuditService {

  private final AuditRepository auditRepository;

  @Autowired
  public AuditService(AuditRepository auditRepository) {
    this.auditRepository = auditRepository;
  }

  public void audit(AuditEvent auditEvent) {
    AuditEntity auditEntity = createAuditEntity(auditEvent);

    auditRepository.save(auditEntity);
    log.info("Audit log created successfully. Action: {} UserId: {} HttpCode: {}", auditEntity.getAction(), auditEntity.getUserId(), auditEntity.getHttpCode());
  }

  private AuditEntity createAuditEntity(AuditEvent auditEvent) {
    AuditEntity auditEntity = new AuditEntity();
    auditEntity.setAction(auditEvent.getAction());
    auditEntity.setActionParams(auditEvent.getActionParams());
    auditEntity.setHttpCode(auditEvent.getHttpCode());
    auditEntity.setUserId(auditEvent.getUserId());
    auditEntity.setRole(auditEvent.getRole());
    auditEntity.setLegalEntityIdentifier(auditEvent.getLegalEntityIdentifier());
    auditEntity.setNetworkAddress(auditEvent.getNetworkAddress());
    return auditEntity;
  }

}
