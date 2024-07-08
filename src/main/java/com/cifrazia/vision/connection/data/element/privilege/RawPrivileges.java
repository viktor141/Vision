package com.cifrazia.vision.connection.data.element.privilege;

import java.util.HashMap;
import java.util.List;

public class RawPrivileges {
    private HashMap<String, Float> price_curve;
    private List<PrivilegeRole> roles;

    public List<PrivilegeRole> getRoles() {
        return roles;
    }
}
