package uk.gov.defra.reach.audit.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import uk.gov.defra.reach.audit.entity.AuditEntity;


public interface AuditRepository extends CrudRepository<AuditEntity, UUID> {
  
}
