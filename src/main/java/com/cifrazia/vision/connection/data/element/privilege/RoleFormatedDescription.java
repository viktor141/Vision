package com.cifrazia.vision.connection.data.element.privilege;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoleFormatedDescription {

    private final PrivilegeRole role;
    private final List<ElementFormed> commandsElements;
    private final List<ElementFormed> possibilitiesElements;

    public RoleFormatedDescription(PrivilegeRole privilegeRole) {
        this.role = privilegeRole;
        commandsElements = new ArrayList<>(privilegeRole.getPerms().size());
        possibilitiesElements = new ArrayList<>(privilegeRole.getPerms().size());

        for (PrivilegeRole.PermissionOfRole permissionOfRole : privilegeRole.getPerms()) {
            if ((permissionOfRole.getDescription().equals("-") || permissionOfRole.getDescription().isEmpty()) && !permissionOfRole.getDisplay_name().startsWith("/")) {
                possibilitiesElements.add(new ElementFormed(permissionOfRole.getDisplay_name()));
            } else if (permissionOfRole.getDisplay_name() != null && !permissionOfRole.getDisplay_name().startsWith("/")) {
                commandsElements.add(new ElementFormed(permissionOfRole.getDescription(), permissionOfRole.getDisplay_name()));
            }
        }
    }

    public List<ElementFormed> getCommandsElements() {
        return commandsElements;
    }

    public List<ElementFormed> getPossibilitiesElements() {
        return possibilitiesElements;
    }

    public PrivilegeRole getRole() {
        return role;
    }

    public class ElementFormed {
        private final String text;
        private final String underText;

        public ElementFormed(String text, String underText) {
            this.text = text;
            this.underText = underText;
        }

        public ElementFormed(String text) {
            this(text, null);
        }

        public String getText() {
            return text;
        }

        public String getUnderText() {
            return underText;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            ElementFormed that = (ElementFormed) object;
            return Objects.equals(text, that.text) && Objects.equals(underText, that.underText);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text, underText);
        }
    }
}
