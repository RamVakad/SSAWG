package sswag.model;


public enum PatternStatus {
    STATUS_PENDING, STATUS_APPROVED, STATUS_REJECTED;

    public String getStatus() {
        return name();
    }

    public static PatternStatus getStatusFromString(String str) {
        switch (str) {
            case "STATUS_PENDING": return PatternStatus.STATUS_PENDING;
            case "STATUS_APPROVED": return PatternStatus.STATUS_APPROVED;
            case "STATUS_REJECTED": return PatternStatus.STATUS_REJECTED;
            default: throw new IllegalArgumentException("Pattern status [" + str + "] not supported.");
        }
    }

}
