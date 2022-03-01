package uk.gov.defra.reach.audit.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEvent {
  private String action;
  private String actionParams;
  private int httpCode;
  private UUID userId;
  private String role;
  private UUID legalEntityIdentifier;
  private String networkAddress;
}
