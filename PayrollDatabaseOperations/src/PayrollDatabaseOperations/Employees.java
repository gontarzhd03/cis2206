/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PayrollDatabaseOperations;

import java.util.ArrayList;

/**
 *
 * @author 55gontarhd03
 */
public class Employees {
    private ArrayList employees = new ArrayList();
    /**
     * 
     * @param employee 
     */
    public void add(Employee employee) {
        employees.add(employee);
    }
    /**
     * 
     * @param i
     * @return 
     */
    public Employee get(int i) {
        return (Employee) employees.get(i);
    }
    /**
     * 
     * @return 
     */
    public int size() {
        return employees.size();
    }
}
