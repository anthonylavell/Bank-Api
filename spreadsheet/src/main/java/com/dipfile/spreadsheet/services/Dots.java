package com.dipfile.spreadsheet.services;

public class Dots {
    private String fieldId;
    private String fieldName;

    public Dots(){}

    public Dots(String fieldID, String fieldName) {
        this.fieldId = fieldID;
        this.fieldName = fieldName;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isContainFieldId(String fieldId){
        return this.fieldId.equalsIgnoreCase(fieldId);
    }

    @Override
    public String toString() {
        return "Form{" +
                "colNum='" + fieldId + '\'' +
                ", colNam='" + fieldName + '\'' +
                '}';
    }
}
