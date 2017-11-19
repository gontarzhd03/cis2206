/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollretrievederbydatabase;

import java.util.ArrayList;

/**
 *
 * @author 55gontarhd03
 */
public class Employees {
    private static ArrayList employees = new ArrayList();
    public void add(Employee employee) {
        employees.add(employee);
    }
    public Employee get(int i) {
        return (Employee) employees.get(i);
    }
    public int size() {
        return employees.size();
    }
}
