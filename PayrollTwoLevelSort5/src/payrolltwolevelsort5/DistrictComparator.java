/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrolltwolevelsort5;

import java.util.Comparator;

/**
 *
 * @author 55gontarhd03
 */
public class DistrictComparator implements Comparator<Employee> {
    public int compare(Employee employee1, Employee employee2) {
        return (int) Math.signum(employee1.getDistrictNumber() - employee2.getDistrictNumber());
    }
}
