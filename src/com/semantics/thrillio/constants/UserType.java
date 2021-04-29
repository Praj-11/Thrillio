package com.semantics.thrillio.constants;

public enum UserType {
    
    USER("user"),
    EDITOR("editor"),
    CHIEFEDITOR("chiefeditor");

    private UserType (String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
