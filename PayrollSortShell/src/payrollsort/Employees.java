/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollsort;

import java.util.ArrayList;

/**
 *
 * @author Clarence
 */
public class Employees {
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
        
    }
    
}
