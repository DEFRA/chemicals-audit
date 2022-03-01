package uk.gov.defra.reach.audit.entity;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "AuditEvent")
@Data
public class AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "AuditEventId")
  @Type(type = "uuid-char")
  private UUID id;
  private String action;
  private String actionParams;
  private int httpCode;
  private UUID userId;
  private String role;
  private UUID legalEntityIdentifier;
  private String networkAddress;
  @Column(insertable = false, updatable = false)
  private Instant createdAt;


}
