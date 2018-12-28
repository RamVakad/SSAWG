package sswag.model;

public enum Classification {
    CLASS_IMPLEMENTATION, CLASS_DESIGN, CLASS_ARCHITECTURE, CLASS_OPERATIONS,
    CLASS_ENGINEERING, CLASS_CONFIGURATION, CLASS_OTHER;

    public String getClassification() {
        return name();
    }

    public static Classification getClassFromString(String str) {
        switch(str) {
            case "CLASS_IMPLEMENTATION": return Classification.CLASS_IMPLEMENTATION;
            case "CLASS_DESIGN": return Classification.CLASS_DESIGN;
            case "CLASS_ARCHITECTURE": return Classification.CLASS_ARCHITECTURE;
            case "CLASS_OPERATIONS": return Classification.CLASS_OPERATIONS;
            case "CLASS_ENGINEERING": return Classification.CLASS_ENGINEERING;
            case "CLASS_CONFIGURATION": return Classification.CLASS_CONFIGURATION;
            case "CLASS_OTHER": return Classification.CLASS_OTHER;
            default: throw new IllegalArgumentException("Classification [" + str + "] not supported.");
        }
    }
}
