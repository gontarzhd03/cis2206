/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrolltwolevelsort5;

import java.util.ArrayList;

/**
 *
 * @author Clarence
 */
public class Employees {
    private static ArrayList <Employee> tempArrayList = new ArrayList <Employee> ();
    private static ArrayList <Employee> employees = new ArrayList <Employee> ();
    
    public void add(Employee employee){
        employees.add(employee);
    }
    
    public Employee get(int i){
        return employees.get(i);
    }
    
    public int size(){
        return employees.size();
    }
    
    public void sort(){
        employees.sort(null); //null means we are going to use comparable
        DistrictComparator districtComparator = new DistrictComparator();
        int controlBreakTerritoryNumber = employees.get(0).getTerritoryNumber();
        int indexBreak = 0; //index of last control break
        for(int i = 1; i < employees.size(); i++) {
            if(employees.get(i).getTerritoryNumber() != controlBreakTerritoryNumber) {
                performControlBreak(indexBreak, i, districtComparator);
                controlBreakTerritoryNumber = employees.get(i).getTerritoryNumber();
                indexBreak = i;
            }
        }
        performControlBreak(indexBreak, employees.size(), districtComparator);
    }
/*
    static void performControlBreak(int indexBreak, int i, DistrictComparator districtComparator) {
        for(int j = indexBreak; j < i; j++) {
            tempArrayList.add(j, employees.get(j));
        }
        tempArrayList.sort(districtComparator);
        for(int j = indexBreak; j < i; j++) {
            employees.set(j, tempArrayList.get(j - indexBreak));
        }
        tempArrayList.clear();
    }
*/
    static void performControlBreak(int indexBreak, int i, DistrictComparator districtComparator) {
        for(int j = indexBreak; j < i; j++) {
            tempArrayList.add(employees.get(j));
        }
        tempArrayList.sort(districtComparator); //we are going to use comparator
        for(int j = indexBreak, k = 0; j < i; j++, k++) {
            employees.set(j, tempArrayList.get(k));
        }
        tempArrayList.clear();
    }
}
