package org.motechproject.ghana.national.repository;

import org.ektorp.CouchDbConnector;
import org.motechproject.dao.MotechBaseRepository;
import org.motechproject.ghana.national.domain.StaffType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AllStaffTypes extends MotechBaseRepository<StaffType> {
    @Autowired
    protected AllStaffTypes(@Qualifier("couchDbConnector") CouchDbConnector db) {
        super(StaffType.class, db);
    }
}
