package sswag.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  ROLE_CLIENT, ROLE_APPROVER, ROLE_ADMIN;

  public String getAuthority() {
    return name();
  }

  public static Role getRoleFromString(String str) {
      switch (str) {
          case "ROLE_CLIENT": return Role.ROLE_CLIENT;
          case "ROLE_APPROVER": return Role.ROLE_APPROVER;
          case "ROLE_ADMIN": return Role.ROLE_ADMIN;
          default: throw new IllegalArgumentException("Role [" + str + "] not supported.");
      }
  }

}
