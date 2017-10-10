/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollsort;

/**
 *
 * @author Clarence
 */
public class Employee {
    private int territoryNumber;
    private String otherInformation;

    /**
     * @return the territoryNumber
     */
    public int getTerritoryNumber() {
        return territoryNumber;
    }

    /**
     * @param territoryNumber the territoryNumber to set
     */
    public void setTerritoryNumber(int territoryNumber) {
        this.territoryNumber = territoryNumber;
    }

    /**
     * @return the otherInformation
     */
    public String getOtherInformation() {
        return otherInformation;
    }

    /**
     * @param otherInformation the otherInformation to set
     */
    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

}
