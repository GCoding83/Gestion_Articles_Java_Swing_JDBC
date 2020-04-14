/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az3eval2girouxalain;


public class Article {

    private int codeA;
    private String designationA;
    private int codeCA;
    private double prixUnitaireA;

    public Article(int codeA, String designationA, int codeCA, double prixUnitaire) {
        this.codeA = codeA;
        this.designationA = designationA;
        this.codeCA = codeCA;
        this.prixUnitaireA = prixUnitaire;
    }
    
    public Article(int codeA) {
        this.codeA = codeA;
    }

    public int getCodeA() {
        return codeA;
    }

    public void setCodeA(int codeA) {
        this.codeA = codeA;
    }

    public String getDesignationA() {
        return designationA;
    }

    public void setDesignationA(String designationA) {
        this.designationA = designationA;
    }

    public int getCodeCA() {
        return codeCA;
    }

    public void setCodeCA(int codeCA) {
        this.codeCA = codeCA;
    }

    public double getPrixUnitaireA() {
        return prixUnitaireA;
    }

    public void setPrixUnitaireA(double prixUnitaireA) {
        this.prixUnitaireA = prixUnitaireA;
    }

}
