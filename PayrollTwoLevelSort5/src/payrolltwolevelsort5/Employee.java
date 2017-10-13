/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrolltwolevelsort5;

/**
 *
 * @author Clarence
 */
public class Employee implements Comparable<Employee> {
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
    public int compareTo(Employee employee) {
        if(territoryNumber == employee.getTerritoryNumber()) {
            return 0;
        }
        else {
            if(territoryNumber > employee.getTerritoryNumber()) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }
}
