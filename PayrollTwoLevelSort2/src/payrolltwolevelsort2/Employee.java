/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrolltwolevelsort2;

/**
 *
 * @author hgontarz
 */
public class Employee implements Comparable {
    private int territoryNumber;
    private int districtNumber;
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

    public int getDistrictNumber() {
        return districtNumber;
    }

    public void setDistrictNumber(int districtNumber) {
        this.districtNumber = districtNumber;
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

    @Override
    public int compareTo(Object employee) {
        if(territoryNumber == ((Employee)employee).getTerritoryNumber()) {
            if(districtNumber == ((Employee)employee).getDistrictNumber()) {
                return 0;
            }
            else {
                if(districtNumber > ((Employee)employee).getDistrictNumber()) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        }
        else {
            if(territoryNumber > ((Employee)employee).getTerritoryNumber()) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }

}
