package com.cifrazia.vision.connection.data.element.privilege;

import java.util.ArrayList;
import java.util.List;

public class FormattedPrivileges {
    private RawPrivileges rawPrivileges;
    private List<RoleFormatedDescription> formedPrivileges;


    public FormattedPrivileges(RawPrivileges rawPrivileges){
        this.rawPrivileges = rawPrivileges;

        formedPrivileges = new ArrayList<>(rawPrivileges.getRoles().size());
        for (PrivilegeRole role: rawPrivileges.getRoles()) {
            formedPrivileges.add(new RoleFormatedDescription(role));
        }
    }

    public List<RoleFormatedDescription> getFormedPrivileges() {
        return formedPrivileges;
    }

    public RawPrivileges getRawPrivileges() {
        return rawPrivileges;
    }
}
