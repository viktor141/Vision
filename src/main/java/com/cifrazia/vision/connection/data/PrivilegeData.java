package com.cifrazia.vision.connection.data;

import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.privilege.PrivilegeRole;

import java.util.List;

import static com.cifrazia.vision.Vision.interval;

public class PrivilegeData extends DataAuthClient {

    private List<PrivilegeRole> privilegeRoles;

    public PrivilegeData(AuthorizedClient service) {
        super(service);
    }

    public List<PrivilegeRole> getPrivilegeRoles() {
        if (privilegeRoles == null || lastUpdateTime < System.currentTimeMillis() - interval) {
            privilegeRoles = loadPrivilegeRoles();
            lastUpdateTime = System.currentTimeMillis();
        }
        return privilegeRoles;
    }

    private List<PrivilegeRole> loadPrivilegeRoles(){
        return service.getPrivilegeList();
    }
}
