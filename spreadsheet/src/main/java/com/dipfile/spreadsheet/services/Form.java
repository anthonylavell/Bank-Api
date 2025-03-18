package com.dipfile.spreadsheet.services;

public class Form {
    private String name;
    private String programId;

    public Form(){}

    public Form(String name, String programId){
        this.name = name;
        this.programId = programId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }
}
