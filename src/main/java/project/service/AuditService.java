package project.service;

import project.entity.AuditRecord;
import java.time.LocalDateTime;

/**
 * AuditService provides functionalities to handle audit records.
 */
public class AuditService {

    /**
     * Creates a new audit record.
     *
     * @param createdBy the user who created the record
     * @return a new AuditRecord with dateCreated set to the current time
     */
    public AuditRecord createAudit(String createdBy) {
        AuditRecord audit = new AuditRecord();
        audit.setCreatedBy(createdBy);
        audit.setDateCreated(LocalDateTime.now());
        return audit;
    }

    /**
     * Updates an existing audit record when it is modified.
     *
     * @param auditRecord the existing audit record to update
     * @param modifiedBy the user who modified the record
     */
    public void updateAudit(AuditRecord auditRecord, String modifiedBy) {
        auditRecord.setModifiedBy(modifiedBy);
        auditRecord.setDateModified(LocalDateTime.now());
    }
}