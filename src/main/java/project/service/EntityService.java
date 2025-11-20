package project.service;

import project.entity.AuditRecord;

/**
 * EntityService example that integrates with the audit service.
 */
public class EntityService {
    private final AuditService auditService;

    public EntityService(AuditService auditService) {
        this.auditService = auditService;
    }

    public void createEntity(String createdBy) {
        // Create the entity logic
        AuditRecord auditRecord = auditService.createAudit(createdBy);
        // Save the entity and the auditRecord
    }

    public void updateEntity(String modifiedBy, AuditRecord auditRecord) {
        // Update the entity logic
        auditService.updateAudit(auditRecord, modifiedBy);
        // Save the updated entity and auditRecord
    }
}