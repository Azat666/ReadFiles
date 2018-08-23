package com.example.student.readfiles;


public class ModelInfo {

    public String textMarka;
    public String textModel;
    public String textPrice;

    public ModelInfo() {

    }

    public ModelInfo(String textMarka, String textModel, String textPrice) {
        this.textMarka = textMarka;
        this.textModel = textModel;
        this.textPrice = textPrice;
    }

    public String getTextMarka() {
        return textMarka;
    }

    public void setTextMarka(String textMarka) {
        this.textMarka = textMarka;
    }

    public String getTextModel() {
        return textModel;
    }

    public void setTextModel(String textModel) {
        this.textModel = textModel;
    }

    public String getTextPrice() {
        return textPrice;
    }

    public void setTextPrice(String textPrinc) {
        this.textPrice = textPrinc;
    }
}