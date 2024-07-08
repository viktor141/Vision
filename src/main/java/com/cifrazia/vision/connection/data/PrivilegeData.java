package com.cifrazia.vision.connection.data;

import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.privilege.FormattedPrivileges;

import static com.cifrazia.vision.Vision.interval;

public class PrivilegeData extends DataAuthClient {

    private FormattedPrivileges privileges;

    public PrivilegeData(AuthorizedClient service) {
        super(service);
    }

    public FormattedPrivileges getPrivilegeRoles() {
        if (privileges == null || lastUpdateTime < System.currentTimeMillis() - interval) {
            privileges = loadPrivilegeRoles();
            lastUpdateTime = System.currentTimeMillis();
        }
        return privileges;
    }

    private FormattedPrivileges loadPrivilegeRoles() {
        return new FormattedPrivileges(service.getPrivileges());
    }
}
