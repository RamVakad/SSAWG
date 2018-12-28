package sswag.model;

public enum SecurityDomain {
    SEC_AUTHENTICATION, SEC_AUTHORIZATION, SEC_LOGGING, SEC_INPUT_VALIDATION,
    SEC_OTHER;

    public String getSecurityDomain() {
        return name();
    }

    public static SecurityDomain getSecDomainFromString(String str) {
        switch(str) {
            case "SEC_AUTHENTICATION": return SecurityDomain.SEC_AUTHENTICATION;
            case "SEC_AUTHORIZATION": return SecurityDomain.SEC_AUTHORIZATION;
            case "SEC_LOGGING": return SecurityDomain.SEC_LOGGING;
            case "SEC_INPUT_VALIDATION": return SecurityDomain.SEC_INPUT_VALIDATION;
            case "SEC_OTHER": return SecurityDomain.SEC_OTHER;
            default: throw new IllegalArgumentException("Security Domain [" + str + "] not supported.");
        }
    }
}
