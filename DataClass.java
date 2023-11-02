package com.example.apprecettes;

public class DataClass {
    private String dataTitle;
    private String dataIngredient;
    private String dataPerson;
    private String dataDescription;
    private String dataImage;

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataIngredient() {
        return dataIngredient;
    }

    public String getDataPerson() {
        return dataPerson;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(String dataTitle, String dataIngredient, String dataPerson, String dataDescription, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataIngredient = dataIngredient;
        this.dataPerson = dataPerson;
        this.dataDescription = dataDescription;
        this.dataImage = dataImage;
    }

    public DataClass(){

    }
}
