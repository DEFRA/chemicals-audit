package uk.gov.defra.reach.audit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.defra.reach.audit.dto.AuditEvent;
import uk.gov.defra.reach.audit.service.AuditService;

@RestController
@RequestMapping("/audit")
public class AuditController {

  private final AuditService auditService;

  @Autowired
  public AuditController(AuditService auditService) {
    this.auditService = auditService;
  }

  /**
   * Audits the received event
   *
   * @param auditEvent The event to audit
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void audit(@RequestBody AuditEvent auditEvent) {
    auditService.audit(auditEvent);
  }
}
